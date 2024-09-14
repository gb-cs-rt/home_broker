import java.util.ArrayList;
import java.util.List;

public class Carteira {

    // Atributos

    // Referência ao usuário dono da carteira
    private Usuario usuario;

    // Lista de ações que o usuário possui na carteira
    private List<Acao> acoes;

    // Lista de grupos personalizados criados pelo usuário
    private List<Grupo> grupos;

    // Construtor

    public Carteira(Usuario usuario) {
        this.usuario = usuario;
        this.acoes = new ArrayList<>();
        this.grupos = new ArrayList<>();
    }

    // Getters e Setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Acao> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<Acao> acoes) {
        this.acoes = acoes;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    // Métodos adicionais

    // Adicionar uma ação à carteira
    public void adicionarAcao(Acao acao) {
        this.acoes.add(acao);
    }

    // Remover uma ação da carteira
    public void removerAcao(Acao acao) {
        this.acoes.remove(acao);
    }

    // Adicionar um grupo à carteira
    public void adicionarGrupo(Grupo grupo) {
        this.grupos.add(grupo);
    }

    // Remover um grupo da carteira
    public void removerGrupo(Grupo grupo) {
        this.grupos.remove(grupo);
    }

    // Calcular o valor total da carteira
    public double calcularValorTotal() {
        double total = 0.0;
        for (Acao acao : acoes) {
            total += acao.getQuantidade() * acao.getPrecoAtual();
        }
        return total;
    }

    // Obter as maiores altas na carteira
    public List<Acao> obterMaioresAltas() {
        // Implementar lógica para retornar as ações com maiores altas
        // Exemplo simplificado:
        List<Acao> maioresAltas = new ArrayList<>();
        double maiorVariacao = Double.NEGATIVE_INFINITY;
        for (Acao acao : acoes) {
            if (acao.getVariacao() > maiorVariacao) {
                maioresAltas.clear();
                maioresAltas.add(acao);
                maiorVariacao = acao.getVariacao();
            } else if (acao.getVariacao() == maiorVariacao) {
                maioresAltas.add(acao);
            }
        }
        return maioresAltas;
    }

    // Obter as maiores baixas na carteira
    public List<Acao> obterMaioresBaixas() {
        // Implementar lógica para retornar as ações com maiores baixas
        // Exemplo simplificado:
        List<Acao> maioresBaixas = new ArrayList<>();
        double menorVariacao = Double.POSITIVE_INFINITY;
        for (Acao acao : acoes) {
            if (acao.getVariacao() < menorVariacao) {
                maioresBaixas.clear();
                maioresBaixas.add(acao);
                menorVariacao = acao.getVariacao();
            } else if (acao.getVariacao() == menorVariacao) {
                maioresBaixas.add(acao);
            }
        }
        return maioresBaixas;
    }

    // Método para exibir todas as ações na carteira do usuário
    public void exibirCarteira() {
        if (acoes.isEmpty()) {
            System.out.println("A carteira está vazia.");
        } else {
            System.out.println("Carteira de Ações:");
            for (Acao acao : acoes) {
                System.out.println("Código: " + acao.getCodigo() +
                        ", Quantidade: " + acao.getQuantidade() + ", Preço Atual: R$ " + acao.getPrecoAtual());
            }
        }
    }

    // Método toString
    @Override
    public String toString() {
        return "Carteira [usuario=" + usuario.getNome() + ", acoes=" + acoes + ", grupos=" + grupos + "]";
    }
}
