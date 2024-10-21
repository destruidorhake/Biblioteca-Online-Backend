package gustavo.gomes.bibliotecaOnlineBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "genero_linguistico")
@Getter
@Setter
public class GeneroLinguistico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idLinguistico;

    @Column(name = "tipo")
    private String itemLinguistico;
}