package gustavo.gomes.bibliotecaOnlineBackend.service;

import gustavo.gomes.bibliotecaOnlineBackend.DTO.AlunoLivroDTO;
import gustavo.gomes.bibliotecaOnlineBackend.config.erros.AlunoLivroException;
import gustavo.gomes.bibliotecaOnlineBackend.config.erros.ConflictException;
import gustavo.gomes.bibliotecaOnlineBackend.model.Aluno;
import gustavo.gomes.bibliotecaOnlineBackend.model.AlunoLivro;
import gustavo.gomes.bibliotecaOnlineBackend.model.AlunoLivroId;
import gustavo.gomes.bibliotecaOnlineBackend.model.Livro;
import gustavo.gomes.bibliotecaOnlineBackend.repository.AlunoLivroRepository;
import gustavo.gomes.bibliotecaOnlineBackend.repository.AlunoRepository;
import gustavo.gomes.bibliotecaOnlineBackend.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AlunoLivroService {

    @Autowired
    private AlunoLivroRepository alunoLivroRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<AlunoLivro> getAllAlunoLivros() {
        return alunoLivroRepository.findAll();
    }

    public AlunoLivro saveAlunoLivro(AlunoLivroDTO dto) {
        // Validações
        if (dto.getAlunoId() == null || dto.getLivroId() == null) {
            throw new AlunoLivroException("Aluno ID e Livro ID devem ser informados.");
        }

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new AlunoLivroException("Aluno não encontrado"));
        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new AlunoLivroException("Livro não encontrado"));

        // Verifica se o livro já está atribuído ao aluno
        if (alunoLivroRepository.existsById(new AlunoLivroId(dto.getAlunoId(), dto.getLivroId()))) {
            throw new ConflictException("Este livro já está atribuído a este aluno.");
        }

        // Verifica a quantidade de livros disponíveis
        if (livro.getSequencia() >= livro.getQuantidadeLivro()) {
            throw new AlunoLivroException("A sequência do livro não pode exceder a quantidade disponível.");
        }

        AlunoLivro alunoLivro = new AlunoLivro();
        alunoLivro.setId(new AlunoLivroId(dto.getAlunoId(), dto.getLivroId()));
        alunoLivro.setAluno(aluno);
        alunoLivro.setLivro(livro);
        alunoLivro.setAlunoNome(dto.getAlunoNome());
        alunoLivro.setLivroNome(dto.getLivroNome());

        // Incrementa a sequência
        livro.setSequencia(livro.getSequencia() + 1);
        livroRepository.save(livro); // Salva a nova sequência no livro

        // Define a data do registro
        alunoLivro.setDataRegistro(dto.getDataRegistro().atStartOfDay());

        return alunoLivroRepository.save(alunoLivro);
    }

    public void desatribuirLivro(Integer alunoId, Integer livroId) {
        AlunoLivroId alunoLivroId = new AlunoLivroId(alunoId, livroId);

        // Verifica se o registro existe
        AlunoLivro alunoLivro = alunoLivroRepository.findById(alunoLivroId)
                .orElseThrow(() -> new AlunoLivroException("A atribuição do livro não foi encontrada."));

        // Decrementa a sequência do livro
        Livro livro = alunoLivro.getLivro();
        if (livro.getSequencia() > 0) {
            livro.setSequencia(livro.getSequencia() - 1);
        }

        // Salva a atualização do livro
        livroRepository.save(livro);

        // Remove a atribuição do livro
        alunoLivroRepository.delete(alunoLivro);
    }
}