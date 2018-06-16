/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.vizualizacao;

import mongodbpostgresql.controle.PostgreSQLControle;
/**
 *
 * @author JOSE
 */
public class MongoDBPostgreSQL {
    
    private static PostgreSQLControle controle = new PostgreSQLControle();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(controle.addUnidadeOrganizacional());
        System.out.println(controle.addAluno());
        System.out.println(controle.addCursoCampus());
        System.out.println(controle.addAlunoCursoCampusId());
    } 

}
