package gustavo.gomes.bibliotecaOnlineBackend.service;

import gustavo.gomes.bibliotecaOnlineBackend.DTO.LivroDTO;
import gustavo.gomes.bibliotecaOnlineBackend.config.erros.AlunoLivroException;
import gustavo.gomes.bibliotecaOnlineBackend.config.erros.LivroJaCadastradoException;
import gustavo.gomes.bibliotecaOnlineBackend.model.GeneroLinguistico;
import gustavo.gomes.bibliotecaOnlineBackend.model.GeneroTextual;
import gustavo.gomes.bibliotecaOnlineBackend.model.Livro;
import gustavo.gomes.bibliotecaOnlineBackend.model.Pratileira;
import gustavo.gomes.bibliotecaOnlineBackend.repository.GeneroLinguisticoRepository;
import gustavo.gomes.bibliotecaOnlineBackend.repository.GeneroTextualRepository;
import gustavo.gomes.bibliotecaOnlineBackend.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private GeneroLinguisticoRepository generoLinguisticoRepository;

    @Autowired
    private GeneroTextualRepository generoTextualRepository;

    @Autowired
    private PratileiraService pratileiraService;

    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }

    public Livro saveLivro(LivroDTO livroDTO) {
        // Verifica se já existe um livro com o mesmo nome e autor
        Optional<Livro> livroExistente = livroRepository.findByNomeLivroAndAutorLivro(livroDTO.getNomeLivro(), livroDTO.getAutorLivro());
        if (livroExistente.isPresent()) {
            throw new LivroJaCadastradoException("Já existe um livro cadastrado com o mesmo nome e autor.");
        }

        Livro livro = new Livro(livroDTO);

        // Buscando prateleira, gênero linguístico e gênero textual
        Pratileira pratileira = pratileiraService.findById(livroDTO.getPratileira().getIdPratileira())
                .orElseThrow(() -> new EntityNotFoundException("Prateleira não encontrada"));
        livro.setPratileira(pratileira);

        GeneroLinguistico generoLinguistico = generoLinguisticoRepository.findById(livroDTO.getGeneroLinguistico().getId())
                .orElseThrow(() -> new EntityNotFoundException("Gênero Linguístico não encontrado"));
        livro.setGeneroLinguistico(generoLinguistico);

        GeneroTextual generoTextual = generoTextualRepository.findById(livroDTO.getGeneroTextual().getId())
                .orElseThrow(() -> new EntityNotFoundException("Gênero Textual não encontrado"));
        livro.setGeneroTextual(generoTextual);

        // Salvando o livro
        return livroRepository.save(livro);
    }

    public void deleteLivro(Integer id) {
        try {
            if (!livroRepository.existsById(id)) {
                throw new EntityNotFoundException("Livro não encontrado");
            }
            livroRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new AlunoLivroException("Erro: Não é possível excluir o livro, pois ele está vinculado a um aluno.");
        }
    }

    public Livro updateQuantidadeLivro(Integer id, LivroDTO livroDTO) {
        Livro existingLivro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        existingLivro.setQuantidadeLivro(livroDTO.getQuantidadeLivro());
        return livroRepository.save(existingLivro);
    }

    public Integer getSequenciaLivro(Integer id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return livro.getSequencia();
    }

    public Livro updateSequenciaLivro(Integer id, Integer novaSequencia) {
        if (novaSequencia == null) {
            throw new IllegalArgumentException("A sequência não pode ser nula.");
        }
        if (novaSequencia < 0) {
            throw new IllegalArgumentException("A sequência não pode ser um número negativo.");
        }

        Livro existingLivro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        existingLivro.setSequencia(novaSequencia);
        return livroRepository.save(existingLivro);
    }

}