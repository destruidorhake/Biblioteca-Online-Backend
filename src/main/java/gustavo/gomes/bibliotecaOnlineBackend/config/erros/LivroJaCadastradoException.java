package gustavo.gomes.bibliotecaOnlineBackend.config.erros;

public class LivroJaCadastradoException extends RuntimeException {
    public LivroJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}
