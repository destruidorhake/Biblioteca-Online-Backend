package gustavo.gomes.bibliotecaOnlineBackend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LivroDTO {
    private Integer idLivro; // ID do livro, deve ser o primeiro, pois é um identificador

    @NotBlank(message = "O nome do livro é obrigatório")
    private String nomeLivro;

    private Integer sequencia;

    @NotBlank(message = "O autor do livro é obrigatório")
    private String autorLivro;

    @NotNull(message = "A quantidade do livro é obrigatória")
    private Integer quantidadeLivro; // Adicione esta propriedade, pois ela não estava no seu DTO

    @NotNull(message = "A prateleira é obrigatória")
    private PratileiraDTO pratileira; // Remover a duplicação

    @NotNull(message = "O gênero linguístico é obrigatório")
    private GeneroLinguisticoDTO generoLinguistico;

    @NotNull(message = "O gênero textual é obrigatório")
    private GeneroTextualDTO generoTextual;
}