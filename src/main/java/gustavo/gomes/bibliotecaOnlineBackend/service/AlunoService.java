package gustavo.gomes.bibliotecaOnlineBackend.service;

import gustavo.gomes.bibliotecaOnlineBackend.model.Aluno;
import gustavo.gomes.bibliotecaOnlineBackend.model.TipoAtivo;
import gustavo.gomes.bibliotecaOnlineBackend.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return alunoRepository.save(aluno);
    }

    public void deleteById(int id) {
        alunoRepository.deleteById(id);
    }

    public Optional<Aluno> findByNomeAluno(String nomeAluno) {
        return alunoRepository.findByNomeAluno(nomeAluno);
    }

    public Optional<Aluno> findById(int id) {
        return alunoRepository.findById(id);
    }
}