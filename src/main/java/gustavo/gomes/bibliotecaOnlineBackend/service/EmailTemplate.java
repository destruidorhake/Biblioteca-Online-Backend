package gustavo.gomes.bibliotecaOnlineBackend.service;

public class EmailTemplate {

    public static String generateResetPasswordEmail(String username, String newPassword) {
        return "<html>" +
                "<head>" +
                "<style>" +
                "    body {" +
                "        font-family: Arial, sans-serif;" +
                "        background-color: #f4f4f4;" +
                "        margin: 0;" +
                "        padding: 0;" +
                "    }" +
                "    .navbar {" +
                "        background-color: #ff4360;" +
                "        color: white;" +
                "        padding: 15px;" +
                "        text-align: center;" +
                "        font-size: 24px;" +
                "        font-weight: bold;" +
                "    }" +
                "    .container {" +
                "        width: 100%;" +
                "        max-width: 600px;" +
                "        margin: auto;" +
                "        background: #ffffff;" +
                "        border-radius: 8px;" +
                "        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);" +
                "        overflow: hidden;" +
                "        padding: 20px;" +
                "    }" +
                "    h1 {" +
                "        color: #ff4360;" +
                "    }" +
                "    p {" +
                "        line-height: 1.5;" +
                "    }" +
                "    .footer {" +
                "        margin-top: 20px;" +
                "        font-size: 0.9em;" +
                "        color: #777777;" +
                "        border-top: 1px solid #e0e0e0;" +
                "        padding-top: 10px;" +
                "    }" +
                "    .highlight {" +
                "        color: #ff4360;" +
                "        font-weight: bold;" +
                "        font-size: 1.2em;" +
                "    }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='navbar'>Biblioteca Online</div>" +  // Cabeçalho estilo navbar
                "<div class='container'>" +
                "<h1>Redefinição de Senha</h1>" +
                "<p>Olá, " + username + ",</p>" +
                "<p>Informamos que a sua senha foi redefinida com sucesso.</p>" +
                "<p>A nova senha é: <span class='highlight'>" + newPassword + "</span></p>" +
                "<p>Se você não solicitou essa alteração, por favor, entre em contato com o suporte.</p>" +
                "</div>" +
                "<div class='footer'>" +
                "<p>Este é um e-mail automático. Por favor, não responda.</p>" +
                "<p>Se você tiver dúvidas, entre em contato com o suporte da Biblioteca Online.</p>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
