package gustavo.gomes.bibliotecaOnlineBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professores")
@Getter
@Setter
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Professor")
    private Integer idProfessor;

    @Column(name = "usuario_professor")
    private String usuarioProfessor;

    @Column(name = "senha_professor")
    private String senhaProfessor;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @ManyToOne
    @JoinColumn(name = "id_ativo")
    private TipoAtivo tipoAtivo;

    @Column(name = "numero_documento", unique = true, nullable = false)
    private String numeroDocumento;

    public String getUserType() {
        return isAdmin ? "admin" : "professor";
    }
}
