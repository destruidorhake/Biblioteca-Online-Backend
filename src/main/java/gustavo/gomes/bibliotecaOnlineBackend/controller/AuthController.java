package gustavo.gomes.bibliotecaOnlineBackend.controller;

import gustavo.gomes.bibliotecaOnlineBackend.config.util.JwtUtil;
import gustavo.gomes.bibliotecaOnlineBackend.model.Aluno;
import gustavo.gomes.bibliotecaOnlineBackend.model.Professor;
import gustavo.gomes.bibliotecaOnlineBackend.service.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie; // Import necessário para usar Cookies

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;  // Para gerar e validar tokens JWT

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Professor professor) {
        Optional<Professor> optionalProfessor = professorService.findByUsuarioProfessor(professor.getUsuarioProfessor());

        if (optionalProfessor.isPresent()) {
            Professor existingProfessor = optionalProfessor.get();

            // Verificação da senha
            if (passwordEncoder.matches(professor.getSenhaProfessor(), existingProfessor.getSenhaProfessor())) {
                // Criar um token de autenticação
                UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken(professor.getUsuarioProfessor(), professor.getSenhaProfessor());

                // Autenticar o usuário
                Authentication authentication = authenticationManager.authenticate(authRequest);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Gera o token JWT incluindo userType
                String jwtToken = jwtUtil.generateToken(existingProfessor.getUsuarioProfessor(), existingProfessor.getUserType());

                // Retorna o token na resposta
                return ResponseEntity.ok().body(Map.of(
                        "message", "Login bem-sucedido!",
                        "token", jwtToken,
                        "userType", existingProfessor.getUserType()
                ));
            } else {
                return ResponseEntity.status(401).body(Map.of("message", "Credenciais inválidas!"));
            }
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Credenciais inválidas!"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Não precisa invalidar o token JWT no servidor, pois ele é stateless (não há sessão para invalidar)
        return ResponseEntity.ok(Map.of("message", "Logout bem-sucedido!"));
    }

    @PostMapping("/login-aluno")
    public ResponseEntity<?> loginAluno(@RequestBody Aluno aluno) {
        Optional<Aluno> optionalAluno = alunoService.findByNomeAluno(aluno.getNomeAluno());

        if (optionalAluno.isPresent()) {
            return ResponseEntity.ok().body(Map.of("message", "Login bem-sucedido!"));
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Credenciais inválidas!"));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String usuarioProfessor, @RequestParam String numeroDocumento) {
        Optional<Professor> optionalProfessor = professorService.findByUsuarioProfessor(usuarioProfessor);

        if (optionalProfessor.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "E-mail não encontrado!"));
        }

        Professor professor = optionalProfessor.get();
        if (!professor.getNumeroDocumento().equals(numeroDocumento)) {
            return ResponseEntity.status(404).body(Map.of("message", "Número de documento não encontrado!"));
        }

        String newPassword = PasswordUtilsService.generateRandomPassword();
        String encryptedPassword = passwordEncoder.encode(newPassword);
        professor.setSenhaProfessor(encryptedPassword);
        professorService.save(professor);

        // Gera o conteúdo do e-mail usando a nova classe
        String emailContent = EmailTemplate.generateResetPasswordEmail(professor.getUsuarioProfessor(), newPassword);

        try {
            emailSenderService.sendHtmlEmail(professor.getUsuarioProfessor(), "Sua senha foi redefinida", emailContent);
            return ResponseEntity.ok().body(Map.of("message", "Senha alterada com sucesso!"));
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao enviar o e-mail."));
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(Map.of("message", "Teste bem-sucedido!"));
    }

    private Cookie createSessionCookie(HttpServletRequest request) {
        Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);  // Define o cookie como seguro para HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1 hora
        return cookie;
    }

    private Cookie createExpiredSessionCookie() {
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Remove o cookie
        return cookie;
    }
}
