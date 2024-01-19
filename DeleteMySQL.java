import java.sql.*;
import java.util.*;

public class DeleteMySQL {
    public static void main(String[] args) {
        String status = "Nada aconteceu ainda...";
        Connection conn = App.conectar();
        Scanner scnRespostas = new Scanner(System.in);
        Scanner scnInput = new Scanner(System.in);
        Scanner scnSenha = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Digite o login:");
        String strBusca = scnInput.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Digite sua senha:");
        String scrSenha = scnSenha.nextLine();
        try {
            String strSqlSelect = "select * from `mysql_connector`.`tbl_login` where `login` = '" + strBusca
                    + "' and `senha` = '" + scrSenha + "' ;";
            Statement stmSql = conn.createStatement();
            ResultSet rsSql = stmSql.executeQuery(strSqlSelect);
            String nome = "";

            if (rsSql.next()) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                status = "Login concluido. Entrando...";
                nome += "[" + rsSql.getString("nome") + "] ";
            } else {
                throw new ArithmeticException("Login ou senha incorretos!");
            }
            System.out.println(status);
            Thread.sleep(2000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Bem vindo! " + nome);
            System.out.println("\n\nDeseja fazer alguma Ação no seu perfil? [s] ou [n]");
            String strRespostas = scnRespostas.nextLine();
            if (strRespostas.equals("s") || strRespostas.equals("S")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(
                        "Voce deseja apagar sua conta? (LEMBRETE!!! --> Essa ação é irreversivel!!!) digite os numeros para selecionar a opção:\n[1] - Sim\n[2] - Sair");
                strRespostas = scnRespostas.nextLine();
                switch (strRespostas) {
                    case "1":
                        try {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Login atual: " + strBusca + " [Esta sendo deletado...]");
                            Thread.sleep(5000);
                            String strSqlUpdate = "DELETE from `mysql_connector`.`tbl_login` where `login` = '"
                                    + strBusca + "';";
                            stmSql.addBatch(strSqlUpdate);
                            stmSql.executeBatch();
                            stmSql.close();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            status = "Perfil deletado com sucesso!";
                        } catch (Exception e) {
                            System.err.println("Algo deu errado " + e);
                            status = ("Erro " + e);
                        }
                        break;
                    case "2":
                        status = "Saindo...";
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        break;
                    default:
                        status = "Saindo...";
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        break;
                }
            } else {
                status = "Saindo...";
            }
            stmSql.close();
            rsSql.close();
        } catch (Exception e) {
            System.out.println("Ops! Ocorreu o erro" + e);
            status = "Saindo...";
        }
        System.out.println(status);
        scnRespostas.close();
        scnSenha.close();
        scnInput.close();
    }
}