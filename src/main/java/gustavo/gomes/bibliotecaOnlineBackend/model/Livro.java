package gustavo.gomes.bibliotecaOnlineBackend.model;

import gustavo.gomes.bibliotecaOnlineBackend.DTO.LivroDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "livros")
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Integer idLivro;

    @Column(name = "nome_livro")
    private String nomeLivro;

    @Column(name = "sequencia")
    private Integer sequencia;

    @Column(name = "autor_livro")
    private String autorLivro;

    @Column(name = "quantidade_livro")
    private Integer quantidadeLivro;

    @ManyToOne
    @JoinColumn(name = "id_pratileira", nullable = false) // Define se é obrigatório ou não
    private Pratileira pratileira;

    @ManyToOne
    @JoinColumn(name = "id_linguistico")
    private GeneroLinguistico generoLinguistico;

    @ManyToOne
    @JoinColumn(name = "id_textual")
    private GeneroTextual generoTextual;

    public Livro(){}

    public Livro(LivroDTO livroDTO){
        nomeLivro=livroDTO.getNomeLivro();
        sequencia = livroDTO.getSequencia();
        autorLivro=livroDTO.getAutorLivro();
        quantidadeLivro=livroDTO.getQuantidadeLivro();
    }
}