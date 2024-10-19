package cadastrobd.model.dao;

import cadastrobd.model.PessoaJuridica;
import cadastrobd.util.ConectorBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    private Connection conn;

    // Construtor
    public PessoaJuridicaDAO() {
        try {
            this.conn = ConectorBD.getConnection(); // Obtendo a conexão
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException(e); // Propagar exceção
        }
    }

    public void incluir(PessoaJuridica pj) throws SQLException {
        String sql = "INSERT INTO Pessoa (Nome, Logradouro, Cidade, Estado, Telefone, Email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pj.getNome());
            stmt.setString(2, pj.getLogradouro());
            stmt.setString(3, pj.getCidade());
            stmt.setString(4, pj.getEstado());
            stmt.setString(5, pj.getTelefone());
            stmt.setString(6, pj.getEmail());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idPessoa = rs.getInt(1);
                pj.setId(idPessoa); // Atualiza o ID da PessoaJuridica
            }
        }

        String sqlPj = "INSERT INTO PessoaJuridica (ID_Pessoa, CNPJ) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sqlPj)) {
            stmt.setInt(1, pj.getId());
            stmt.setString(2, pj.getCnpj());
            stmt.executeUpdate();
        }
    }

    public void alterar(PessoaJuridica pj) throws SQLException {
        String sql = "UPDATE Pessoa SET Nome=?, Logradouro=?, Cidade=?, Estado=?, Telefone=?, Email=? WHERE ID_Pessoa=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pj.getNome());
            stmt.setString(2, pj.getLogradouro());
            stmt.setString(3, pj.getCidade());
            stmt.setString(4, pj.getEstado());
            stmt.setString(5, pj.getTelefone());
            stmt.setString(6, pj.getEmail());
            stmt.setInt(7, pj.getId());
            stmt.executeUpdate();
        }

        String sqlPj = "UPDATE PessoaJuridica SET CNPJ=? WHERE ID_Pessoa=?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlPj)) {
            stmt.setString(1, pj.getCnpj());
            stmt.setInt(2, pj.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sqlPj = "DELETE FROM PessoaJuridica WHERE ID_Pessoa=?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlPj)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        String sql = "DELETE FROM Pessoa WHERE ID_Pessoa=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<PessoaJuridica> getPessoas() throws SQLException {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM PessoaJuridica pj JOIN Pessoa p ON pj.ID_Pessoa = p.ID_Pessoa";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PessoaJuridica pj = new PessoaJuridica(
                        rs.getInt("ID_PessoaJuridica"),
                        rs.getString("Nome"),
                        rs.getString("Logradouro"),
                        rs.getString("Cidade"),
                        rs.getString("Estado"),
                        rs.getString("Telefone"),
                        rs.getString("Email"),
                        rs.getString("CNPJ")
                );
                pessoas.add(pj);
            }
        }
        return pessoas;
    }
}
