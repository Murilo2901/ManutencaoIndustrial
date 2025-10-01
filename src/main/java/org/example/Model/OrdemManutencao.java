package org.example.Model;

import java.time.LocalDate;

public class OrdemManutencao {
    private int id;
    private Maquina maquina;
    private Tecnico tecnicos;
    private LocalDate dataSolicitada;
    private String status;

    public OrdemManutencao(int id, Maquina maquina, Tecnico tecnicos, LocalDate dataSolicitada, String status) {
        this.id = id;
        this.maquina = maquina;
        this.tecnicos = tecnicos;
        this.dataSolicitada = dataSolicitada;
        this.status = status;
    }

    public OrdemManutencao(Maquina maquina, Tecnico tecnicos, LocalDate dataSolicitada, String status) {
        this.maquina = maquina;
        this.tecnicos = tecnicos;
        this.dataSolicitada = dataSolicitada;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public Tecnico getTecnicos() {
        return tecnicos;
    }

    public LocalDate getDataSolicitada() {
        return dataSolicitada;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public void setTecnicos(Tecnico tecnicos) {
        this.tecnicos = tecnicos;
    }

    public void setDataSolicitada(LocalDate dataSolicitada) {
        this.dataSolicitada = dataSolicitada;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
