package org.example.DAO;


import org.example.Model.Maquina;
import org.example.database.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MaquinaDAO {
    public void insertMaquina(Maquina maquina)throws SQLException {
        String sql = """
                INSERT INTO maquina
                (nome, setor, status)
                VALUES
                (?,?,?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maquina.getNome());
            stmt.setString(2, maquina.getSetor());
            stmt.setString(3, maquina.getStatus());
            stmt.executeUpdate();
        }

    }

    public boolean verificaDuplicacaoMaquina(Maquina maquina)throws SQLException {
        String sql = """
                SELECT COUNT(0) AS Linhas
                FROM Maquina
                WHERE nome = ?
                AND setor = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maquina.getNome());
            stmt.setString(2, maquina.getSetor());
            stmt.setString(3, maquina.getStatus());

            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt("Linhas") > 0) {
                return true;
            }
        }
        return false;
    }

    public List<Maquina> listarMaquinasOperacional()throws SQLException{
        String sql = """
                SELECT id, nome, setor, status
                FROM maquina
                WHERE status = 'Operciaonal'
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String setor = rs.getString("setor");
                String status = rs.getString("status");
            }

        }
        return maquinas;
    }
}
