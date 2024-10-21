package gustavo.gomes.bibliotecaOnlineBackend.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PratileiraDTO {
    private Integer idPratileira;
    private String nomePratileira;
    private TipoEstadoPratileiraDTO tipoEstadoPratileira;
}
