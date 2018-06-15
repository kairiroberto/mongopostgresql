/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOSE
 */
@Entity
@Table(name = "aluno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a")
    , @NamedQuery(name = "Aluno.findById", query = "SELECT a FROM Aluno a WHERE a.id = :id")
    , @NamedQuery(name = "Aluno.findByCidadeNome", query = "SELECT a FROM Aluno a WHERE a.cidadeNome = :cidadeNome")
    , @NamedQuery(name = "Aluno.findByPeriodoLetivo", query = "SELECT a FROM Aluno a WHERE a.periodoLetivo = :periodoLetivo")
    , @NamedQuery(name = "Aluno.findByCidadeEstadoNome", query = "SELECT a FROM Aluno a WHERE a.cidadeEstadoNome = :cidadeEstadoNome")
    , @NamedQuery(name = "Aluno.findByAnoLetivoAno", query = "SELECT a FROM Aluno a WHERE a.anoLetivoAno = :anoLetivoAno")
    , @NamedQuery(name = "Aluno.findByPessoaFisicaSexo", query = "SELECT a FROM Aluno a WHERE a.pessoaFisicaSexo = :pessoaFisicaSexo")
    , @NamedQuery(name = "Aluno.findByPessoaFisicaNascData", query = "SELECT a FROM Aluno a WHERE a.pessoaFisicaNascData = :pessoaFisicaNascData")
    , @NamedQuery(name = "Aluno.findByCep", query = "SELECT a FROM Aluno a WHERE a.cep = :cep")})
public class Aluno implements Serializable {

    @ManyToMany(mappedBy = "alunoCollection")
    private Collection<CursoCampus> cursoCampusCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "cidade_nome")
    private String cidadeNome;
    @Column(name = "periodo_letivo")
    private Integer periodoLetivo;
    @Column(name = "cidade_estado_nome")
    private String cidadeEstadoNome;
    @Column(name = "ano_letivo_ano")
    private Integer anoLetivoAno;
    @Column(name = "pessoa_fisica_sexo")
    private String pessoaFisicaSexo;
    @Column(name = "pessoa_fisica_nasc_data")
    @Temporal(TemporalType.DATE)
    private Date pessoaFisicaNascData;
    @Column(name = "cep")
    private String cep;

    public Aluno() {
    }

    public Aluno(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidadeNome() {
        return cidadeNome;
    }

    public void setCidadeNome(String cidadeNome) {
        this.cidadeNome = cidadeNome;
    }

    public Integer getPeriodoLetivo() {
        return periodoLetivo;
    }

    public void setPeriodoLetivo(Integer periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }

    public String getCidadeEstadoNome() {
        return cidadeEstadoNome;
    }

    public void setCidadeEstadoNome(String cidadeEstadoNome) {
        this.cidadeEstadoNome = cidadeEstadoNome;
    }

    public Integer getAnoLetivoAno() {
        return anoLetivoAno;
    }

    public void setAnoLetivoAno(Integer anoLetivoAno) {
        this.anoLetivoAno = anoLetivoAno;
    }

    public String getPessoaFisicaSexo() {
        return pessoaFisicaSexo;
    }

    public void setPessoaFisicaSexo(String pessoaFisicaSexo) {
        this.pessoaFisicaSexo = pessoaFisicaSexo;
    }

    public Date getPessoaFisicaNascData() {
        return pessoaFisicaNascData;
    }

    public void setPessoaFisicaNascData(Date pessoaFisicaNascData) {
        this.pessoaFisicaNascData = pessoaFisicaNascData;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mongodbpostgresql.modelo.Aluno[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<CursoCampus> getCursoCampusCollection() {
        return cursoCampusCollection;
    }

    public void setCursoCampusCollection(Collection<CursoCampus> cursoCampusCollection) {
        this.cursoCampusCollection = cursoCampusCollection;
    }
    
}
