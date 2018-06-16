/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.modelo.dao;

import mongodbpostgresql.modelo.conexao.ConexaoPostgreSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author JOSE
 */
public class ObjetoPostegreSQLDAO {
    
    private ConexaoPostgreSQL conexaoPostgreSQL;
    
    public ObjetoPostegreSQLDAO() {
    }
    
    public List<Object> listarObjetos() {
        try {
            Statement statement = conexaoPostgreSQL.getStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM unidade_organizacional");
            return (List<Object>) resultSet;
        } catch (SQLException ex) {
            return null;
            
        }
    }
    
}
