/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.modelo.conexao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import javax.swing.JOptionPane;

/**
 *
 * @author JOSE
 */
public class ConexaoMongoDB {

    private static final String HOST = "localhost";
    private static final int PORT = 27017;

    private static MongoClient mongoClient = null;
    private static ConexaoMongoDB conexaoMongoDB = null;
    
    private ConexaoMongoDB() {
    }
    
    public ConexaoMongoDB getInstance() {
        if (conexaoMongoDB == null) {
            conexaoMongoDB = new ConexaoMongoDB();
        }
        return conexaoMongoDB;
    }

    public static MongoClient getMongoClient() {
        try {
            if (mongoClient == null) {
                mongoClient = new MongoClient(HOST, PORT);
            }
            //JOptionPane.showMessageDialog(null, "Conectado ao mongodb com sucesso!");
            return mongoClient;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }
    
    public static DB db(String db_name) {
        DB db = null;
        try {
            db = getMongoClient().getDB(db_name);
            //JOptionPane.showMessageDialog(null, "Conectado a collection com sucesso!");
            return db;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }

}
