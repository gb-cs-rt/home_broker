import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

public class SistemaDeAutenticacao {

    private Map<String, Usuario> usuarios;              // Mapa de usuários cadastrados (chave: e-mail)
    private APIBiometria apiBiometria;                  // Associação com a API de Biometria
    private SistemaDeNotificacao sistemaDeNotificacao;  // Associação com o Sistema de Notificação
    private Map<String, Boolean> usuariosLogados;       // Mapa para verificar se o usuário está logado (chave: e-mail)

    // Construtor
    public SistemaDeAutenticacao(APIBiometria apiBiometria, SistemaDeNotificacao sistemaDeNotificacao) {
        this.usuarios = new HashMap<>();
        this.apiBiometria = apiBiometria;
        this.sistemaDeNotificacao = sistemaDeNotificacao;
        this.usuariosLogados = new HashMap<>();  // Inicializa o mapa de usuários logados
    }

    // Método para registrar um novo usuário
    public void registrarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getEmail())) {
            System.out.println("O e-mail já está cadastrado.");
        } else {
            usuarios.put(usuario.getEmail(), usuario);
            System.out.println("Usuário registrado com sucesso.");
            apiBiometria.registrarBiometria(usuario);  // Registra a biometria do usuário
        }
    }

    // Método para login com biometria (obrigatório para autenticação)
    public boolean loginComBiometria(String email) {
        Usuario usuario = usuarios.get(email);

        if (usuario != null) {
            boolean biometriaValida = apiBiometria.verificarBiometria(usuario);
            if (biometriaValida) {
                System.out.println("Login biométrico realizado com sucesso.");
                usuariosLogados.put(email, true);  // Marca o usuário como logado
                return true;
            } else {
                System.out.println("Falha na autenticação biométrica.");
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
        return false;
    }

    // Método para logout
    public void logout(String email) {
        if (usuariosLogados.containsKey(email) && usuariosLogados.get(email)) {
            usuariosLogados.put(email, false);  // Marca o usuário como deslogado
            System.out.println("Usuário deslogado com sucesso.");
        } else {
            System.out.println("Usuário não está logado.");
        }
    }

    // Método para recuperação de senha (necessita identificação biométrica)
    public void recuperarSenha(String email) {
        Usuario usuario = usuarios.get(email);

        if (usuario != null) {
            if (loginComBiometria(email)) {  // Necessita de biometria
                String novaSenha = gerarNovaSenha();
                usuario.setSenha(novaSenha);  // Atualiza a senha do usuário

                // Enviar notificação de recuperação de senha
                Notificacao notificacao = new Notificacao(
                        "RECUPERACAO",
                        "Sua senha foi redefinida. Sua nova senha é: " + novaSenha,
                        LocalDateTime.now()
                );
                sistemaDeNotificacao.enviarEmail(usuario, notificacao);

                System.out.println("Senha redefinida e enviada para o e-mail do usuário.");
            } else {
                System.out.println("Falha na autenticação biométrica para recuperação de senha.");
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // Método para alterar senha (necessita estar logado)
    public void alterarSenha(String email, String senhaAtual, String novaSenha) {
        Usuario usuario = usuarios.get(email);

        if (usuario != null && usuarioLogado(email)) {
            if (usuario.getSenha().equals(senhaAtual)) {
                usuario.setSenha(novaSenha);
                System.out.println("Senha alterada com sucesso.");

                // Enviar notificação de alteração de senha
                Notificacao notificacao = new Notificacao(
                        "ALTERACAO",
                        "Sua senha foi alterada com sucesso.",
                        LocalDateTime.now()
                );
                sistemaDeNotificacao.enviarEmail(usuario, notificacao);
            } else {
                System.out.println("A senha atual não está correta.");
            }
        } else {
            System.out.println("Usuário não está logado ou não foi encontrado.");
        }
    }

    // Método para modificar informações de cadastro (necessita estar logado)
    public void modificarCadastro(String email, String novoEndereco, String novoTelefone) {
        Usuario usuario = usuarios.get(email);

        if (usuario != null && usuarioLogado(email)) {
            usuario.setEndereco(novoEndereco);
            usuario.setTelefone(novoTelefone);
            System.out.println("Informações de cadastro alteradas com sucesso.");

            // Enviar notificação de alteração de cadastro
            Notificacao notificacao = new Notificacao(
                    "ALTERACAO_CADASTRO",
                    "Seu cadastro foi atualizado com sucesso.",
                    LocalDateTime.now()
            );
            sistemaDeNotificacao.enviarEmail(usuario, notificacao);
        } else {
            System.out.println("Usuário não está logado ou não foi encontrado.");
        }
    }

    // Método auxiliar para verificar se o usuário está logado
    private boolean usuarioLogado(String email) {
        return usuariosLogados.getOrDefault(email, false);
    }

    // Método auxiliar para gerar uma nova senha (simples exemplo)
    private String gerarNovaSenha() {
        return "novaSenha123";  // Em um sistema real, uma senha aleatória mais segura seria gerada
    }

    // Método para remover um usuário
    public void removerUsuario(String email) {
        Usuario usuario = usuarios.remove(email);
        if (usuario != null) {
            apiBiometria.excluirBiometria(usuario);  // Excluir dados biométricos do usuário
            System.out.println("Usuário e dados biométricos removidos com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // Método para listar os usuários cadastrados (apenas para fins de debug)
    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            usuarios.values().forEach(usuario -> System.out.println("Usuário: " + usuario.getNome() + " - E-mail: " + usuario.getEmail()));
        }
    }
}
