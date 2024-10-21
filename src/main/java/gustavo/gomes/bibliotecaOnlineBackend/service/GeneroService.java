package gustavo.gomes.bibliotecaOnlineBackend.service;

import gustavo.gomes.bibliotecaOnlineBackend.DTO.GeneroLinguisticoDTO;
import gustavo.gomes.bibliotecaOnlineBackend.DTO.GeneroTextualDTO;
import gustavo.gomes.bibliotecaOnlineBackend.model.GeneroLinguistico;
import gustavo.gomes.bibliotecaOnlineBackend.model.GeneroTextual;
import gustavo.gomes.bibliotecaOnlineBackend.repository.GeneroLinguisticoRepository;
import gustavo.gomes.bibliotecaOnlineBackend.repository.GeneroTextualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneroService {
    @Autowired
    private GeneroTextualRepository generoTextualRepository;

    @Autowired
    private GeneroLinguisticoRepository generoLinguisticoRepository;

    public List<GeneroTextualDTO> getGenerosTextuais() {
        return generoTextualRepository.findAll().stream()
                .map(genero -> new GeneroTextualDTO(genero.getIdTextual(), genero.getItemTextual()))
                .collect(Collectors.toList());
    }

    public List<GeneroLinguisticoDTO> getGenerosLinguisticos() {
        return generoLinguisticoRepository.findAll().stream()
                .map(genero -> new GeneroLinguisticoDTO(genero.getIdLinguistico(), genero.getItemLinguistico()))
                .collect(Collectors.toList());
    }

    public Optional<GeneroLinguistico> findGeneroLinguisticoById(Integer id) {
        return generoLinguisticoRepository.findById(id);
    }

    public Optional<GeneroTextual> findGeneroTextualById(Integer id) {
        return generoTextualRepository.findById(id);
    }
}