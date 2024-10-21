package gustavo.gomes.bibliotecaOnlineBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "aluno_livro")
@Getter
@Setter
public class AlunoLivro {

    @EmbeddedId
    private AlunoLivroId id;

    @ManyToOne
    @MapsId("idAluno")
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @MapsId("idLivro")
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @Column(name = "aluno_nome", nullable = false)
    private String alunoNome;

    @Column(name = "livro_nome", nullable = false)
    private String livroNome;

    @Column(name = "data_registro", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataRegistro;

    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    // Você pode adicionar um construtor padrão se precisar
    public AlunoLivro() {
        this.dataRegistro = LocalDateTime.now(); // Define a data de registro automaticamente
    }
}