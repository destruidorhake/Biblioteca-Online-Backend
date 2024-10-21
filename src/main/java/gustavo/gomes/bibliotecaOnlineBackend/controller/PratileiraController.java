package gustavo.gomes.bibliotecaOnlineBackend.controller;

import gustavo.gomes.bibliotecaOnlineBackend.DTO.PratileiraDTO;
import gustavo.gomes.bibliotecaOnlineBackend.model.Pratileira;
import gustavo.gomes.bibliotecaOnlineBackend.service.PratileiraService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pratileiras")
public class PratileiraController {

    @Autowired
    private PratileiraService pratileiraService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<PratileiraDTO>> getAllPratileiras() {
        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna 401 Unauthorized
        }

        try {
            List<PratileiraDTO> pratileiras = pratileiraService.getAllPratileiras();
            if (pratileiras.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pratileiras, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pratileira> getPratileiraById(@PathVariable Integer id) {
        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna 401 Unauthorized
        }

        Optional<Pratileira> pratileira = pratileiraService.findById(id);
        if (pratileira.isPresent()) {
            return new ResponseEntity<>(pratileira.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PratileiraDTO> createPratileira(@RequestBody PratileiraDTO pratileiraDTO) {
        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna 401 Unauthorized
        }

        try {
            PratileiraDTO savedPratileira = pratileiraService.save(pratileiraDTO);
            return new ResponseEntity<>(savedPratileira, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePratileira(@PathVariable Integer id) {
        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna 401 Unauthorized
        }

        try {
            pratileiraService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }
}
