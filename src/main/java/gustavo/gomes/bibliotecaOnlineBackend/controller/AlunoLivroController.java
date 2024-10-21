package gustavo.gomes.bibliotecaOnlineBackend.controller;

import gustavo.gomes.bibliotecaOnlineBackend.DTO.AlunoLivroDTO;
import gustavo.gomes.bibliotecaOnlineBackend.config.util.JwtUtil;
import gustavo.gomes.bibliotecaOnlineBackend.model.AlunoLivro;
import gustavo.gomes.bibliotecaOnlineBackend.service.AlunoLivroService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunoLivros")
public class AlunoLivroController {

    @Autowired
    private AlunoLivroService alunoLivroService;

    @GetMapping
    public ResponseEntity<List<AlunoLivro>> getAllAlunoLivros() {
        List<AlunoLivro> alunoLivros = alunoLivroService.getAllAlunoLivros();
        return ResponseEntity.ok(alunoLivros);
    }

    @PostMapping
    public ResponseEntity<AlunoLivro> atribuirLivro(@RequestBody AlunoLivroDTO alunoLivroDTO) {
        AlunoLivro alunoLivro = alunoLivroService.saveAlunoLivro(alunoLivroDTO);
        return new ResponseEntity<>(alunoLivro, HttpStatus.CREATED);
    }

    @DeleteMapping("/desatribuir/{alunoId}/{livroId}")
    public ResponseEntity<Void> desatribuirLivro(@PathVariable Integer alunoId, @PathVariable Integer livroId) {
        alunoLivroService.desatribuirLivro(alunoId, livroId);
        return ResponseEntity.noContent().build();
    }
}