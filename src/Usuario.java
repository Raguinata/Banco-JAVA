public class Usuario {
    private String nomeUsuario;
    private String senhaUsuario;
    private double saldoConta;

    public  Usuario(){

    }

    public Usuario(String nomeUsuario, String senhaUsuario, double saldoConta) {

        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.saldoConta = saldoConta;
    }

    /**
     * @return String return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @param nomeUsuario the nomeUsuario to set
     */
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * @return String return the senhaUsuario
     */
    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    /**
     * @param senhaUsuario the senhaUsuario to set
     */
    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    /**
     * @return double return the saldoConta
     */
    public double getSaldoConta() {
        return saldoConta;
    }

    /**
     * @param saldoConta the saldoConta to set
     */
    public void setSaldoConta(double saldoConta) {
        this.saldoConta = saldoConta;
    }

    @Override
    public String toString() {
        return "Login: " + nomeUsuario + ", Senha: " + senhaUsuario + ", Saldo: " + saldoConta;
    }
}
