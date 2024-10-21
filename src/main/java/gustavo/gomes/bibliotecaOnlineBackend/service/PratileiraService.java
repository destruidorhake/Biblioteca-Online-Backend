package gustavo.gomes.bibliotecaOnlineBackend.service;

import gustavo.gomes.bibliotecaOnlineBackend.DTO.PratileiraDTO;
import gustavo.gomes.bibliotecaOnlineBackend.model.Pratileira;
import gustavo.gomes.bibliotecaOnlineBackend.repository.PratileiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PratileiraService {

    @Autowired
    private PratileiraRepository pratileiraRepository;

    // Método existente para buscar todas as prateleiras
    public List<PratileiraDTO> getAllPratileiras() {
        return pratileiraRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Novo método para buscar uma prateleira pelo ID
    public Optional<Pratileira> findById(Integer id) {
        return pratileiraRepository.findById(id);
    }

    // Método para salvar uma nova prateleira
    public PratileiraDTO save(PratileiraDTO pratileiraDTO) {
        Pratileira pratileira = new Pratileira();
        pratileira.setNomePratileira(pratileiraDTO.getNomePratileira());

        try {
            Pratileira pratileiraSalva = pratileiraRepository.save(pratileira);
            return mapToDTO(pratileiraSalva);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao salvar a prateleira. Verifique os dados fornecidos.");
        }
    }

    // Método para deletar uma prateleira
    public void delete(Integer id) {
        if (!pratileiraRepository.existsById(id)) {
            throw new RuntimeException("Prateleira não encontrada");
        }
        pratileiraRepository.deleteById(id);
    }

    // Método para mapear Pratileira para PratileiraDTO
    private PratileiraDTO mapToDTO(Pratileira pratileira) {
        PratileiraDTO dto = new PratileiraDTO();
        dto.setIdPratileira(pratileira.getIdPratileira());
        dto.setNomePratileira(pratileira.getNomePratileira());
        return dto;
    }
}