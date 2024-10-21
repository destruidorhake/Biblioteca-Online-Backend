package gustavo.gomes.bibliotecaOnlineBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo_estado_pratileira")
@Getter
@Setter
public class TipoEstadoPratileira {

    @Id
    @Column(name = "id_estado_pratileira")
    private Integer idEstadoPratileira;

    @Column(name = "descricao")
    private String descricao;
}