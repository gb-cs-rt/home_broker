import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExtratoTransacoes {

    // Atributos
    private LocalDateTime dataInicio;            // Data de início do período do extrato
    private LocalDateTime dataFim;               // Data de fim do período do extrato
    private List<Transacao> transacoes;          // Lista de transações ocorridas no período
    private double valorTotalCompras;            // Valor total das transações de compra no período
    private double valorTotalVendas;             // Valor total das transações de venda no período

    // Construtor
    public ExtratoTransacoes(LocalDateTime dataInicio, LocalDateTime dataFim, List<Transacao> todasTransacoes) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.transacoes = filtrarTransacoesPorPeriodo(todasTransacoes, dataInicio, dataFim);
        this.valorTotalCompras = calcularValorTotalPorTipo("COMPRA");
        this.valorTotalVendas = calcularValorTotalPorTipo("VENDA");
    }

    // Getters e Setters

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public double getValorTotalCompras() {
        return valorTotalCompras;
    }

    public void setValorTotalCompras(double valorTotalCompras) {
        this.valorTotalCompras = valorTotalCompras;
    }

    public double getValorTotalVendas() {
        return valorTotalVendas;
    }

    public void setValorTotalVendas(double valorTotalVendas) {
        this.valorTotalVendas = valorTotalVendas;
    }

    // Métodos adicionais

    // Filtrar transações por período
    private List<Transacao> filtrarTransacoesPorPeriodo(List<Transacao> todasTransacoes, LocalDateTime inicio, LocalDateTime fim) {
        return todasTransacoes.stream()
                .filter(t -> !t.getDataHora().isBefore(inicio) && !t.getDataHora().isAfter(fim))
                .collect(Collectors.toList());
    }

    // Calcular o valor total das transações por tipo (COMPRA ou VENDA)
    private double calcularValorTotalPorTipo(String tipo) {
        return transacoes.stream()
                .filter(t -> t.getTipo().equalsIgnoreCase(tipo))
                .mapToDouble(t -> t.getQuantidade() * t.getPreco())
                .sum();
    }

    // Método para exibir o extrato detalhado
    public String exibirExtratoDetalhado() {
        StringBuilder sb = new StringBuilder();
        sb.append("Extrato de Transações\n");
        sb.append("Período: De " + dataInicio + " a " + dataFim + "\n\n");
        sb.append("Transações:\n");

        for (Transacao t : transacoes) {
            sb.append(t.toString()).append("\n");
        }

        sb.append("\nResumo:\n");
        sb.append("Valor Total de Compras: R$ " + String.format("%.2f", valorTotalCompras) + "\n");
        sb.append("Valor Total de Vendas: R$ " + String.format("%.2f", valorTotalVendas) + "\n");

        return sb.toString();
    }
}
