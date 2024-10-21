package gustavo.gomes.bibliotecaOnlineBackend.controller;

import gustavo.gomes.bibliotecaOnlineBackend.DTO.LivroDTO;
import gustavo.gomes.bibliotecaOnlineBackend.config.erros.AlunoLivroException;
import gustavo.gomes.bibliotecaOnlineBackend.config.erros.PrateleiraException;
import gustavo.gomes.bibliotecaOnlineBackend.model.Livro;
import gustavo.gomes.bibliotecaOnlineBackend.service.GeneroService;
import gustavo.gomes.bibliotecaOnlineBackend.service.LivroService;
import gustavo.gomes.bibliotecaOnlineBackend.service.PratileiraService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private PratileiraService pratileiraService;

    @Autowired
    private HttpServletRequest request;

    private static final Logger logger = LoggerFactory.getLogger(LivroController.class);

    @GetMapping
    public ResponseEntity<List<Livro>> getAllLivros() {
        logUserSession();
        List<Livro> livros = livroService.getAllLivros();
        return ResponseEntity.ok(livros);
    }

    @PostMapping
    public ResponseEntity<?> saveLivro(@Valid @RequestBody LivroDTO livroDTO) {
        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuário não autenticado!"));
        }

        validatePratileiraId(livroDTO);

        Livro savedLivro = livroService.saveLivro(livroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLivro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLivro(@PathVariable Integer id) {
        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuário não autenticado!"));
        }

        livroService.deleteLivro(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/quantidade/{id}")
    public ResponseEntity<?> updateQuantidadeLivro(@PathVariable Integer id, @RequestBody LivroDTO livroDTO) {
        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuário não autenticado!"));
        }

        Livro updatedLivro = livroService.updateQuantidadeLivro(id, livroDTO);
        return ResponseEntity.ok(updatedLivro);
    }

    @PutMapping("/sequencia/{id}")
    public ResponseEntity<?> updateSequenciaLivro(@PathVariable Integer id, @RequestBody Integer novaSequencia) {
        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuário não autenticado!"));
        }

        Livro updatedLivro = livroService.updateSequenciaLivro(id, novaSequencia);
        return ResponseEntity.ok(updatedLivro);
    }

    @GetMapping("/sequencia/{id}")
    public ResponseEntity<Map<String, Object>> getSequenciaLivro(@PathVariable Integer id) {
        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Integer sequencia = livroService.getSequenciaLivro(id);
        Map<String, Object> response = new HashMap<>();
        response.put("idLivro", id);
        response.put("sequencia", sequencia);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(AlunoLivroException.class)
    public ResponseEntity<String> handleAlunoLivroException(AlunoLivroException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro de integridade de dados: " + ex.getMessage());
    }

    private void logUserSession() {
        // Log do usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            logger.info("Usuário autenticado: {}", authentication.getName());
        } else {
            logger.info("Nenhum usuário autenticado.");
        }
    }

    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    private void validatePratileiraId(LivroDTO livroDTO) {
        if (livroDTO.getPratileira() != null && livroDTO.getPratileira().getIdPratileira() <= 0) {
            throw new PrateleiraException("O campo idPratileira deve ser um valor positivo.");
        }
    }
}
