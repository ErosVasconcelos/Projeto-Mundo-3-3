package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.dao.PessoaFisicaDAO;
import cadastrobd.model.dao.PessoaJuridicaDAO;
import java.sql.SQLException;

public class CadastroBDTeste {
    public static void main(String[] args) {
        try {
            PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
            PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();

            PessoaFisica pf = new PessoaFisica(0, "Jo�o Silva", "Rua A", "Recife", "PE", "81987654321", "joao@example.com", "423.456.789-09");
            pfDAO.incluir(pf);
            System.out.println("Pessoa F�sica inclu�da:");
            pf.exibir();

            pf.setNome("Jo�o Pedro Silva");
            pfDAO.alterar(pf);
            System.out.println("Pessoa F�sica alterada:");
            pf.exibir();

            System.out.println("Todas as Pessoas F�sicas:");
            for (PessoaFisica pessoa : pfDAO.getPessoas()) {
                pessoa.exibir();
            }

            pfDAO.excluir(pf.getId());
            System.out.println("Pessoa F�sica exclu�da.");

            PessoaJuridica pj = new PessoaJuridica(0, "Empresa X", "Rua B", "Recife", "PE", "8130123456", "contato@empresa.com", "42.345.678/0001-99");
            pjDAO.incluir(pj);
            System.out.println("Pessoa Jur�dica inclu�da:");
            pj.exibir();

            pj.setNome("Empresa Y");
            pjDAO.alterar(pj);
            System.out.println("Pessoa Jur�dica alterada:");
            pj.exibir();

            System.out.println("Todas as Pessoas Jur�dicas:");
            for (PessoaJuridica pessoa : pjDAO.getPessoas()) {
                pessoa.exibir();
            }

            pjDAO.excluir(pj.getId());
            System.out.println("Pessoa Jur�dica exclu�da.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
