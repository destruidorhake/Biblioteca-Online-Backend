package gustavo.gomes.bibliotecaOnlineBackend.controller;

import gustavo.gomes.bibliotecaOnlineBackend.model.Aluno;
import gustavo.gomes.bibliotecaOnlineBackend.service.AlunoService;
import gustavo.gomes.bibliotecaOnlineBackend.service.TipoAtivoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private TipoAtivoService tipoAtivoService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> alunos = alunoService.getAllAlunos();
        return ResponseEntity.ok(alunos);
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno) {
        Aluno savedAluno = alunoService.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        try {
            Optional<Aluno> aluno = alunoService.findById(id);
            if (aluno.isPresent()) {
                alunoService.deleteById(id);
                return ResponseEntity.noContent().build(); // 204
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar excluir o aluno.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable int id) {
        Optional<Aluno> aluno = alunoService.findById(id);
        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}