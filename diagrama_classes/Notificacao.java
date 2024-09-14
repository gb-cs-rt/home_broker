import java.time.LocalDateTime;

public class Notificacao {

    // Atributos
    private static long contadorId = 0; // Contador estático para gerar IDs únicos

    private final long id;                   // ID único da notificação
    private String tipo;                     // Tipo da notificação (ex: "ALERTA", "CONFIRMACAO", "BLOQUEIO")
    private String mensagem;                 // Mensagem da notificação
    private LocalDateTime dataHora;          // Data e hora em que a notificação foi gerada
    private boolean lida;                    // Indica se a notificação já foi lida pelo usuário

    // Construtor
    public Notificacao(String tipo, String mensagem, LocalDateTime dataHora) {
        this.id = ++contadorId;
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
        this.lida = false; // Inicialmente, a notificação não foi lida
    }

    // Getters e Setters

    public long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    // Método para marcar a notificação como lida
    public void marcarComoLida() {
        this.lida = true;
    }

    // Método toString
    @Override
    public String toString() {
        return "Notificacao [id=" + id + ", tipo=" + tipo + ", mensagem=" + mensagem +
                ", dataHora=" + dataHora + ", lida=" + lida + "]";
    }
}
