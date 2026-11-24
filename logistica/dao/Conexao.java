/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;

/**
 *
 * @author Thiago
 */
public class Conexao {
    private final String URL = "jdbc:mysql://localhost:3306/logistica";
    private final String USER = "root";
    private final String SENHA = "root";
    
    public Connection conectar() throws SQLException{
        return DriverManager.getConnection(URL, USER, SENHA);
    }
}