import java.util.ArrayList;
import java.util.List;

public class Grupo {

    // Atributos
    private String nome;                // Nome do grupo (ex: "Setor Financeiro", "Tecnologia")
    private String criterio;            // Critério de agrupamento (ex: "Setor", "Tipo", "Personalizado")
    private List<Acao> acoes;           // Lista de ações pertencentes ao grupo

    // Construtor
    public Grupo(String nome, String criterio) {
        this.nome = nome;
        this.criterio = criterio;
        this.acoes = new ArrayList<>();
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public List<Acao> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<Acao> acoes) {
        this.acoes = acoes;
    }

    // Métodos adicionais

    // Adicionar uma ação ao grupo
    public void adicionarAcao(Acao acao) {
        if (!acoes.contains(acao)) {
            this.acoes.add(acao);
        }
    }

    // Remover uma ação do grupo
    public void removerAcao(Acao acao) {
        this.acoes.remove(acao);
    }

    // Verificar se o grupo contém uma ação específica
    public boolean contemAcao(Acao acao) {
        return this.acoes.contains(acao);
    }

    // Método toString
    @Override
    public String toString() {
        return "Grupo [nome=" + nome + ", criterio=" + criterio + ", acoes=" + acoes + "]";
    }
}
