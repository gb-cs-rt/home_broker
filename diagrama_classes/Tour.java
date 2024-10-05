import java.util.ArrayList;
import java.util.List;

public class Tour {

    private List<String> passos;     // Lista de passos do tutorial
    private boolean tourConcluido;  // Indica se o tutorial foi concluído

    // Construtor
    public Tour() {
        this.passos = new ArrayList<>();
        this.tourConcluido = false;
        carregarPassos();  // Carregar os passos do tutorial
    }

    // Método para carregar os passos do tutorial
    private void carregarPassos() {
        passos.add("Bem-vindo ao sistema! Vamos começar com um tour pelas principais funcionalidades.");
        passos.add("Passo 1: Cadastro e Login - Você pode se cadastrar e logar no sistema usando sua senha e biometria.");
        passos.add("Passo 2: Consultar Ações - Acompanhe as cotações de suas ações diretamente na sua carteira.");
        passos.add("Passo 3: Realizar Ordens de Compra/Venda - Simule e envie ordens de compra e venda de ações.");
        passos.add("Passo 4: Acompanhe o Histórico de Transações - Consulte seu histórico de compras e vendas no sistema.");
        passos.add("Passo 5: Configure Alertas - Defina alertas de cotação para ser notificado sobre variações.");
        passos.add("Passo 6: Segurança - Não se esqueça de usar a autenticação biométrica para garantir a segurança de sua conta.");
        passos.add("Parabéns! Você completou o tutorial. Agora você está pronto para usar o sistema.");
    }

    // Método para iniciar o tutorial
    public void iniciarTour(Usuario usuario) {
        if (tourConcluido) {
            System.out.println("O tutorial já foi concluído.");
        } else {
            System.out.println("Iniciando tutorial para o usuário: " + usuario.getNome());
            for (String passo : passos) {
                exibeProximoPasso();
            }
            concluirTour();
        }
    }

    // Método para concluir o tutorial
    private void concluirTour() {
        this.tourConcluido = true;
        System.out.println("Tutorial concluído com sucesso!");
    }

    // Método para verificar se o tutorial foi concluído
    public boolean isTourConcluido() {
        return tourConcluido;
    }

    // Método para reiniciar o tutorial
    public void reiniciarTour() {
        this.tourConcluido = false;
        System.out.println("Tutorial reiniciado.");
    }

    // Método para exibir os passos disponíveis no tutorial
    public void exibeProximoPasso() {
        System.out.println("Passos do Tutorial:");
        for (String passo : passos) {
            System.out.println(passo);
        }
    }
}