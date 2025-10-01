package org.example.DAO;

import org.example.Model.Tecnico;
import org.example.Util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TecnicoDAO {
    public void insertTecnico(Tecnico tecnico)throws SQLException {
        String sql = """
                INSERT INTO tecnico
                (nome, especialidade)
                VALUES
                (?,?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getEspecialidade());
            stmt.executeUpdate();
        }

    }

    public boolean verificaDuplicacaoTecnico(Tecnico tecnico)throws SQLException {
        String sql = """
                SELECT COUNT(0) AS Linhas
                FROM Tecnico
                WHERE nome = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getEspecialidade());

            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt("Linhas") > 0) {
                return true;
            }
        }
        return false;
    }

    public List<Tecnico> listarTecnicos() throws SQLException{
        List<Tecnico> tecnicos = new ArrayList<>();
        String query = """
                SELECT   id
                        ,nome
                        ,especialidade
                FROM Tecnico
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String especialidade = rs.getString("especialidade");

                var tecnico = new Tecnico(id,nome,especialidade);
                tecnicos.add(tecnico);
            }
        }
        return tecnicos;
    }
}
