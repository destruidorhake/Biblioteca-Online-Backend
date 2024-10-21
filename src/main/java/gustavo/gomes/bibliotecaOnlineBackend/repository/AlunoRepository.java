package gustavo.gomes.bibliotecaOnlineBackend.repository;

import gustavo.gomes.bibliotecaOnlineBackend.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    Optional<Aluno> findById(Integer id);
    Optional<Aluno> findByNomeAluno(String nomeAluno);

}