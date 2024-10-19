package cadastrobd.model.dao;

import cadastrobd.model.PessoaFisica;
import cadastrobd.util.ConectorBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    private Connection conn;

    public PessoaFisicaDAO() {
        try {
            this.conn = ConectorBD.getConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void incluir(PessoaFisica pf) throws SQLException {
        String sql = "INSERT INTO Pessoa (Nome, Logradouro, Cidade, Estado, Telefone, Email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pf.getNome());
            stmt.setString(2, pf.getLogradouro());
            stmt.setString(3, pf.getCidade());
            stmt.setString(4, pf.getEstado());
            stmt.setString(5, pf.getTelefone());
            stmt.setString(6, pf.getEmail());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idPessoa = rs.getInt(1);
                pf.setId(idPessoa);
            }
        }

        String sqlPf = "INSERT INTO PessoaFisica (ID_Pessoa, CPF) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sqlPf)) {
            stmt.setInt(1, pf.getId());
            stmt.setString(2, pf.getCpf());
            stmt.executeUpdate();
        }
    }

    public void alterar(PessoaFisica pf) throws SQLException {
        String sql = "UPDATE Pessoa SET Nome=?, Logradouro=?, Cidade=?, Estado=?, Telefone=?, Email=? WHERE ID_Pessoa=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pf.getNome());
            stmt.setString(2, pf.getLogradouro());
            stmt.setString(3, pf.getCidade());
            stmt.setString(4, pf.getEstado());
            stmt.setString(5, pf.getTelefone());
            stmt.setString(6, pf.getEmail());
            stmt.setInt(7, pf.getId());
            stmt.executeUpdate();
        }

        String sqlPf = "UPDATE PessoaFisica SET CPF=? WHERE ID_Pessoa=?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlPf)) {
            stmt.setString(1, pf.getCpf());
            stmt.setInt(2, pf.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sqlPf = "DELETE FROM PessoaFisica WHERE ID_Pessoa=?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlPf)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        String sql = "DELETE FROM Pessoa WHERE ID_Pessoa=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<PessoaFisica> getPessoas() throws SQLException {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM PessoaFisica pf JOIN Pessoa p ON pf.ID_Pessoa = p.ID_Pessoa";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PessoaFisica pf = new PessoaFisica(
                        rs.getInt("ID_PessoaFisica"),
                        rs.getString("Nome"),
                        rs.getString("Logradouro"),
                        rs.getString("Cidade"),
                        rs.getString("Estado"),
                        rs.getString("Telefone"),
                        rs.getString("Email"),
                        rs.getString("CPF")
                );
                pessoas.add(pf);
            }
        }
        return pessoas;
    }
}
