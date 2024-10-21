package gustavo.gomes.bibliotecaOnlineBackend.controller;

import gustavo.gomes.bibliotecaOnlineBackend.model.TipoAtivo;
import gustavo.gomes.bibliotecaOnlineBackend.service.TipoAtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoAtivo")
public class TipoAtivoController {

    @Autowired
    private TipoAtivoService tipoAtivoService;

    @GetMapping
    public ResponseEntity<List<TipoAtivo>> getAllTipoAtivo() {
        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna 401 Unauthorized
        }

        List<TipoAtivo> tipoAtivos = tipoAtivoService.getAllTipoAtivo();
        return ResponseEntity.ok(tipoAtivos);
    }

    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }
}
