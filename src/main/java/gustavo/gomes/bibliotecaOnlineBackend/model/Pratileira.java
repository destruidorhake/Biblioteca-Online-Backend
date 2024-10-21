package gustavo.gomes.bibliotecaOnlineBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pratileiras")
@Getter
@Setter
public class Pratileira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pratileira")
    private Integer idPratileira;

    @Column(name = "nome_pratileira")
    private String nomePratileira;

    @ManyToOne
    @JoinColumn(name = "id_tipo_estado_pratileira")
    private TipoEstadoPratileira tipoEstadoPratileira;
}