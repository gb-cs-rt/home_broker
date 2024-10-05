public class SistemaDeNotificacao {

    // Singleton instance (opcional, caso queira uma única instância do sistema)
    private static SistemaDeNotificacao instance;

    // Construtor privado para o padrão Singleton
    private SistemaDeNotificacao() {
        // Inicialização de recursos, se necessário
    }

    // Método para obter a instância única
    public static SistemaDeNotificacao getInstance() {
        if (instance == null) {
            instance = new SistemaDeNotificacao();
        }
        return instance;
    }

    // Método para enviar notificação por e-mail
    public void enviarEmail(Usuario usuario, Notificacao notificacao) {
        String email = usuario.getEmail();
        String assunto = "Notificação: " + notificacao.getTipo();
        String mensagem = notificacao.getMensagem();

        // Simular envio de e-mail
        System.out.println("Enviando e-mail para " + email);
        System.out.println("Assunto: " + assunto);
        System.out.println("Mensagem: " + mensagem);
        // Aqui você implementaria a integração com um serviço de e-mail real
    }

    // Método para enviar notificação por SMS
    public void enviarSMS(Usuario usuario, Notificacao notificacao) {
        String telefone = usuario.getTelefone();
        String mensagem = notificacao.getMensagem();

        // Simular envio de SMS
        System.out.println("Enviando SMS para " + telefone);
        System.out.println("Mensagem: " + mensagem);
        // Aqui você implementaria a integração com um serviço de SMS real
    }

    // Método genérico para enviar notificação (e-mail e SMS)
    public void enviarNotificacao(Usuario usuario, Notificacao notificacao) {
        // Enviar e-mail
        enviarEmail(usuario, notificacao);

        // Enviar SMS
        enviarSMS(usuario, notificacao);
    }

    public void atualizaAlertasDeCotacao() {
        ;
    }
    // Outros métodos podem ser adicionados conforme necessário
}
