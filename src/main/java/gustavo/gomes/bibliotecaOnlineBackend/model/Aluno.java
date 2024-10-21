package gustavo.gomes.bibliotecaOnlineBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "alunos")
@Getter
@Setter
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Aluno")
    private Integer idAluno;

    @Column(name = "nome_Aluno")
    private String nomeAluno;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "senha_Aluno")
    private String senhaAluno;

    @Column(name = "email_Aluno")
    private String emailAluno;

    @ManyToOne
    @JoinColumn(name = "id_Ativo")
    private TipoAtivo tipoAtivo;

    @ManyToOne
    @JoinColumn(name = "id_estado_pratileira")
    private TipoEstadoPratileira tipoEstadoPratileira;
}