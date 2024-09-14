import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Acao {

    // Atributos
    private String codigo;          // Código da ação, por exemplo, "PETR4"
    private String nome;            // Nome da empresa, por exemplo, "Petrobras PN"
    private double precoAtual;      // Preço atual da ação
    private double precoAbertura;   // Preço de abertura do dia
    private double precoFechamento; // Preço de fechamento do dia anterior
    private double precoMaximo;     // Preço máximo do dia
    private double precoMinimo;     // Preço mínimo do dia
    private int quantidade;         // Quantidade de ações que o usuário possui
    private double variacao;        // Variação percentual do dia
    private List<Double> historicoPrecos; // Lista de preços históricos

    // Construtor
    public Acao(String codigo, String nome, double precoAtual, double precoAbertura,
                double precoFechamento, double precoMaximo, double precoMinimo, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoAtual = precoAtual;
        this.precoAbertura = precoAbertura;
        this.precoFechamento = precoFechamento;
        this.precoMaximo = precoMaximo;
        this.precoMinimo = precoMinimo;
        this.quantidade = quantidade;
        this.variacao = calcularVariacao();
        this.historicoPrecos = new ArrayList<>();
    }

    // Getters e Setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(double precoAtual) {
        this.precoAtual = precoAtual;
        // Atualiza a variação sempre que o preço atual é alterado
        this.variacao = calcularVariacao();
    }

    public double getPrecoAbertura() {
        return precoAbertura;
    }

    public void setPrecoAbertura(double precoAbertura) {
        this.precoAbertura = precoAbertura;
    }

    public double getPrecoFechamento() {
        return precoFechamento;
    }

    public void setPrecoFechamento(double precoFechamento) {
        this.precoFechamento = precoFechamento;
    }

    public double getPrecoMaximo() {
        return precoMaximo;
    }

    public void setPrecoMaximo(double precoMaximo) {
        this.precoMaximo = precoMaximo;
    }

    public double getPrecoMinimo() {
        return precoMinimo;
    }

    public void setPrecoMinimo(double precoMinimo) {
        this.precoMinimo = precoMinimo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getVariacao() {
        return variacao;
    }

    public List<Double> getHistoricoPrecos() {
        return historicoPrecos;
    }

    public void setHistoricoPrecos(List<Double> historicoPrecos) {
        this.historicoPrecos = historicoPrecos;
    }

    // Métodos adicionais

    // Calcula a variação percentual com base no preço de fechamento anterior
    private double calcularVariacao() {
        if (precoFechamento != 0) {
            return ((precoAtual - precoFechamento) / precoFechamento) * 100;
        } else {
            return 0.0;
        }
    }

    // Adiciona um preço ao histórico
    public void adicionarPrecoHistorico(double preco) {
        this.historicoPrecos.add(preco);
    }

    // Método toString
    @Override
    public String toString() {
        return "Acao [codigo=" + codigo + ", nome=" + nome + ", precoAtual=" + precoAtual +
                ", quantidade=" + quantidade + ", variacao=" + String.format("%.2f", variacao) + "%]";
    }
}
