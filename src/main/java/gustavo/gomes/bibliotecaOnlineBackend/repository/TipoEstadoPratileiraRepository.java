package gustavo.gomes.bibliotecaOnlineBackend.repository;

import gustavo.gomes.bibliotecaOnlineBackend.model.TipoEstadoPratileira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface TipoEstadoPratileiraRepository extends JpaRepository<TipoEstadoPratileira, Integer> {
}
