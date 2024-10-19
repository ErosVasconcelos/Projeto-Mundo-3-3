package cadastrobd;

import java.util.Scanner;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.dao.PessoaFisicaDAO;
import cadastrobd.model.dao.PessoaJuridicaDAO;

public class CadastroBDTeste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("Selecione uma op��o:");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer do teclado

            switch (opcao) {
                case 1:
                    System.out.println("Tipo de Pessoa (1 - F�sica, 2 - Jur�dica):");
                    int tipoPessoa = scanner.nextInt();
                    scanner.nextLine();  // Limpa o buffer
                    if (tipoPessoa == 1) {
                        // Usando construtor padr�o
                        PessoaFisica pf = new PessoaFisica();
                        System.out.println("Nome: ");
                        pf.setNome(scanner.nextLine());
                        System.out.println("Logradouro: ");
                        pf.setLogradouro(scanner.nextLine());
                        System.out.println("Cidade: ");
                        pf.setCidade(scanner.nextLine());
                        System.out.println("Estado: ");
                        pf.setEstado(scanner.nextLine());
                        System.out.println("Telefone: ");
                        pf.setTelefone(scanner.nextLine());
                        System.out.println("Email: ");
                        pf.setEmail(scanner.nextLine());
                        System.out.println("CPF: ");
                        pf.setCpf(scanner.nextLine());
                        try {
                            pfDAO.incluir(pf);
                            System.out.println("Pessoa F�sica inclu�da com sucesso.");
                        } catch (Exception e) {
                            System.out.println("Erro ao incluir pessoa f�sica: " + e.getMessage());
                        }
                    } else if (tipoPessoa == 2) {
                        PessoaJuridica pj = new PessoaJuridica();
                        System.out.println("Nome: ");
                        pj.setNome(scanner.nextLine());
                        System.out.println("Logradouro: ");
                        pj.setLogradouro(scanner.nextLine());
                        System.out.println("Cidade: ");
                        pj.setCidade(scanner.nextLine());
                        System.out.println("Estado: ");
                        pj.setEstado(scanner.nextLine());
                        System.out.println("Telefone: ");
                        pj.setTelefone(scanner.nextLine());
                        System.out.println("Email: ");
                        pj.setEmail(scanner.nextLine());
                        System.out.println("CNPJ: ");
                        pj.setCnpj(scanner.nextLine());
                        try {
                            pjDAO.incluir(pj);
                            System.out.println("Pessoa Jur�dica inclu�da com sucesso.");
                        } catch (Exception e) {
                            System.out.println("Erro ao incluir pessoa jur�dica: " + e.getMessage());
                        }
                    }
                    break;

                case 2:
                    // C�digo de altera��o (semelhante ao de inclus�o)
                    break;

                case 3:
                    // C�digo de exclus�o
                    break;

                case 4:
                    // C�digo para exibir pelo ID usando os m�todos getPessoaFisica e getPessoaJuridica
                    break;

                case 5:
                    // C�digo para exibir todos
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Op��o inv�lida.");
                    break;
            }
        }

        scanner.close();
    }
}
