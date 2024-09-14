import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    // Atributos
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private String telefone;
    private LocalDateTime dataUltimoLogin;
    private boolean bloqueado;
    private int tentativasLogin;
    private double saldoDisponivel;

    // Relacionamentos de composição
    private Carteira carteira;
    private HistoricoTransacoes historicoTransacoes;

    // Relacionamentos de agregação
    private List<Notificacao> notificacoes;
    private List<AlertaCotacao> alertasCotacao;

    // Construtor
    public Usuario(String nome, String email, String senha, String endereco, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataUltimoLogin = null;
        this.bloqueado = false;
        this.tentativasLogin = 0;
        this.carteira = new Carteira(this);
        this.historicoTransacoes = new HistoricoTransacoes();
        this.notificacoes = new ArrayList<>();
        this.alertasCotacao = new ArrayList<>();
        this.saldoDisponivel = 0.0; // Saldo inicial
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataUltimoLogin() {
        return dataUltimoLogin;
    }

    public void setDataUltimoLogin(LocalDateTime dataUltimoLogin) {
        this.dataUltimoLogin = dataUltimoLogin;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getTentativasLogin() {
        return tentativasLogin;
    }

    public void setTentativasLogin(int tentativasLogin) {
        this.tentativasLogin = tentativasLogin;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public HistoricoTransacoes getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    public void setHistoricoTransacoes(HistoricoTransacoes historicoTransacoes) {
        this.historicoTransacoes = historicoTransacoes;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public List<AlertaCotacao> getAlertasCotacao() {
        return alertasCotacao;
    }

    public void setAlertasCotacao(List<AlertaCotacao> alertasCotacao) {
        this.alertasCotacao = alertasCotacao;
    }

    // Métodos adicionais
    public void adicionarNotificacao(Notificacao notificacao) {
        this.notificacoes.add(notificacao);
    }

    public void removerNotificacao(Notificacao notificacao) {
        this.notificacoes.remove(notificacao);
    }

    public void adicionarAlertaCotacao(AlertaCotacao alerta) {
        this.alertasCotacao.add(alerta);
    }

    public void removerAlertaCotacao(AlertaCotacao alerta) {
        this.alertasCotacao.remove(alerta);
    }

    public void incrementarTentativasLogin() {
        this.tentativasLogin++;
        if (this.tentativasLogin >= 5) {
            this.bloqueado = true;
            // Chamar método para enviar alerta por SMS ou e-mail
        }
    }

    public void resetarTentativasLogin() {
        this.tentativasLogin = 0;
    }

    public void desbloquearUsuario() {
        this.bloqueado = false;
        this.tentativasLogin = 0;
    }

    // Getters e Setters do saldoDisponivel
    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(double saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    // Método para debitar saldo
    public void debitarSaldo(double valor) {
        if (this.saldoDisponivel >= valor) {
            this.saldoDisponivel -= valor;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    // Método para adicionar saldo (caso necessário)
    public void adicionarSaldo(double valor) {
        this.saldoDisponivel += valor;
    }

    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", email=" + email + ", endereco=" + endereco + ", telefone=" + telefone + "]";
    }
}