package org.example;

import org.example.DAO.*;
import org.example.Model.*;
import org.example.Util.Conexao;
import org.example.database.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
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
        SC.nextLine();

        switch (opcao){
            case 1:{
                cadastrarMaquina();
                break;
            }
            case 2:{
                cadastrarTecnico();
                break;
            }
            case 3:{
                cadastrarPeca();
                break;
            }
            case 4:{
                cadastrarOrdemManutecao();
                break;
            }
            case 5:{
                associarPecasOrdem();
                break;
            }
            case 0:{
                sair = true;
                break;
            }
        }
        if(!sair){
            inicio();
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

    public static void cadastrarPeca(){
        System.out.println("Digite o nome da peça: ");
        String nomePeca = SC.nextLine();

        System.out.println("Digite o estoque inicial da peça: ");
        double estoque = SC.nextDouble();
        SC.nextLine();

        if(!nomePeca.isEmpty() && estoque>=0){
            var peca = new Peca(nomePeca,estoque);
            var pecaDao = new PecaDAO();
            try{
                if(!pecaDao.verificaDuplicacaoPeca(peca.getNome())){
                    pecaDao.insertPeca(peca);
                    System.out.println("Peça cadastrada com sucesso!");
                }else{
                    System.out.println("Peça já cadastrada!");
                }
            }catch (SQLException e){
                System.out.println("Erro ao cadastrar peça");
            }
        }else{
            System.out.println("nome da peça não pode ser nulo e estoque não pode ser menor que zero!");
        }
    }
    public static void cadastrarOrdemManutecao(){
        System.out.println("Cadastrar Ordem de Manutenção");
        var maquinaDao = new MaquinaDAO();
        List<Maquina> maquinas = new ArrayList<>();
        List<Integer> opcoesMaquinas = new ArrayList<>();
        List<Tecnico> tecnicos = new ArrayList<>();
        List<Integer> opcoesTecnicos = new ArrayList<>();

        try{
            maquinas = maquinaDao.listarMaquinasOperacional();
        }catch (SQLException e){
            System.out.println("Erro ao buscar máquinas!");
            e.printStackTrace();
        }

        maquinas.forEach((maquina -> {
            System.out.println("ID DA MÁQUINA: " + maquina.getId());
            System.out.println("NOME DA MÁQUINA: " + maquina.getNome());
            System.out.println("SETOR DA MÁQUINA: " + maquina.getSetor());
            System.out.println("STATUS DA MÁQUINA: " + maquina.getStatus());

            opcoesMaquinas.add(maquina.getId());
        }));

        System.out.println("Selecione o id da maquina: ");
        int idMaquina= SC.nextInt();
        SC.nextLine();

        var tecnicoDao = new TecnicoDAO();

        if(opcoesMaquinas.contains(idMaquina)){
            try{
                tecnicos = tecnicoDao.listarTecnicos();
            }catch (SQLException e){
                System.out.println("Erro ao buscar técnicos");
                e.printStackTrace();
            }

            tecnicos.forEach(tecnico -> {
                System.out.println("ID DO TÉCNICO: " + tecnico.getId());
                System.out.println("NOME DO TÉCNICO: " + tecnico.getNome());
                System.out.println("ESPECIALIDADE DO TÉCNICO: " + tecnico.getEspecialidade());

                opcoesTecnicos.add(tecnico.getId());
            });

            int idTecnico = SC.nextInt();
            SC.nextLine();
            var ordemManutencaoDao = new OrdemManutencaoDAO();
            if(opcoesTecnicos.contains(idTecnico)){
                var ordemManutencao = new OrdemManutencaoDAO(idMaquina, idTecnico, LocalDate.now(), "PENDENTE");
                Connection conn = null;
                try{
                    conn = Conexao.conectar();
                    conn.setAutoCommit(false);

                    ordemManutencaoDao.insertOrdemManutencao(ordemManutencao,conn);
                    maquinaDao.atualizarStatusManutencao(idMaquina,"EM_MANUTENCAO", conn);

                    conn.commit();
                    System.out.println("Ordem de manutenção criada com sucesso!");
                }catch (SQLException e) {
                    try{
                        conn.rollback();
                        conn.close();
                    }catch (SQLException e2){
                        e2.printStackTrace();
                    }
                    System.out.println("Erro ao conectar no banco de dados");
                }finally {
                    try{
                        conn.close();
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }else{
                System.out.println("Opção invalida!");
            }
        }else{
            System.out.println("Id inválido!");
        }
    }
    public static void associarPecasOrdem(){
        boolean sair = false;
        System.out.println("Associar Peças a ordem");
        List<OrdemManutencaoPeca> ordemManutencaoPecas = new ArrayList<>();
        List<Integer> opcoesOrdem = new ArrayList<>();
        var ordemManutencaoDao= new OrdemManutencaoDAO();
        try{
            ordemManutencaoPecas = ordemManutencaoDao.listarOrdensPendentes();
        }catch (SQLException e){
            System.out.println("Erro ao buscar no banco de dados!");
            e.printStackTrace();
        }

        System.out.println("\nORDENS PENDENTES: ");
        ordemManutencaoPecas.forEach(ordem -> {

            System.out.println("ID Ordem: " + ordem.getId());
            System.out.println("ID Máquina: " + ordem.getId());
            System.out.println("Nome Máquina: " + ordem.getNomeMaquina());
            System.out.println("Id Técnico: " + ordem.getId());
            System.out.println("Nome Técnico: " + ordem.getNomeTecnico());
            System.out.println("Status: " + ordem.getStatus());
            System.out.println("Status: " + ordem.getDataSolicitacao());

            System.out.println("\n");

            opcoesOrdem.add(ordem.getId());
        });

        System.out.println("Digite o ID da ordem desejada: ");
        int idOrdem = SC.nextInt();
        SC.nextLine();

        var pecaDao = new PecaDAO();
        List<Peca> pecas  = new ArrayList<>();
        if(opcoesOrdem.contains(idOrdem)){
            try{
                pecas = pecaDao.listarPecas();
            }catch (SQLException e){
                System.out.println("Erro ao conectar no banco de dados!");
                e.printStackTrace();
            }
            //while
            List<Integer> opcoesPecas = new ArrayList<>();
            System.out.println("Peças: ");
            pecas.forEach(peca -> {

                System.out.println("ID" + peca.getId());
                System.out.println("NOME" + peca.getNome());
                System.out.println("ESTOQUE" + peca.getEstoque());

                opcoesPecas.add(peca.getId());
            });

            System.out.println("Digite o id da peça: ");
            int idPeca = SC.nextInt();
            SC.nextLine();

            if(opcoesPecas.contains(idPeca)){
                System.out.println("Digite a quantidade necessária: ");
                double quantidade = SC.nextDouble();
                SC.nextLine();

                int indicePeca = opcoesPecas.indexOf(idPeca);

                Peca pecaEscolhida = pecas.get(indicePeca);

                if(pecaEscolhida.getEstoque() >= quantidade){
                    var associarPecaDao = new AssociarPecaDAO();
                    try{
                        associarPecaDao.inserirAssociacaoOrdem(new OrdemPeca(idOrdem,idPeca,quantidade));
                        pecas.remove(indicePeca);
                        opcoesPecas.remove(indicePeca);
                    }catch (SQLException e){
                        System.out.println("Erro de conexão com o banco de dados!");
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("estoque insuficiente!");
                    return;
                }
            }else{
                System.out.println("Id da peça invalida!");
            }

        }else{
            System.out.println("Opção invalida!");
        }
    }
}