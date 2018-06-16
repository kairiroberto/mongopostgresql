/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.controle;

import com.mongodb.DBCursor;
import mongodbpostgresql.modelo.dao.ObjetoMongoDBDAO;

/**
 *
 * @author JOSE
 */
public class ObjetoMongoControle {

    SiglasControle siglasControle = new SiglasControle();
    ObjetoMongoDBDAO edao = new ObjetoMongoDBDAO("suap", "Encaminhamento");
    ObjetoMongoDBDAO uodao = new ObjetoMongoDBDAO("suap", "UnidadeOrganizacional");
    ObjetoMongoDBDAO ccdao = new ObjetoMongoDBDAO("suap", "CursoCampus");
    ObjetoMongoDBDAO adao = new ObjetoMongoDBDAO("suap", "Aluno");
    ObjetoMongoDBDAO aedao = new ObjetoMongoDBDAO("suap", "AcompanhamentoEncaminhamento");
    ObjetoMongoDBDAO mpdao = new ObjetoMongoDBDAO("suap", "MatriculaPeriodo");
    ObjetoMongoDBDAO smdao = new ObjetoMongoDBDAO("suap", "SituacaoMatircula");
    ObjetoMongoDBDAO smpdao = new ObjetoMongoDBDAO("suap", "SituacaoMatriculaPeriodo");
    ObjetoMongoDBDAO sddao = new ObjetoMongoDBDAO("suap", "SituacaoDisciplina");
    ObjetoMongoDBDAO ddao = new ObjetoMongoDBDAO("suap", "Disciplina");
    ObjetoMongoDBDAO pdao = new ObjetoMongoDBDAO("suap", "Professor");
    ObjetoMongoDBDAO ndao = new ObjetoMongoDBDAO("suap", "Notas");

    public DBCursor listarObjetos(int i) {
        DBCursor cursor = null;
        final int UODAO = 0;
        final int CCDAO = 1;
        final int ADAO = 2;
        final int AEDAO = 3;
        final int EDAO = 4;
        final int MPDAO = 5;
        final int SMDAO = 6;
        final int SMPDAO = 7;
        final int SDDAO = 8;
        final int DDAO = 9;
        final int PDAO = 10;
        final int NDAO = 11;
        switch (i) {
            case UODAO:
                cursor = uodao.listarObjetos();
                break;
            case CCDAO:
                cursor = ccdao.listarObjetos();
                break;
            case ADAO:
                cursor = adao.listarObjetos();
                break;
            case AEDAO:
                cursor = aedao.listarObjetos();
                break;
            case EDAO:
                cursor = edao.listarObjetos();
                break;
            case MPDAO:
                cursor = mpdao.listarObjetos();
                break;
            case SMDAO:
                cursor = smdao.listarObjetos();
                break;
            case SMPDAO:
                cursor = smpdao.listarObjetos();
                break;
            case SDDAO:
                cursor = sddao.listarObjetos();
                break;
            case DDAO:
                cursor = ddao.listarObjetos();
                break;
            case PDAO:
                cursor = pdao.listarObjetos();
                break;
            case NDAO:
                cursor = ndao.listarObjetos();
                break;
        }
        return cursor;
    }

}
