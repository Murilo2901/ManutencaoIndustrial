package org.example.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String URL = "jdbc:mysql://localhost:3306/ManutencaoIndustrial?useSSL=false&serverTimezone=UTC";
    private static String USER = "root";
    private static String PASSWORD = "mysqlPW";

    public static Connection conectar()throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public static void texto(String texto){
        System.out.println("\n" + texto);
    }

    public static void cabecalho(String texto){
        System.out.println(texto);
    }

    public static void opcao(){
        System.out.print("> ");
    }
}

