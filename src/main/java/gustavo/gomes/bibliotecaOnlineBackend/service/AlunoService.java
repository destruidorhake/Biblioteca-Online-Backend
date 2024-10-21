package gustavo.gomes.bibliotecaOnlineBackend.service;

import gustavo.gomes.bibliotecaOnlineBackend.model.Aluno;
import gustavo.gomes.bibliotecaOnlineBackend.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno save(Aluno aluno) {
        // Verifica se já existe um aluno com o mesmo nome
        Optional<Aluno> alunoExistente = alunoRepository.findByNomeAluno(aluno.getNomeAluno());
        if (alunoExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Aluno com o mesmo nome já cadastrado.");
        }
        return alunoRepository.save(aluno);
    }

    public void deleteById(int id) {
        // Verifica se o aluno existe antes de deletar
        if (!alunoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado para exclusão.");
        }
        alunoRepository.deleteById(id);
    }

    public Optional<Aluno> findByNomeAluno(String nomeAluno) {
        return alunoRepository.findByNomeAluno(nomeAluno);
    }

    public Optional<Aluno> findById(int id) {
        return alunoRepository.findById(id);
    }
}