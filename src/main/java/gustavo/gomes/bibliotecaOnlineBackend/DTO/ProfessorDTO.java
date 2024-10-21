package gustavo.gomes.bibliotecaOnlineBackend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorDTO {
    private String usuarioProfessor;
    private String senhaProfessor;
    private boolean isAdmin;
    private int idAtivo;
    private String numeroDocumento; // CPF ou CNPJ
}
