import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Autenticador {

    private ArrayList<Usuario> usuarios;
    private Usuario usuarioAutenticado;
    private int tentativasRestantes;
    private LocalDateTime ultimoAcesso;

    public Autenticador(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
        this.tentativasRestantes = 3; // número máximo de tentativas permitidas
    }

    public boolean autenticarLogin(String login, String senha) throws Exception {
        // verifica se o usuário ainda tem tentativas restantes
        if (tentativasRestantes <= 0) {
            // verifica se já passou tempo suficiente para tentar novamente (10 segundos neste caso)
            LocalDateTime agora = LocalDateTime.now();
            if (ChronoUnit.SECONDS.between(ultimoAcesso, agora) < 10) {
                throw new Exception("Você excedeu o número máximo de tentativas. Tente novamente mais tarde.");
            } else {
                // se já passou tempo suficiente, zera as tentativas e atualiza o último acesso
                tentativasRestantes = 3;
                ultimoAcesso = agora;
            }
        }

        for (Usuario user : usuarios) {
            if (user.getNomeUsuario().equals(login) && user.getSenhaUsuario().equals(senha)) {
                this.usuarioAutenticado = user;
                tentativasRestantes = 3; // reseta as tentativas em caso de sucesso
                return true;
            }
        }

        // se não encontrou um usuário com os dados informados, decrementa o número de tentativas restantes
        tentativasRestantes--;

        // verifica se ainda há tentativas restantes ou se deve bloquear o acesso por um tempo
        if (tentativasRestantes > 0) {
            throw new Exception("Usuário ou senha incorretos. Tentativas restantes: " + tentativasRestantes);
        } else {
            ultimoAcesso = LocalDateTime.now();
            throw new Exception("Você excedeu o número máximo de tentativas. Tente novamente mais tarde.");
        }
    }

    public boolean autenticarLoginAdmin(String login, String senha) throws Exception {
        if (login.equals("admin") && senha.equals("123")) {
            return true;
        } else {
            throw new Exception("Usuário ou senha incorretos");
        }
    }

    public Usuario getUsuarioAutenticado() {
        return this.usuarioAutenticado;
    }

}
