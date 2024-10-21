package gustavo.gomes.bibliotecaOnlineBackend.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GeneroTextualDTO {
    private Integer id;
    private String tipo;


    @JsonCreator
    public GeneroTextualDTO(
            @JsonProperty("idTextual") Integer idTextual,
            @JsonProperty("itemTextual") String itemTextual) {
        this.id = idTextual;
        this.tipo = itemTextual;
    }

    // Construtor padrão
    public GeneroTextualDTO() {}
}