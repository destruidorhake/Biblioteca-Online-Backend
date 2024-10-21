package gustavo.gomes.bibliotecaOnlineBackend.repository;

import gustavo.gomes.bibliotecaOnlineBackend.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Optional<Professor> findByUsuarioProfessorAndSenhaProfessor(String usuarioProfessor, String senhaProfessor);
    Optional<Professor> findByUsuarioProfessor(String usuarioProfessor);
    Optional<Professor> findByNumeroDocumento(String numeroDocumento); // Adiciona o método de busca por CPF/CNPJ
}