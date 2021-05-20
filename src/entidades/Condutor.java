/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author guilherme.santos
 */
@Entity
@Table(name = "condutor")
@NamedQueries({
    @NamedQuery(name = "Condutor.findAll", query = "SELECT c FROM Condutor c"),
    @NamedQuery(name = "Condutor.findByIdCondutor", query = "SELECT c FROM Condutor c WHERE c.idCondutor = :idCondutor"),
    @NamedQuery(name = "Condutor.findByNumeroHabilitacao", query = "SELECT c FROM Condutor c WHERE c.numeroHabilitacao = :numeroHabilitacao"),
    @NamedQuery(name = "Condutor.findByTelefone1", query = "SELECT c FROM Condutor c WHERE c.telefone1 = :telefone1"),
    @NamedQuery(name = "Condutor.findByTelefone2", query = "SELECT c FROM Condutor c WHERE c.telefone2 = :telefone2"),
    @NamedQuery(name = "Condutor.findByEmail", query = "SELECT c FROM Condutor c WHERE c.email = :email")})
public class Condutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCondutor")
    private Integer idCondutor;
    @Basic(optional = false)
    @Column(name = "numeroHabilitacao")
    private String numeroHabilitacao;
    @Basic(optional = false)
    @Column(name = "telefone1")
    private String telefone1;
    @Column(name = "telefone2")
    private String telefone2;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondutorHabilitacao")
    private Collection<Tipohabilitacao> tipohabilitacaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondutorLocacao")
    private Collection<Locacao> locacaoCollection;

    public Condutor() {
    }

    public Condutor(Integer idCondutor) {
        this.idCondutor = idCondutor;
    }

    public Condutor(Integer idCondutor, String numeroHabilitacao, String telefone1, String email) {
        this.idCondutor = idCondutor;
        this.numeroHabilitacao = numeroHabilitacao;
        this.telefone1 = telefone1;
        this.email = email;
    }

    public Integer getIdCondutor() {
        return idCondutor;
    }

    public void setIdCondutor(Integer idCondutor) {
        this.idCondutor = idCondutor;
    }

    public String getNumeroHabilitacao() {
        return numeroHabilitacao;
    }

    public void setNumeroHabilitacao(String numeroHabilitacao) {
        this.numeroHabilitacao = numeroHabilitacao;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Collection<Tipohabilitacao> getTipohabilitacaoCollection() {
        return tipohabilitacaoCollection;
    }

    public void setTipohabilitacaoCollection(Collection<Tipohabilitacao> tipohabilitacaoCollection) {
        this.tipohabilitacaoCollection = tipohabilitacaoCollection;
    }

    public Collection<Locacao> getLocacaoCollection() {
        return locacaoCollection;
    }

    public void setLocacaoCollection(Collection<Locacao> locacaoCollection) {
        this.locacaoCollection = locacaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCondutor != null ? idCondutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condutor)) {
            return false;
        }
        Condutor other = (Condutor) object;
        if ((this.idCondutor == null && other.idCondutor != null) || (this.idCondutor != null && !this.idCondutor.equals(other.idCondutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Condutor[ idCondutor=" + idCondutor + " ]";
    }
    
}
