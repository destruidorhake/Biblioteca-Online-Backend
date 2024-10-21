package gustavo.gomes.bibliotecaOnlineBackend.controller;

import gustavo.gomes.bibliotecaOnlineBackend.model.TipoEstadoPratileira;
import gustavo.gomes.bibliotecaOnlineBackend.service.TipoEstadoPratileiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoEstadoPratileira")
public class TipoEstadoPratileiraController {

    @Autowired
    private TipoEstadoPratileiraService tipoEstadoPratileiraService;

    @GetMapping
    public ResponseEntity<List<TipoEstadoPratileira>> getAllTipoEstadoPratileira() {
        if (!isUserAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna 401 Unauthorized
        }

        List<TipoEstadoPratileira> tiposEstado = tipoEstadoPratileiraService.getAllTipoEstadoPratileira();
        return ResponseEntity.ok(tiposEstado);
    }

    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }
}
