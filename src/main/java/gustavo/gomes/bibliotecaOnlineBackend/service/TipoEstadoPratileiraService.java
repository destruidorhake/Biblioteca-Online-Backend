package gustavo.gomes.bibliotecaOnlineBackend.service;

import gustavo.gomes.bibliotecaOnlineBackend.model.TipoEstadoPratileira;
import gustavo.gomes.bibliotecaOnlineBackend.repository.TipoEstadoPratileiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEstadoPratileiraService {


    @Autowired
    private TipoEstadoPratileiraRepository tipoEstadoPratileiraRepository;

    public List<TipoEstadoPratileira> getAllTipoEstadoPratileira() {
        return tipoEstadoPratileiraRepository.findAll();
    }
}