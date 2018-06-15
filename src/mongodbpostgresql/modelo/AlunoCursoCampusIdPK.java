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
import javax.persistence.Embeddable;
import javax.persistence.Id;

/**
 *
 * @author JOSE
 */
@Embeddable
public class AlunoCursoCampusIdPK implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "fk_aluno_id")
    private Long fkAlunoId;
    
    @Basic(optional = false)
    @Column(name = "fk_curso_campus_id")
    private Long fkCursoCampusId;

    public Long getFkAlunoId() {
        return fkAlunoId;
    }

    public void setFkAlunoId(Long fkAlunoId) {
        this.fkAlunoId = fkAlunoId;
    }

    public Long getFkCursoCampusId() {
        return fkCursoCampusId;
    }

    public void setFkCursoCampusId(Long fkCursoCampusId) {
        this.fkCursoCampusId = fkCursoCampusId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.fkAlunoId);
        hash = 19 * hash + Objects.hashCode(this.fkCursoCampusId);
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
        final AlunoCursoCampusIdPK other = (AlunoCursoCampusIdPK) obj;
        if (!Objects.equals(this.fkAlunoId, other.fkAlunoId)) {
            return false;
        }
        if (!Objects.equals(this.fkCursoCampusId, other.fkCursoCampusId)) {
            return false;
        }
        return true;
    }
    
}
