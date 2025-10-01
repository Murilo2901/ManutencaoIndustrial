package org.example.Model;

import java.time.LocalDate;

public class OrdemManutencaoPeca extends OrdemManutencao {
    private String nomeMaquina;

    private String nomeTecnico;

    public OrdemManutencaoPeca(int id,int idMaquina
            , int idTecnico, LocalDate dataSolicitacao
            , String status, String nomeMaquina
            , String nomeTecnico) {

        super(id, idMaquina, idTecnico, dataSolicitacao, status);
        this.nomeMaquina = nomeMaquina;
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeMaquina() {
        return nomeMaquina;
    }

    public void setNomeMaquina(String nomeMaquina) {
        this.nomeMaquina = nomeMaquina;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getDataSolicitacao() {
    }
}

