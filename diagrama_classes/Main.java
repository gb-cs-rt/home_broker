import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inicializar componentes do sistema
        APIBiometria apiBiometria = new APIBiometria();
        SistemaDeNotificacao sistemaDeNotificacao = SistemaDeNotificacao.getInstance();
        APIB3 apiB3 = new APIB3();
        ModuloAnaliseMercado moduloAnaliseMercado = new ModuloAnaliseMercado(apiB3);
        SistemaDeAutenticacao sistemaDeAutenticacao = new SistemaDeAutenticacao(apiBiometria, sistemaDeNotificacao);

        // Cadastrar um usuário no sistema e registrar biometria
        Usuario usuario = new Usuario("Gustavo Baggio", "baggio@example.com", "senha123", "Rua X", "11999999999");
        sistemaDeAutenticacao.registrarUsuario(usuario);

        // Iniciar tutorial para novos usuários
        Tour tour = new Tour();
        tour.iniciarTutorial(usuario);

        // Simular login com biometria
        boolean loginSucesso = sistemaDeAutenticacao.loginComBiometria(usuario.getEmail());

        if (loginSucesso) {
            // Adicionar ações à carteira do usuário
            Acao acao1 = new Acao("PETR4", "Petrobras", 29.50, 29.00, 29.20, 30.00, 28.00, 100);
            Acao acao2 = new Acao("VALE3", "Vale", 65.00, 64.00, 64.50, 66.00, 63.00, 50);
            usuario.getCarteira().adicionarAcao(acao1);
            usuario.getCarteira().adicionarAcao(acao2);

            System.out.println("\nAções adicionadas à carteira de " + usuario.getNome() + ":");
            usuario.getCarteira().exibirCarteira();

            // Simular a criação de uma ordem de compra
            OrdemCompra ordemCompra = new OrdemCompra(usuario, acao1, 50, 29.60, apiB3);
            ordemCompra.executarOrdem();

            // Simular a criação de uma ordem de venda
            OrdemVenda ordemVenda = new OrdemVenda(usuario, acao2, 30, 65.50, apiB3);
            ordemVenda.executarOrdem();

            // Gerar sugestões de compra e venda com o Módulo de Análise de Mercado
            System.out.println("\nSugestões de compra e venda de ações:");
            List<String> sugestoes = moduloAnaliseMercado.gerarSugestoes(usuario);
            sugestoes.forEach(System.out::println);

            // Consultar histórico de transações e gerar um extrato
            HistoricoTransacoes historicoTransacoes = usuario.getHistoricoTransacoes();
            LocalDateTime inicio = LocalDateTime.of(2023, 1, 1, 0, 0);
            LocalDateTime fim = LocalDateTime.of(2023, 12, 31, 23, 59);
            ExtratoTransacoes extrato = new ExtratoTransacoes(inicio, fim, historicoTransacoes.getTransacoes());

            System.out.println("\nExtrato de transações:");
            System.out.println(extrato.exibirExtratoDetalhado());

            // Configurar alertas de cotação
            AlertaCotacao alerta1 = new AlertaCotacao(acao1, 28.00, "QUEDA");
            usuario.adicionarAlertaCotacao(alerta1);
            System.out.println("\nAlertas de cotação configurados para " + usuario.getNome() + ":");
            usuario.getAlertasCotacao().forEach(alerta -> System.out.println(alerta));

            // Alterar informações de cadastro (necessário estar logado)
            sistemaDeAutenticacao.modificarCadastro(usuario.getEmail(), "Rua Y", "11988888888");

            // Enviar notificações para o usuário (via e-mail ou SMS)
            Notificacao notificacao = new Notificacao("ALERTA", "Sua ação PETR4 caiu abaixo de R$28.00", LocalDateTime.now());
            sistemaDeNotificacao.enviarNotificacao(usuario, notificacao);
        }

        // Realizar logout do usuário
        sistemaDeAutenticacao.logout(usuario.getEmail());
    }
}
