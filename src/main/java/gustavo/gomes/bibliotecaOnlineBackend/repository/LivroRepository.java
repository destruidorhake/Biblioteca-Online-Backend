package gustavo.gomes.bibliotecaOnlineBackend.repository;

import gustavo.gomes.bibliotecaOnlineBackend.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
    Optional<Livro> findByNomeLivroAndAutorLivro(String nomeLivro, String autorLivro);

}