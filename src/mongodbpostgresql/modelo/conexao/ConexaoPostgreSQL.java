/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author JOSE
 */
public class ConexaoPostgreSQL {

    private final String driver = "org.postgresql.Driver";
    private final String user = "postgres";
    private final String password = "12345";
    private final String url = "jdbc:postgresql://localhost:5432/suap";

    private static Connection connection = null;
    private static ConexaoPostgreSQL conexaoPostgreSQL = null;
    private static Statement statement = null;

    public ConexaoPostgreSQL() {
    }

    public ConexaoPostgreSQL getInstance() {
        if (conexaoPostgreSQL == null) {
            conexaoPostgreSQL = new ConexaoPostgreSQL();
        }
        return conexaoPostgreSQL;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = (Connection) DriverManager.getConnection(url, user, password);
                return connection;
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
                return null;
            }
        }
        return null;
    }
    
    public Statement getStatement() {
        if (statement == null) {
            try {
                statement = getConnection().createStatement();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
                return null;
            }
        }
        return null;
    }

}
