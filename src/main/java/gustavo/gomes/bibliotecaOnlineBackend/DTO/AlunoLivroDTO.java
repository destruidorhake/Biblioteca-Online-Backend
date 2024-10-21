package gustavo.gomes.bibliotecaOnlineBackend.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
public class AlunoLivroDTO {
    private Integer alunoId;
    private String alunoNome;
    private Integer livroId;
    private String livroNome;
    private LocalDate dataRegistro;
    private LocalDateTime dataSaida; // Pode ser nulo
}
