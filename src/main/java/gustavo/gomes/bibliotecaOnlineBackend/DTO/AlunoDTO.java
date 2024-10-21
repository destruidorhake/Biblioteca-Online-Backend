package gustavo.gomes.bibliotecaOnlineBackend.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlunoDTO {
    private int idAluno;
    private String nomeAluno;
    private int idade;
    private String senhaAluno;
    private String emailAluno;
    private int idAtivo;
    private int idEstadoPratileira;
}