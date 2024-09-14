import java.util.ArrayList;
import java.util.List;

public class ModuloAnaliseMercado {

    private APIB3 apiB3;  // Associação com a API da B3 para obter cotações e dados de mercado

    // Construtor
    public ModuloAnaliseMercado(APIB3 apiB3) {
        this.apiB3 = apiB3;
    }

    // Método para gerar sugestões de compra e venda de ações
    public List<String> gerarSugestoes(Usuario usuario) {
        List<String> sugestoes = new ArrayList<>();

        // Obter a carteira do usuário
        List<Acao> acoesNaCarteira = usuario.getCarteira().getAcoes();

        // Verificar cada ação da carteira do usuário
        for (Acao acao : acoesNaCarteira) {
            double precoAtual = apiB3.obterCotacaoAcao(acao.getCodigo());
            double precoCompra = acao.getPrecoAtual();  // Preço de compra da ação ou preço que ela foi adquirida
            double variacao = calcularVariacao(precoAtual, precoCompra);

            // Gerar sugestão de compra ou venda com base na variação
            if (variacao >= 10.0) {
                // Sugestão de venda se a ação valorizou 10% ou mais
                sugestoes.add("Sugestão de VENDA: Ação " + acao.getCodigo() + " valorizou " + String.format("%.2f", variacao) + "%.");
            } else if (variacao <= -10.0) {
                // Sugestão de compra se a ação desvalorizou 10% ou mais
                sugestoes.add("Sugestão de COMPRA: Ação " + acao.getCodigo() + " desvalorizou " + String.format("%.2f", variacao) + "%.");
            }
        }

        // Se não houver sugestões, fornecer um aviso
        if (sugestoes.isEmpty()) {
            sugestoes.add("Nenhuma sugestão de compra ou venda no momento.");
        }

        return sugestoes;
    }

    // Método auxiliar para calcular a variação percentual entre dois preços
    private double calcularVariacao(double precoAtual, double precoCompra) {
        return ((precoAtual - precoCompra) / precoCompra) * 100.0;
    }

    // Métodos adicionais podem ser implementados para critérios mais complexos de análise
}
