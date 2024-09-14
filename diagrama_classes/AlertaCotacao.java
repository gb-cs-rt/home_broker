import java.time.LocalDateTime;

public class AlertaCotacao {

    // Atributos
    private static long contadorId = 0;   // Contador estático para gerar IDs únicos

    private long id;                      // ID único do alerta de cotação
    private Acao acao;                    // Ação associada ao alerta
    private double precoAlvo;             // Preço alvo definido para o alerta
    private boolean ativo;                // Indica se o alerta está ativo ou foi desativado
    private LocalDateTime dataCriacao;    // Data e hora da criação do alerta
    private String tipoNotificacao;       // Tipo de notificação: "AUMENTO", "QUEDA"

    // Construtor
    public AlertaCotacao(Acao acao, double precoAlvo, String tipoNotificacao) {
        this.id = ++contadorId;
        this.acao = acao;
        this.precoAlvo = precoAlvo;
        this.ativo = true;                // Por padrão, o alerta é ativo ao ser criado
        this.dataCriacao = LocalDateTime.now();
        this.tipoNotificacao = tipoNotificacao;
    }

    // Getters e Setters

    public long getId() {
        return id;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public double getPrecoAlvo() {
        return precoAlvo;
    }

    public void setPrecoAlvo(double precoAlvo) {
        this.precoAlvo = precoAlvo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    // Método para desativar o alerta
    public void desativarAlerta() {
        this.ativo = false;
    }

    // Método toString
    @Override
    public String toString() {
        return "AlertaCotacao [id=" + id + ", acao=" + acao.getCodigo() + ", precoAlvo=" + precoAlvo +
                ", ativo=" + ativo + ", dataCriacao=" + dataCriacao + ", tipoNotificacao=" + tipoNotificacao + "]";
    }
}
