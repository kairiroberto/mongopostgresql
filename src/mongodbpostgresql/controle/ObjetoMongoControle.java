/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.controle;

import com.mongodb.DBCursor;
import java.util.ArrayList;
import java.util.List;
import mongodbpostgresql.modelo.ObjetoMongoDBDAO;

/**
 *
 * @author JOSE
 */
public class ObjetoMongoControle {
    
    ObjetoMongoDBDAO uodao = new ObjetoMongoDBDAO("suap", "UnidadeOrganizacional");
    ObjetoMongoDBDAO ccdao = new ObjetoMongoDBDAO("suap", "CursoCampus");
    ObjetoMongoDBDAO adao = new ObjetoMongoDBDAO("suap", "Aluno");
    ObjetoMongoDBDAO aedao = new ObjetoMongoDBDAO("suap", "AcompanhamentoEncaminhamento");
    ObjetoMongoDBDAO edao = new ObjetoMongoDBDAO("suap", "Encaminhamento");
    ObjetoMongoDBDAO mpdao = new ObjetoMongoDBDAO("suap", "MatriculaPeriodo");
    ObjetoMongoDBDAO smdao = new ObjetoMongoDBDAO("suap", "MatriculaMatircula");
    ObjetoMongoDBDAO smpdao = new ObjetoMongoDBDAO("suap", "SituacaoMatriculaPeriodo");
    ObjetoMongoDBDAO sddao = new ObjetoMongoDBDAO("suap", "SituacaoDisciplina");
    ObjetoMongoDBDAO ddao = new ObjetoMongoDBDAO("suap", "Disciplina");
    ObjetoMongoDBDAO pdao = new ObjetoMongoDBDAO("suap", "Professor");
    ObjetoMongoDBDAO ndao = new ObjetoMongoDBDAO("suap", "Notas");
    
    
    public DBCursor listarObjetos(int i) {
        DBCursor cursor = null;
        switch (i) {
            case 0:
                cursor = uodao.listarObjetos();
                break;
            case 1:
                cursor = ccdao.listarObjetos();
                break;
            case 2:
                cursor = adao.listarObjetos();
                break;
            case 3:
                cursor = aedao.listarObjetos();
                break;
            case 4:
                cursor = edao.listarObjetos();
                break;
            case 5:
                cursor = mpdao.listarObjetos();
                break;
            case 6:
                cursor = smdao.listarObjetos();
                break;
            case 7:
                cursor = smpdao.listarObjetos();
                break;
            case 8:
                cursor = sddao.listarObjetos();
                break;
            case 9:
                cursor = ddao.listarObjetos();
                break;
            case 10:
                cursor = pdao.listarObjetos();
                break;
            case 11:
                cursor = ndao.listarObjetos();
                break;
        }
        return cursor;
    }
    
}
