package gustavo.gomes.bibliotecaOnlineBackend.controller;

import gustavo.gomes.bibliotecaOnlineBackend.DTO.GeneroLinguisticoDTO;
import gustavo.gomes.bibliotecaOnlineBackend.DTO.GeneroTextualDTO;
import gustavo.gomes.bibliotecaOnlineBackend.service.GeneroService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping("/textuais")
    public List<GeneroTextualDTO> getGenerosTextuais() {
        return generoService.getGenerosTextuais();
    }

    @GetMapping("/linguisticos")
    public List<GeneroLinguisticoDTO> getGenerosLinguisticos() {
        return generoService.getGenerosLinguisticos();
    }
}