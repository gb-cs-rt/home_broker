import java.time.LocalDateTime;

public class OrdemVenda extends Ordem {

    // Constructor
    public OrdemVenda(Usuario usuario, Acao acao, int quantidade, double preco, APIB3 apiB3) {
        super(usuario, acao, quantidade, preco, apiB3);
        this.setTipoOrdem("VENDA");
    }

    // Specific methods for OrdemVenda

    // Verify if the user has enough shares to sell
    public boolean verificarQuantidadeDisponivel() {
        int quantidadePossuida = 0;
        // Find the action in the user's portfolio
        for (Acao acao : this.getUsuario().getCarteira().getAcoes()) {
            if (acao.getCodigo().equals(this.getAcao().getCodigo())) {
                quantidadePossuida = acao.getQuantidade();
                break;
            }
        }
        return quantidadePossuida >= this.getQuantidade();
    }

    // Update the user's balance after executing the order
    public void atualizarSaldoUsuario() {
        double totalVenda = this.getQuantidade() * this.getPreco();
        this.getUsuario().adicionarSaldo(totalVenda); // Method to credit the user's balance
    }

    // Update the user's portfolio after executing the order
    public void atualizarCarteiraUsuario() {
        Acao acaoNaCarteira = null;
        for (Acao acao : this.getUsuario().getCarteira().getAcoes()) {
            if (acao.getCodigo().equals(this.getAcao().getCodigo())) {
                acaoNaCarteira = acao;
                break;
            }
        }
        if (acaoNaCarteira != null) {
            // Decrease the quantity of shares
            int novaQuantidade = acaoNaCarteira.getQuantidade() - this.getQuantidade();
            if (novaQuantidade > 0) {
                acaoNaCarteira.setQuantidade(novaQuantidade);
            } else {
                // Remove the action from the portfolio if the quantity reaches zero
                this.getUsuario().getCarteira().removerAcao(acaoNaCarteira);
            }
        }
    }

    // Execute the sell order
    @Override
    public void executarOrdem() {
        if (verificarQuantidadeDisponivel()) {
            // Enviar a ordem para a API B3
            boolean sucessoEnvio = apiB3.enviarOrdem(this);

            if (sucessoEnvio) {
                // Simular resposta positiva da API B3
                this.setStatus("EXECUTADA");

                // Atualizar o saldo do usuário
                atualizarSaldoUsuario();

                // Atualizar a carteira do usuário
                atualizarCarteiraUsuario();

                // Registrar a transação no histórico
                Transacao transacao = new Transacao(
                        this.getUsuario(),
                        this.getAcao(),
                        this.getQuantidade(),
                        this.getPreco(),
                        "VENDA",
                        LocalDateTime.now()
                );
                this.getUsuario().getHistoricoTransacoes().adicionarTransacao(transacao);

                // Enviar notificação ao usuário
                Notificacao notificacao = new Notificacao(
                        "CONFIRMACAO",
                        "Sua ordem de venda foi executada com sucesso.",
                        LocalDateTime.now()
                );
                this.getUsuario().adicionarNotificacao(notificacao);

                System.out.println("Ordem de venda executada com sucesso.");
            } else {
                System.out.println("Falha ao enviar a ordem para a API B3.");
                this.setStatus("FALHA");
            }
        } else {
            System.out.println("Quantidade de ações insuficiente para executar a ordem de venda.");
            this.setStatus("FALHA");
        }
    }
}
