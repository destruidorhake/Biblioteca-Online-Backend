package gustavo.gomes.bibliotecaOnlineBackend.repository;

import gustavo.gomes.bibliotecaOnlineBackend.model.TipoAtivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAtivoRepository extends JpaRepository<TipoAtivo, Integer> {
}
