/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOSE
 */
@Entity
@Table(name = "aluno_curso_campus_id")
@XmlRootElement
public class AlunoCursoCampusId implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private AlunoCursoCampusIdPK alunoCursoCampusIdPK;

    public AlunoCursoCampusIdPK getAlunoCursoCampusIdPK() {
        return alunoCursoCampusIdPK;
    }

    public void setAlunoCursoCampusIdPK(AlunoCursoCampusIdPK alunoCursoCampusIdPK) {
        this.alunoCursoCampusIdPK = alunoCursoCampusIdPK;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.alunoCursoCampusIdPK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlunoCursoCampusId other = (AlunoCursoCampusId) obj;
        if (!Objects.equals(this.alunoCursoCampusIdPK, other.alunoCursoCampusIdPK)) {
            return false;
        }
        return true;
    }        
    
}
