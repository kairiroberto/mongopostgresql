/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.controle;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import mongodbpostgresql.modelo.Aluno;
import mongodbpostgresql.modelo.AlunoCursoCampusId;
import mongodbpostgresql.modelo.AlunoCursoCampusIdPK;
import mongodbpostgresql.modelo.CursoCampus;
import mongodbpostgresql.modelo.UnidadeOrganizacional;
import mongodbpostgresql.modelo.dao.AlunoCursoCampusIdDAO;
import mongodbpostgresql.modelo.dao.AlunoDAO;
import mongodbpostgresql.modelo.dao.CursoCampusDAO;
import mongodbpostgresql.modelo.dao.UnidadeOrganizacionalDAO;

/**
 *
 * @author JOSE
 */
public class PostgreSQLControle {
    
    SiglasControle siglasControle = new SiglasControle();
    ObjetoMongoControle mongoControle = new ObjetoMongoControle();
    UnidadeOrganizacionalDAO uoControle = new UnidadeOrganizacionalDAO();
    CursoCampusDAO ccControle = new CursoCampusDAO();
    AlunoDAO aControle = new AlunoDAO();
    AlunoCursoCampusIdDAO accidControle = new AlunoCursoCampusIdDAO();
    DBCursor cursor = null;
    
    public String addUnidadeOrganizacional() {
        try {
            if (uoControle.getUnidadeOrganizacionalCount() == 0) {
                cursor = mongoControle.listarObjetos(siglasControle.UODAO);
                for (DBObject o : cursor) {
                    UnidadeOrganizacional uo = new UnidadeOrganizacional();
                    uo.setId(Long.parseLong(o.get("id").toString()));
                    uo.setSigla(o.get("sigla").toString());
                    uoControle.create(uo);
                }
                return "Total: " + uoControle.findUnidadeOrganizacionalEntities().size();
            } else {
                return "Já existem dados cadastrados." + "Total: " + uoControle.findUnidadeOrganizacionalEntities().size();
            }
        } catch (Exception e) {
            return "Erro: " + e.toString();
        } finally {
            //cursor.close();
        }
    }
    
    public String addCursoCampus() {
        try {
            if (ccControle.getCursoCampusCount() == 0) {
                cursor = mongoControle.listarObjetos(siglasControle.CCDAO);
                for (DBObject o : cursor) {
                    CursoCampus cc = new CursoCampus();
                    cc.setId(Long.parseLong(o.get("id").toString()));
                    cc.setDescricaoHistorico(o.get("descricao_historico").toString());
                    UnidadeOrganizacional l = uoControle.findUnidadeOrganizacional(Long.parseLong(o.get("diretoria__setor__uo_id").toString()));
                    cc.setFkUnidadeOrganizacionalId(l);
                    ccControle.create(cc);
                }
                return "Total: " + ccControle.findCursoCampusEntities().size();
            } else {
                return "Já existem dados cadastrados." + "Total: " + ccControle.findCursoCampusEntities().size();
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            //cursor.close();
        }
    }
    
    public String addAluno() {
        try {
            if (aControle.getAlunoCount() == 0) {
                cursor = mongoControle.listarObjetos(siglasControle.ADAO);
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
                    aControle.create(a);
                }
                return "Total: " + aControle.findAlunoEntities().size();
            } else {
                return "Já existem dados cadastrados." + "Total: " + aControle.findAlunoEntities().size();
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            //cursor.close();
        }
    }
    
    public String addAlunoCursoCampusId() {
        try {
            if (accidControle.getAlunoCursoCampusIdCount() == 0) {
                cursor = mongoControle.listarObjetos(siglasControle.ADAO);
                for (DBObject o : cursor) {
                    AlunoCursoCampusId acc = new AlunoCursoCampusId();
                    AlunoCursoCampusIdPK pk = new AlunoCursoCampusIdPK();
                    pk.setFkAlunoId(Long.parseLong(o.get("id").toString()));
                    if (ccControle.findCursoCampus(Long.parseLong(o.get("curso_campus_id").toString())) == null) {
                         CursoCampus cc = new CursoCampus();
                         cc.setId(Long.parseLong(o.get("curso_campus_id").toString()));
                         cc.setDescricaoHistorico("");
                         ccControle.create(cc);
                    }
                    pk.setFkCursoCampusId(Long.parseLong(o.get("curso_campus_id").toString()));
                    acc.setAlunoCursoCampusIdPK(pk);
                    accidControle.create(acc);
                }
                return "Total: " + accidControle.findAlunoCursoCampusIdEntities().size();
            } else {
                return "Já existem dados cadastrados." + "Total: " + accidControle.findAlunoCursoCampusIdEntities().size();
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            //cursor.close();
        }
    }
    
}
