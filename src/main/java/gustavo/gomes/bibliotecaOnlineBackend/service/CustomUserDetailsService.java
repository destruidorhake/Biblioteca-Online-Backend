package gustavo.gomes.bibliotecaOnlineBackend.service;
import gustavo.gomes.bibliotecaOnlineBackend.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfessorService professorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Professor> optionalProfessor = professorService.findByUsuarioProfessor(username);

        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            // Aqui você pode construir e retornar um UserDetails a partir do professor
            return org.springframework.security.core.userdetails.User.builder()
                    .username(professor.getUsuarioProfessor())
                    .password(professor.getSenhaProfessor())
                    .roles("PROFESSOR") // ou outras roles que você definir
                    .build();
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
}