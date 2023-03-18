import java.util.ArrayList;

import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {

        String senacBank = "<html><font face=\"Times New Roman\" size=\"700\">Senac Bank</font></html>";
        String opcoes[] = { "Consulta", "Depósito", "Saque", "Recursos Avançados", "Sair" };
        String opcoesAdmin[] = { "Listar todos os Usuários", "Remover Usuário", "Adicionar Usuário", "Editar Usuário",
                "Sair" };
        String nomeUsuario;
        String senhaUsuario;
        int escolha;
        int escolhaAdmin;
        byte erros = 0;
        double deposito;
        double saque;
        boolean validarAcesso = false;
        Usuario usuarioLogado = null;

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        Autenticador login = new Autenticador(usuarios);

        usuarios.add(new Usuario("Leonardo", "123", 1000));
        usuarios.add(new Usuario("Bruna", "345", 0));
        usuarios.add(new Usuario("Pedro", "678", 1110));
        usuarios.add(new Usuario("admin", "123", 0));

        JOptionPane.showMessageDialog(null, senacBank + "\n\nSeja Bem Vindo(a) ao Senac Bank\n\n",
                "Bem Vindo(a)", 1);

        // Validação do Login

        do {
            try {
                erros++;
                nomeUsuario = JOptionPane.showInputDialog(null, senacBank + "\n\nDigite o nome do usuário: ", "login", 1);
                senhaUsuario = (JOptionPane.showInputDialog(null, senacBank + "\n\nInsira a senha:", "login", 1));
        
                if (login.autenticarLogin(nomeUsuario, senhaUsuario)) {
                    validarAcesso = true;
                    usuarioLogado = login.getUsuarioAutenticado();
                }
        
            } catch (Exception l) {
                JOptionPane.showMessageDialog(null, senacBank + "\n\n" + l.getMessage(), "Erro", 0);
            }
        
        } while (!validarAcesso && erros < 3);
        
        if (erros >= 3) {
            JOptionPane.showMessageDialog(null, senacBank + "\n\nNÚMERO MÁXIMO DE TENTATIVAS ATINGIDO. TENTE NOVAMENTE EM 5 SEGUNDOS.");
            try {
                Thread.sleep(5000); // espera 5 segundos antes de permitir outra tentativa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            erros = 0; // reseta o contador de erros
        }
        
        // Loop do-while externo
        do {
            try {
                erros++;
                nomeUsuario = JOptionPane.showInputDialog(null, senacBank + "\n\nDigite o nome do usuário: ", "login", 1);
                senhaUsuario = (JOptionPane.showInputDialog(null, senacBank + "\n\nInsira a senha:", "login", 1));
        
                if (login.autenticarLogin(nomeUsuario, senhaUsuario)) {
                    validarAcesso = true;
                    usuarioLogado = login.getUsuarioAutenticado();
                }
        
            } catch (Exception l) {
                JOptionPane.showMessageDialog(null, senacBank + "\n\n" + l.getMessage(), "Erro", 0);
            }
        
        } while (!validarAcesso && erros < 3);
        
        // Verificação final para determinar se o usuário foi autenticado
        if (validarAcesso) {
            JOptionPane.showMessageDialog(null, senacBank + "\n\nBEM-VINDO, " + usuarioLogado.getNomeUsuario() + "!");
        } else {
            JOptionPane.showMessageDialog(null, senacBank + "\n\nNÃO FOI POSSÍVEL AUTENTICAR O LOGIN.");
        }
        

        validarAcesso = false;

        do {
            escolha = JOptionPane.showOptionDialog(null, senacBank +
                    "\n\nO que você deseja fazer?", "Menu", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0:
                    JOptionPane.showMessageDialog(null, senacBank + "\n\nO saldo da sua conta é R$" +
                            usuarioLogado.getSaldoConta(), "Saldo", 1);
                    break;

                case 1:
                    deposito = Double.parseDouble(JOptionPane.showInputDialog(null,
                            senacBank + "\n\nNos informe o valor que você deseja depósitar"));
                    if (deposito >= 0) {
                        usuarioLogado.setSaldoConta(usuarioLogado.getSaldoConta() + deposito);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                senacBank + "\n\nNão é possível depósitar um valor negativo", "Erro", 0);
                    }
                    break;

                case 2:
                    saque = Double.parseDouble(JOptionPane.showInputDialog(null,
                            senacBank + "\n\nNos informe o valor que você deseja sacar"));
                    if (saque <= usuarioLogado.getSaldoConta()) {
                        usuarioLogado.setSaldoConta(usuarioLogado.getSaldoConta() - saque);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                senacBank + "\n\nNão é possível sacar um valor maior do que o saldo", "Erro", 0);
                    }
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null,
                            senacBank + "\n\n Este é um recurso apenas para administradores/gerente do banco",
                            "Atenção", 2);
                            int numeroTentativas = 0;
                            do {
                                try {
                                    nomeUsuario = JOptionPane.showInputDialog(null,
                                            senacBank + "\n\nDigite o nome do usuário: ",
                                            "login", 1);
                                    senhaUsuario = (JOptionPane.showInputDialog(null, senacBank + "\n\nInsira a senha:",
                                            "login", 1));
                            
                                    if (login.autenticarLoginAdmin(nomeUsuario, senhaUsuario)) {
                                        validarAcesso = true;
                                    }
                                } catch (Exception l) {
                                    JOptionPane.showMessageDialog(null, senacBank + "\n\n" + l.getMessage(), "Erro", 0);
                                }
                                
                                numeroTentativas++;
                                
                                if (numeroTentativas >= 3 && !validarAcesso) {
                                    JOptionPane.showMessageDialog(null, "Número máximo de tentativas atingido. Tente novamente em alguns segundos.");
                                    try {
                                        Thread.sleep(5000); // espera por 5 segundos
                                    } catch (InterruptedException ex) {
                                        Thread.currentThread().interrupt();
                                    }
                                    numeroTentativas = 0;
                                }
                            } while (!validarAcesso && numeroTentativas < 3);

                    do {
                        escolhaAdmin = JOptionPane.showOptionDialog(null, senacBank +
                                "\n\nO que você deseja fazer?", "Menu", JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, opcoesAdmin, opcoesAdmin[0]);

                        switch (escolhaAdmin) {
                            case 0:
                                String lista = "Usuários do banco:\n";
                                int j = 0;
                                for (Usuario i : usuarios) {
                                    lista += j + " - " + i + "\n";
                                    j++;
                                }
                                JOptionPane.showMessageDialog(null, lista, "Lista de usuários", 1);
                                break;

                            case 1:
                                int remove = Integer.parseInt(JOptionPane.showInputDialog(null, senacBank +
                                        "\n\nDigite a posição da conta que você deseja remover", "Remover", 2));
                                if (remove < 0 || remove > usuarios.size()) {
                                    JOptionPane.showMessageDialog(null, senacBank + "\n\nPosição inválida", "Erro", 0);
                                } else {
                                    usuarios.remove(remove);
                                }
                                break;

                            case 2:
                                String novoNome = JOptionPane.showInputDialog(null,
                                        senacBank + "\n\nInsira o nome do novo usuário:");
                                String novaSenha = JOptionPane.showInputDialog(null,
                                        senacBank + "\n\nInsira a senha do novo usuário:");
                                double novoSaldo = Double.parseDouble(
                                        JOptionPane.showInputDialog(null,
                                                senacBank + "\n\nInsira o saldo inicial do novo usuário:"));

                                Usuario novoUsuario = new Usuario(novoNome, novaSenha, novoSaldo);
                                usuarios.add(novoUsuario);

                                JOptionPane.showMessageDialog(null, senacBank + "\n\nUsuário adicionado com sucesso!",
                                        "Sucesso",
                                        JOptionPane.INFORMATION_MESSAGE);
                                break;

                            case 3:
                                int edit = Integer.parseInt(JOptionPane.showInputDialog(null, senacBank +
                                        "\n\nDigite a posição do usuário que você deseja editar", "Editar", 2));
                                if (edit < 0 || edit > usuarios.size()) {
                                    JOptionPane.showMessageDialog(null, senacBank + "\n\nPosição inválida", "Erro", 0);
                                    break;
                                }

                                String novoNomeUsuario = JOptionPane.showInputDialog(null, senacBank + "\n\nDigite o novo nome do usuário");
                                String novoSenhaUsuario = JOptionPane.showInputDialog(null, senacBank + "\n\nDigite a nova senha do usuário");
                                double novoSaldoConta = Double.parseDouble(JOptionPane.showInputDialog(null, senacBank + "\n\nDigite o novo saldo do usuário"));
                                
                                // Atualizar o objeto do usuário no ArrayList com os novos valores fornecidos
                                usuarios.get(edit).setNomeUsuario(novoNomeUsuario);
                                usuarios.get(edit).setSenhaUsuario(novoSenhaUsuario);
                                usuarios.get(edit).setSaldoConta(novoSaldoConta);

                                break;

                            default:
                                if (escolhaAdmin == -1) {
                                    escolhaAdmin = 4;
                                }
                                break;
                        }
                    } while (escolhaAdmin != 4);

                    break;

                default:
                    if (escolha == -1) {
                        escolha = 4;
                    }
                    break;
            }
        } while (escolha != 4);

        JOptionPane.showMessageDialog(null, senacBank + "\n\nObrigado por utilizar o nosso banco :)");
    
}
}
