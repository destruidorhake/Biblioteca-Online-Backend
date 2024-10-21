package gustavo.gomes.bibliotecaOnlineBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipo_ativo")
@Getter
@Setter
@NoArgsConstructor
public class TipoAtivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAtivo")
    private Integer idAtivo;

    @Column(name = "descricao")
    private String descricao;

    // Construtor com argumentos, se necessário
    public TipoAtivo(Integer idAtivo, String descricao) {
        this.idAtivo = idAtivo;
        this.descricao = descricao;
    }
}