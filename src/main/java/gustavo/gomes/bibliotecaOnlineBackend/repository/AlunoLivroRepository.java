package gustavo.gomes.bibliotecaOnlineBackend.repository;

import gustavo.gomes.bibliotecaOnlineBackend.model.AlunoLivro;
import gustavo.gomes.bibliotecaOnlineBackend.model.AlunoLivroId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoLivroRepository extends JpaRepository<AlunoLivro, AlunoLivroId> {
}
