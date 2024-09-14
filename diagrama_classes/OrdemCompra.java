import java.time.LocalDateTime;

public class OrdemCompra extends Ordem {

    // Construtor
    public OrdemCompra(Usuario usuario, Acao acao, int quantidade, double preco, APIB3 apiB3) {
        super(usuario, acao, quantidade, preco, apiB3);
        this.setTipoOrdem("COMPRA");
    }

    // Métodos específicos para OrdemCompra

    // Verificar se o usuário possui saldo suficiente para a compra
    public boolean verificarSaldoDisponivel() {
        double totalCompra = this.getQuantidade() * this.getPreco();
        double saldoDisponivel = this.getUsuario().getSaldoDisponivel();
        return saldoDisponivel >= totalCompra;
    }

    // Atualizar o saldo do usuário após a execução da ordem
    public void atualizarSaldoUsuario() {
        double totalCompra = this.getQuantidade() * this.getPreco();
        this.getUsuario().debitarSaldo(totalCompra);
    }

    // Atualizar a carteira do usuário após a execução da ordem
    public void atualizarCarteiraUsuario() {
        // Verifica se a ação já existe na carteira
        Acao acaoNaCarteira = null;
        for (Acao acao : this.getUsuario().getCarteira().getAcoes()) {
            if (acao.getCodigo().equals(this.getAcao().getCodigo())) {
                acaoNaCarteira = acao;
                break;
            }
        }
        if (acaoNaCarteira != null) {
            // Atualiza a quantidade existente
            acaoNaCarteira.setQuantidade(acaoNaCarteira.getQuantidade() + this.getQuantidade());
        } else {
            // Adiciona nova ação à carteira
            Acao novaAcao = new Acao(
                    this.getAcao().getCodigo(),
                    this.getAcao().getNome(),
                    this.getAcao().getPrecoAtual(),
                    this.getAcao().getPrecoAbertura(),
                    this.getAcao().getPrecoFechamento(),
                    this.getAcao().getPrecoMaximo(),
                    this.getAcao().getPrecoMinimo(),
                    this.getQuantidade()
            );
            this.getUsuario().getCarteira().adicionarAcao(novaAcao);
        }
    }

    // Executar a ordem de compra
    @Override
    public void executarOrdem() {
        if (verificarSaldoDisponivel()) {
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
                        "COMPRA",
                        LocalDateTime.now()
                );
                this.getUsuario().getHistoricoTransacoes().adicionarTransacao(transacao);

                // Enviar notificação ao usuário
                Notificacao notificacao = new Notificacao(
                        "CONFIRMACAO",
                        "Sua ordem de compra foi executada com sucesso.",
                        LocalDateTime.now()
                );
                this.getUsuario().adicionarNotificacao(notificacao);

                System.out.println("Ordem de compra executada com sucesso.");
            } else {
                System.out.println("Falha ao enviar a ordem para a API B3.");
                this.setStatus("FALHA");
            }
        } else {
            System.out.println("Saldo insuficiente para executar a ordem de compra.");
            this.setStatus("FALHA");
        }
    }
}
