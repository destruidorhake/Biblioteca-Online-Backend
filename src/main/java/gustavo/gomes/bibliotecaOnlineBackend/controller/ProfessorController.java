package gustavo.gomes.bibliotecaOnlineBackend.controller;

import gustavo.gomes.bibliotecaOnlineBackend.DTO.ProfessorDTO;
import gustavo.gomes.bibliotecaOnlineBackend.model.Professor;
import gustavo.gomes.bibliotecaOnlineBackend.model.TipoAtivo;
import gustavo.gomes.bibliotecaOnlineBackend.service.ProfessorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private HttpServletRequest request; // Injeta o HttpServletRequest para acessar a sessão

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessores() {
        // Verifica se o usuário está autenticado na sessão
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return ResponseEntity.status(401).build(); // Retorna 401 Unauthorized
        }

        List<Professor> professores = professorService.getAllProfessores();
        return ResponseEntity.ok(professores);
    }

    @PostMapping
    public ResponseEntity<?> createProfessor(@RequestBody ProfessorDTO professorDTO) {
        // Verifica se o usuário está autenticado na sessão
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return ResponseEntity.status(401).build(); // Retorna 401 Unauthorized
        }

        // Verifica se o usuarioProfessor (email) já existe
        Optional<Professor> existingProfessorByEmail = professorService.findByUsuarioProfessor(professorDTO.getUsuarioProfessor());
        if (existingProfessorByEmail.isPresent()) {
            return ResponseEntity.status(409).body(Map.of("message", "Já existe um professor cadastrado com este e-mail."));
        }

        // Verifica se o numeroDocumento (CPF/CNPJ) já existe
        Optional<Professor> existingProfessorByNumeroDocumento = professorService.findByNumeroDocumento(professorDTO.getNumeroDocumento());
        if (existingProfessorByNumeroDocumento.isPresent()) {
            return ResponseEntity.status(409).body(Map.of("message", "Já existe um professor cadastrado com este CPF ou CNPJ."));
        }

        // Cria um novo professor se o email e o CPF/CNPJ não existirem
        Professor professor = new Professor();
        professor.setUsuarioProfessor(professorDTO.getUsuarioProfessor());
        // Criptografa a senha antes de armazená-la
        String encodedPassword = passwordEncoder.encode(professorDTO.getSenhaProfessor());
        professor.setSenhaProfessor(encodedPassword);
        professor.setIsAdmin(professorDTO.isAdmin());
        professor.setNumeroDocumento(professorDTO.getNumeroDocumento());

        Optional<TipoAtivo> tipoAtivo = professorService.findTipoAtivoById(professorDTO.getIdAtivo());
        if (tipoAtivo.isPresent()) {
            professor.setTipoAtivo(tipoAtivo.get());
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Tipo de ativo inválido."));
        }

        professorService.save(professor);
        return ResponseEntity.created(URI.create("/professores/" + professor.getIdProfessor())).body(professor);
    }
}