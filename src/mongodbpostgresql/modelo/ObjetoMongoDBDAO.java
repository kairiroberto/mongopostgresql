/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.modelo;

import com.mongodb.DBCursor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JOSE
 */
public class ObjetoMongoDBDAO {

    private String bd;
    private String collection;

    public ObjetoMongoDBDAO(String bd, String collection) {
        this.bd = bd;
        this.collection = collection;
    }

    public DBCursor listarObjetos() {
        DBCursor cursor = null;
        try {
            cursor = ConexaoMongoDB.db(bd).getCollection(collection).find();
            List<Object> list = new ArrayList<Object>();
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }
            //JOptionPane.showMessageDialog(null, "Dados lidos com sucesso!");
            return cursor;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }

}
