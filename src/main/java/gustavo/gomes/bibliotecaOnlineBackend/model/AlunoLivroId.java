package gustavo.gomes.bibliotecaOnlineBackend.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AlunoLivroId implements Serializable {

    private Integer idAluno;
    private Integer idLivro;

    // Construtor padrão
    public AlunoLivroId() {}

    public AlunoLivroId(Integer idAluno, Integer idLivro) {
        this.idAluno = idAluno;
        this.idLivro = idLivro;
    }

    // Getters e Setters
    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlunoLivroId)) return false;
        AlunoLivroId that = (AlunoLivroId) o;
        return Objects.equals(idAluno, that.idAluno) &&
                Objects.equals(idLivro, that.idLivro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAluno, idLivro);
    }
}