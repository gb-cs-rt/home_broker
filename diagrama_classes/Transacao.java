import java.time.LocalDateTime;

public class Transacao {

    // Atributos
    private static long contadorId = 0; // Contador estático para gerar IDs únicos

    private long id;                        // ID único da transação
    private Usuario usuario;                // Usuário que realizou a transação
    private Acao acao;                      // Ação envolvida na transação
    private int quantidade;                 // Quantidade de ações transacionadas
    private double preco;                   // Preço por ação na transação
    private String tipo;                    // Tipo da transação: "COMPRA" ou "VENDA"
    private LocalDateTime dataHora;         // Data e hora em que a transação ocorreu

    // Construtor
    public Transacao(Usuario usuario, Acao acao, int quantidade, double preco, String tipo, LocalDateTime dataHora) {
        this.id = ++contadorId;
        this.usuario = usuario;
        this.acao = acao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.tipo = tipo;
        this.dataHora = dataHora;
    }

    // Getters e Setters

    public long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    // Método toString
    @Override
    public String toString() {
        return "Transacao [id=" + id + ", usuario=" + usuario.getNome() + ", acao=" + acao.getCodigo() +
                ", quantidade=" + quantidade + ", preco=" + preco + ", tipo=" + tipo +
                ", dataHora=" + dataHora + "]";
    }
}
