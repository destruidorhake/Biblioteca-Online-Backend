package gustavo.gomes.bibliotecaOnlineBackend.service;

import gustavo.gomes.bibliotecaOnlineBackend.model.Professor;
import gustavo.gomes.bibliotecaOnlineBackend.model.TipoAtivo;
import gustavo.gomes.bibliotecaOnlineBackend.repository.ProfessorRepository;
import gustavo.gomes.bibliotecaOnlineBackend.repository.TipoAtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TipoAtivoRepository tipoAtivoRepository;

    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    public Optional<Professor> findByUsuarioAndSenha(String usuario, String senha) {
        return professorRepository.findByUsuarioProfessorAndSenhaProfessor(usuario, senha);
    }

    public Optional<Professor> findByUsuarioProfessor(String usuarioProfessor) {
        return professorRepository.findByUsuarioProfessor(usuarioProfessor);
    }

    public Optional<Professor> findByNumeroDocumento(String numeroDocumento) {
        return professorRepository.findByNumeroDocumento(numeroDocumento);
    }

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public Optional<TipoAtivo> findTipoAtivoById(Integer idAtivo) {
        return tipoAtivoRepository.findById(idAtivo);
    }
}