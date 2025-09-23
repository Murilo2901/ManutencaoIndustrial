package org.example;

import org.example.DAO.MaquinaDAO;
import org.example.DAO.PecaDAO;
import org.example.DAO.TecnicoDAO;
import org.example.Model.Maquina;
import org.example.Model.peca;
import org.example.Model.Tecnicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    static Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        inicio();
    }
    public static void inicio() {
        boolean sair = false;
        System.out.println("\n __________________________________");
        System.out.println("| SISTEMA DE MANUTENÇÃO INDUSTRIAL |");
        System.out.println("|----------------------------------|");
        System.out.println("| 1. Cadastrar máquina             |");
        System.out.println("| 2. Cadastrar técnico             |");
        System.out.println("| 3. Cadastrar peça                |");
        System.out.println("| 4. Criar ordem de manutenção     |");
        System.out.println("| 5. Associar peças à ordem        |");
        System.out.println("| 6. Executar manutenção           |");
        System.out.println("|----------------------------------|");
        System.out.println("| 0. Sair                          |");
        System.out.println("|__________________________________|");

        int opcao = SC.nextInt();

        switch (opcao){
            case 1:{
                cadastrarMaquina();
                break;
            }
            case 2:{
                cadastrarTecnico();
            }
            case 3:{
                cadastarPeca();
            }
            case 4:{
                cadastrarOrdemManutencao();
            }
            case 0:{
                sair = true;
                break;
            }
        }
    }
    public static void cadastrarMaquina(){
        System.out.println("Digite o nome da maquina");
        String nome = SC.nextLine();

        System.out.println("Digite o setor da maquina");
        String setorMaquina = SC.nextLine();

        if (nome.isEmpty() && !setorMaquina.isEmpty()){
            var maquina = new Maquina(nome, setorMaquina, "OPERACIONAL");

            var maquinaDAO = new MaquinaDAO();

            try {
                if (maquinaDAO.verificaDuplicacaoMaquina(maquina)){
                    maquinaDAO.insertMaquina(maquina);
                    System.out.println("Cadastrada");
                }
                maquinaDAO.insertMaquina(maquina);
                System.out.println("Maquina já cadastrada");
            }catch (SQLException e){
                System.out.println("Erro ao cadastrar maquina");
                e.printStackTrace();
            }
        }else {
            System.out.println("nome não pode estar null");
        }
    }

    public static void cadastrarTecnico(){
        System.out.println("Digite o nome do tecnico");
        String nomeTecnico = SC.nextLine();

        System.out.println("Digite a especialidade do tecnico");
        String especialidade = SC.nextLine();

        if(nomeTecnico.isEmpty() && !especialidade.isEmpty()) {
            var tecnico = new Tecnico(nomeTecnico, especialidade);

            var tecnicoDAO = new TecnicoDAO();

            try {
                if (tecnicoDAO.verificaDuplicacaoTecnico(tecnico)) {
                    tecnicoDAO.insertTecnico(tecnico);
                    System.out.println("Cadastrado");
                } else {
                    System.out.println("nome não pode estar null");
                }
                System.out.println("tecnico já cadastrado");
            } catch (SQLException e) {
                System.out.println("Erro ao cadastrar tecnico");
                e.printStackTrace();
            }
        }
    }

    public static void cadastarPeca(){
        System.out.println("Digite o nome da peça");
        String nomePeca = SC.nextLine();

        System.out.println("Digite o estoque inical");
        double estoque = SC.nextDouble();

        if(nomePeca.isEmpty() && estoque>=0) {
            var peca = new Peca(nomePeca, estoque);

            var pecaDAO = new PecaDAO();

            try {
                if (pecaDAO.verificaDuplicacaoPeca(peca)) {
                    pecaDAO.insertPeca(peca);
                    System.out.println("Cadastrado");
                } else {
                    System.out.println("nome não pode estar null");
                }
                System.out.println("tecnico já cadastrado");
            } catch (SQLException e) {
                System.out.println("Erro ao cadastrar tecnico");
                e.printStackTrace();
            }
        }
    }

    public static void cadastrarOrdemManutencao()throws SQLException{
        System.out.println("Cadastrar ordem de manutenção");
        String nomePeca = SC.nextLine();

        System.out.println("Digite o estoque inical");
        double estoque = SC.nextDouble();

        var maquinaDAO = new MaquinaDAO();
        List<Maquina> maquinas = new ArrayList<>();
        List<Integer> opcoes = new ArrayList<>();
        List<Tecnico> tecnicos = new ArrayList<>();
        List<Integer> opcoesTecnico = new ArrayList<>();
        try {
            maquinas = maquinaDAO.listarMaquinasOperacional();
        }catch (SQLException e){
            System.out.println("Erro ao buscar maquinas");
            e.printStackTrace();
        }

        maquinas.forEach((maquina -> {
            System.out.println("id" + maquina.getId());
            System.out.println("NOME" + maquina.getNome());
            System.out.println("Setor" + maquina.getSetor());
            System.out.println("status" + maquina.getStatus());

            opcoes.add(maquina.getId());

        }));

        System.out.println("Selecione o id da máquina: ");
        int idMaquina = SC.nextInt();
        SC.nextLine();

        var tecnicoDAO = new TecnicoDAO();

        if(opcoes.contains(idMaquina)){
            try {
                tecnicos = tecnicoDAO.listarTecnico();
            }catch (SQLException e){
                System.out.println("Erro");
                e.printStackTrace();
            }

            tecnicos.forEach((tecnico -> {
                System.out.println("id" + tecnico.getId());
                System.out.println("NOME" + tecnico.getNome());
                System.out.println("Setor" + tecnico.getEspecialidade());

                opcoesTecnico.add(tecnico.getId());
            }));
        }else{
            System.out.println("id inválido!");
        }
    }
}