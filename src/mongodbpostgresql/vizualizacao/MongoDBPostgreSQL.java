/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.vizualizacao;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import mongodbpostgresql.modelo.dao.AlunoCursoCampusIdDAO;
import mongodbpostgresql.modelo.dao.AlunoDAO;
import mongodbpostgresql.modelo.dao.CursoCampusDAO;
import mongodbpostgresql.controle.ObjetoMongoControle;
import mongodbpostgresql.modelo.dao.UnidadeOrganizacionalDAO;
import mongodbpostgresql.modelo.Aluno;
import mongodbpostgresql.modelo.AlunoCursoCampusId;
import mongodbpostgresql.modelo.AlunoCursoCampusIdPK;
import mongodbpostgresql.modelo.CursoCampus;
import mongodbpostgresql.modelo.UnidadeOrganizacional;

/**
 *
 * @author JOSE
 */
public class MongoDBPostgreSQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(addUnidadeOrganizacional());
        System.out.println(addAluno());
        System.out.println(addCursoCampus());
        System.out.print(addAlunoCursoCampusId());
    }

    private static boolean addUnidadeOrganizacional() {
        try {
            UnidadeOrganizacionalDAO jpaController = new UnidadeOrganizacionalDAO();
            if (jpaController.getUnidadeOrganizacionalCount() == 0) {
                ObjetoMongoControle mongoControle = new ObjetoMongoControle();
                DBCursor cursor = mongoControle.listarObjetos(0);
                for (DBObject o : cursor) {
                    UnidadeOrganizacional uo = new UnidadeOrganizacional();
                    uo.setId(Long.parseLong(o.get("id").toString()));
                    uo.setSigla(o.get("sigla").toString());
                    jpaController.create(uo);
                }
                return true;
            } else {
                System.err.println("Já existem dados cadastrados.");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    private static boolean addCursoCampus() {
        try {
            CursoCampusDAO jpaController = new CursoCampusDAO();
            UnidadeOrganizacionalDAO controller = new UnidadeOrganizacionalDAO();
            if (jpaController.getCursoCampusCount() == 0) {
                ObjetoMongoControle mongoControle = new ObjetoMongoControle();
                DBCursor cursor = mongoControle.listarObjetos(1);
                for (DBObject o : cursor) {
                    CursoCampus cc = new CursoCampus();
                    cc.setId(Long.parseLong(o.get("id").toString()));
                    cc.setDescricaoHistorico(o.get("descricao_historico").toString());
                    UnidadeOrganizacional l = controller.findUnidadeOrganizacional(Long.parseLong(o.get("diretoria__setor__uo_id").toString()));
                    cc.setFkUnidadeOrganizacionalId(l);
                    jpaController.create(cc);
                }
                return true;
            } else {
                System.err.println("Já existem dados cadastrados.");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    private static boolean addAluno() {
        try {
            AlunoDAO jpaController = new AlunoDAO();
            if (jpaController.getAlunoCount() == 0) {
                ObjetoMongoControle mongoControle = new ObjetoMongoControle();
                DBCursor cursor = mongoControle.listarObjetos(2);
                for (DBObject o : cursor) {
                    Aluno a = new Aluno();
                    a.setId(Long.parseLong(o.get("id").toString()));
                    a.setAnoLetivoAno(Integer.parseInt(o.get("ano_letivo__ano").toString()));
                    a.setCep(o.get("cep").toString());
                    a.setCidadeEstadoNome(o.get("cidade__estado__nome").toString());
                    a.setCidadeNome(o.get("cidade__nome").toString());
                    a.setPeriodoLetivo(Integer.parseInt(o.get("periodo_letivo").toString()));
                    String data = o.get("pessoa_fisica__nascimento_data").toString();
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                    Date date = (Date)formatter.parse(data);
                    a.setPessoaFisicaNascData(date);
                    a.setPessoaFisicaSexo(o.get("pessoa_fisica__sexo").toString());
                    jpaController.create(a);
                }
                return true;
            } else {
                System.err.println("Já existem dados cadastrados.");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    private static boolean addAlunoCursoCampusId() {
        try {
            AlunoCursoCampusIdDAO jpaController = new AlunoCursoCampusIdDAO();
            CursoCampusDAO cursoCampusController = new CursoCampusDAO();
            if (jpaController.getAlunoCursoCampusIdCount() == 0) {
                ObjetoMongoControle mongoControle = new ObjetoMongoControle();
                DBCursor cursor = mongoControle.listarObjetos(2);
                for (DBObject o : cursor) {
                    AlunoCursoCampusId acc = new AlunoCursoCampusId();
                    AlunoCursoCampusIdPK pk = new AlunoCursoCampusIdPK();
                    pk.setFkAlunoId(Long.parseLong(o.get("id").toString()));
                    if (cursoCampusController.findCursoCampus(Long.parseLong(o.get("curso_campus_id").toString())) == null) {
                         CursoCampus cc = new CursoCampus();
                         cc.setId(Long.parseLong(o.get("curso_campus_id").toString()));
                         cc.setDescricaoHistorico("");
                         cursoCampusController.create(cc);
                    }
                    pk.setFkCursoCampusId(Long.parseLong(o.get("curso_campus_id").toString()));
                    acc.setAlunoCursoCampusIdPK(pk);
                    jpaController.create(acc);
                }
                return true;
            } else {
                System.err.println("Já existem dados cadastrados.");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    

}
