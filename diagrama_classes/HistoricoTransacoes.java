import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HistoricoTransacoes {

    // Atributos
    private final List<Transacao> transacoes;

    // Construtor
    public HistoricoTransacoes() {
        this.transacoes = new ArrayList<>();
    }

    // Métodos

    // Adicionar uma transação ao histórico
    public void adicionarTransacao(Transacao transacao) {
        this.transacoes.add(transacao);
    }

    // Obter todas as transações
    public List<Transacao> getTransacoes() {
        return Collections.unmodifiableList(transacoes);
    }

    // Filtrar transações por tipo (COMPRA ou VENDA)
    public List<Transacao> filtrarPorTipo(String tipo) {
        return transacoes.stream()
                .filter(t -> t.getTipo().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }

    // Filtrar transações por código de ação
    public List<Transacao> filtrarPorAcao(String codigoAcao) {
        return transacoes.stream()
                .filter(t -> t.getAcao().getCodigo().equalsIgnoreCase(codigoAcao))
                .collect(Collectors.toList());
    }

    // Filtrar transações por intervalo de datas
    public List<Transacao> filtrarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return transacoes.stream()
                .filter(t -> !t.getDataHora().isBefore(inicio) && !t.getDataHora().isAfter(fim))
                .collect(Collectors.toList());
    }

    // Obter o número total de transações
    public int getTotalTransacoes() {
        return transacoes.size();
    }

    // Obter o valor total das transações (compra e venda separadamente)
    public double getValorTotalPorTipo(String tipo) {
        return transacoes.stream()
                .filter(t -> t.getTipo().equalsIgnoreCase(tipo))
                .mapToDouble(t -> t.getQuantidade() * t.getPreco())
                .sum();
    }

    // Método toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Histórico de Transações:\n");
        for (Transacao t : transacoes) {
            sb.append(t.toString()).append("\n");
        }
        return sb.toString();
    }
}
