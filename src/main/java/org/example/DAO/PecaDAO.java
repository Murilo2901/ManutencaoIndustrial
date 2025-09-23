package org.example.DAO;

import org.example.Model.Peca;
import org.example.Model.Tecnico;
import org.example.Util.Conexao;
import org.example.database.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PecaDAO {
    public void insertPeca(Peca peca)throws SQLException {
        String sql = """
                INSERT INTO peca
                (nome, estoque)
                VALUES
                (?,?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getEstoque());
            stmt.executeUpdate();
        }

    }

    public boolean verificaDuplicacaoPeca(Peca peca)throws SQLException {
        String sql = """
                SELECT COUNT(0) AS Linhas
                FROM peca
                WHERE nome = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getEstoque());

            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt("Linhas") > 0) {
                return true;
            }
        }
        return false;
    }
}
