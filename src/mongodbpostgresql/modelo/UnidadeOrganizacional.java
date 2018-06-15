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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOSE
 */
@Entity
@Table(name = "unidade_organizacional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadeOrganizacional.findAll", query = "SELECT u FROM UnidadeOrganizacional u")
    , @NamedQuery(name = "UnidadeOrganizacional.findById", query = "SELECT u FROM UnidadeOrganizacional u WHERE u.id = :id")
    , @NamedQuery(name = "UnidadeOrganizacional.findBySigla", query = "SELECT u FROM UnidadeOrganizacional u WHERE u.sigla = :sigla")})
public class UnidadeOrganizacional implements Serializable {

    @OneToMany(mappedBy = "fkUnidadeOrganizacionalId")
    private Collection<CursoCampus> cursoCampusCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "sigla")
    private String sigla;

    public UnidadeOrganizacional() {
    }

    public UnidadeOrganizacional(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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
        if (!(object instanceof UnidadeOrganizacional)) {
            return false;
        }
        UnidadeOrganizacional other = (UnidadeOrganizacional) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mongodbpostgresql.modelo.UnidadeOrganizacional[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<CursoCampus> getCursoCampusCollection() {
        return cursoCampusCollection;
    }

    public void setCursoCampusCollection(Collection<CursoCampus> cursoCampusCollection) {
        this.cursoCampusCollection = cursoCampusCollection;
    }
    
}
