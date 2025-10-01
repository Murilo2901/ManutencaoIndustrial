package org.example.DAO;

import org.example.Model.OrdemManutencaoPeca;
import org.example.Util.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdemManutencaoDAO {
    public OrdemManutencaoDAO(int idMaquina, int idTecnico, LocalDate now, String pendente) {
    }

    public void insertOrdemManutencao(OrdemManutencaoDAO ordem, Connection conn) throws SQLException {
        String query = """
                 INSERT INTO OrdemManutencao
                 (idMaquina
                 ,idTecnico
                 ,dataSolicitacao
                 ,status)
                 VALUES
                 (?,?,?,?)
                 """;
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, ordem.getIdMaquina());
            stmt.setInt(2, ordem.getIdTecnico());
            stmt.setDate(3, Date.valueOf(ordem.getDataSolicitacao()));
            stmt.setString(4, ordem.getStatus());
            stmt.executeUpdate();
        }
    }

    private LocalDate getDataSolicitacao() {
        return null;
    }

    private String getStatus() {
    }

    private int getIdTecnico() {
        return 0;
    }

    private int getIdMaquina() {
        return 0;
    }

    public List<OrdemManutencaoPeca> listarOrdensPendentes() throws SQLException{
        List<OrdemManutencaoPeca> ordens = new ArrayList<>();
        String query = """
                SELECT O.id
                , O.idMaquina
                , M.nome AS nomeMaquina
                , O.idTecnico
                , T.nome AS nomeTecnico
                , O.status
                , O.dataSolicitacao
                FROM OrdemManutencao O
                JOIN Maquina M
                ON O.idMaquina = M.id
                JOIN Tecnico T
                ON O.idTecnico = T.id
                WHERE O.status = 'PENDENTE';
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int idMaquina = rs.getInt("idMaquina");
                String nomeMaquina = rs.getString("nomeMaquina");
                int idTecnico = rs.getInt("idTecnico");
                String nomeTecnico = rs.getString("nomeTecnico");
                String status = rs.getString("status");
                LocalDate dataSolicitacao = rs.getDate("dataSolicitacao").toLocalDate();

                var ordemManutencaoPeca = new OrdemManutencaoPeca(
                        id,
                        idMaquina,
                        idTecnico,
                        dataSolicitacao,
                        status,
                        nomeMaquina,
                        nomeTecnico
                );

                ordens.add(ordemManutencaoPeca);
            }
        }
        return ordens;
    }
}
