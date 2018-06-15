/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOSE
 */
@Entity
@Table(name = "curso_campus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoCampus.findAll", query = "SELECT c FROM CursoCampus c")
    , @NamedQuery(name = "CursoCampus.findById", query = "SELECT c FROM CursoCampus c WHERE c.id = :id")
    , @NamedQuery(name = "CursoCampus.findByDescricaoHistorico", query = "SELECT c FROM CursoCampus c WHERE c.descricaoHistorico = :descricaoHistorico")})
public class CursoCampus implements Serializable {

    @JoinTable(name = "aluno_curso_campus_id", joinColumns = {
        @JoinColumn(name = "fk_curso_campus_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_aluno_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Aluno> alunoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "descricao_historico")
    private String descricaoHistorico;
    @JoinColumn(name = "fk_unidade_organizacional_id", referencedColumnName = "id")
    @ManyToOne
    private UnidadeOrganizacional fkUnidadeOrganizacionalId;

    public CursoCampus() {
    }

    public CursoCampus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoHistorico() {
        return descricaoHistorico;
    }

    public void setDescricaoHistorico(String descricaoHistorico) {
        this.descricaoHistorico = descricaoHistorico;
    }

    public UnidadeOrganizacional getFkUnidadeOrganizacionalId() {
        return fkUnidadeOrganizacionalId;
    }

    public void setFkUnidadeOrganizacionalId(UnidadeOrganizacional fkUnidadeOrganizacionalId) {
        this.fkUnidadeOrganizacionalId = fkUnidadeOrganizacionalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoCampus)) {
            return false;
        }
        CursoCampus other = (CursoCampus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mongodbpostgresql.modelo.CursoCampus[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Aluno> getAlunoCollection() {
        return alunoCollection;
    }

    public void setAlunoCollection(Collection<Aluno> alunoCollection) {
        this.alunoCollection = alunoCollection;
    }
    
}
