/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author erick.costa
 */
public class Conexao {
    private final static String URL = "jdbc:mysql://localhost:3306/comercio";
    private final static String USER = "root";
    private final static String PASS = "";
    private static Connection conexao;

    public static Connection getConexao() {
        try {
            if (conexao == null) {
                conexao = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conectou....");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conexao;
    }
}
