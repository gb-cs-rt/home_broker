import java.time.LocalDateTime;

public abstract class Ordem {

    // Atributos

    private static long contadorId = 0; // Contador estático para gerar IDs únicos

    private long id;                        // ID único da ordem
    private Usuario usuario;                // Usuário que realizou a ordem
    private Acao acao;                      // Ação relacionada à ordem
    private int quantidade;                 // Quantidade de ações na ordem
    private double preco;                   // Preço da ordem (por ação)
    private LocalDateTime dataCriacao;      // Data e hora em que a ordem foi criada
    private String tipoOrdem;               // Tipo de ordem: "COMPRA" ou "VENDA"
    private String status;                  // Status da ordem: "PENDENTE", "EXECUTADA", "CANCELADA"
    private String tipoExecucao;            // Tipo de execução: "A MERCADO", "LIMITADA", "STOP", etc.

    // Referência à APIB3 para executar ações
    protected APIB3 apiB3;

    // Construtor

    public Ordem(Usuario usuario, Acao acao, int quantidade, double preco, APIB3 apiB3) {
        this.id = ++contadorId;
        this.usuario = usuario;
        this.acao = acao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.dataCriacao = LocalDateTime.now();
        this.status = "PENDENTE";
        this.tipoExecucao = tipoExecucao;
        this.apiB3 = apiB3;
        // O atributo 'tipoOrdem' será definido nas subclasses 'OrdemCompra' e 'OrdemVenda'
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getTipoOrdem() {
        return tipoOrdem;
    }

    protected void setTipoOrdem(String tipoOrdem) {
        this.tipoOrdem = tipoOrdem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoExecucao() {
        return tipoExecucao;
    }

    public void setTipoExecucao(String tipoExecucao) {
        this.tipoExecucao = tipoExecucao;
    }

    // Métodos adicionais

    // Método abstrato para executar a ordem
    public abstract void executarOrdem();

    // Método para cancelar a ordem
    public void cancelarOrdem() {
        if (this.status.equals("PENDENTE")) {
            boolean sucesso = apiB3.cancelarOrdem(this);
            if (sucesso) {
                this.status = "CANCELADA";
                System.out.println("Ordem cancelada com sucesso.");
            } else {
                System.out.println("Falha ao cancelar a ordem na API B3.");
            }
        } else {
            System.out.println("Não é possível cancelar uma ordem com status: " + this.status);
        }
    }

    // Método para modificar a ordem
    public void modificarOrdem(int novaQuantidade, double novoPreco, String novoTipoExecucao) {
        if (this.status.equals("PENDENTE")) {
            // Atualiza os parâmetros da ordem
            this.quantidade = novaQuantidade;
            this.preco = novoPreco;
            this.tipoExecucao = novoTipoExecucao;
            boolean sucesso = apiB3.modificarOrdem(this);
            if (sucesso) {
                System.out.println("Ordem modificada com sucesso.");
            } else {
                System.out.println("Falha ao modificar a ordem na API B3.");
            }
        } else {
            System.out.println("Não é possível modificar uma ordem com status: " + this.status);
        }
    }

    // Método toString
    @Override
    public String toString() {
        return "Ordem [id=" + id + ", usuario=" + usuario.getNome() + ", acao=" + acao.getCodigo() +
                ", quantidade=" + quantidade + ", preco=" + preco + ", dataCriacao=" + dataCriacao +
                ", tipoOrdem=" + tipoOrdem + ", status=" + status + ", tipoExecucao=" + tipoExecucao + "]";
    }
}
