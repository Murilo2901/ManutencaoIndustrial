package org.example.DAO;

import org.example.Model.Maquina;
import org.example.Util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaquinaDAO {
    public void cadastrarMaquina(Maquina maquina)throws SQLException {
        String sql = """
                INSERT INTO maquina
                (nome,setor,status)
                VALUES
                (?,?,?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, maquina.getNome());
            stmt.setString(2,maquina.getSetor());
            stmt.setString(3,maquina.getStatus());
            stmt.executeUpdate();
        }
    }


}
