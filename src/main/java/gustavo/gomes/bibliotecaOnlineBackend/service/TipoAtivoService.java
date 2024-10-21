package gustavo.gomes.bibliotecaOnlineBackend.service;

import gustavo.gomes.bibliotecaOnlineBackend.model.TipoAtivo;
import gustavo.gomes.bibliotecaOnlineBackend.repository.TipoAtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoAtivoService {

    @Autowired
    private TipoAtivoRepository tipoAtivoRepository;

    public List<TipoAtivo> getAllTipoAtivo() {
        return tipoAtivoRepository.findAll();
    }

    public Optional<TipoAtivo> findById(int id) {
        return tipoAtivoRepository.findById(id);
    }
}
