/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author luanl
 */
@Entity
@Table(name = "tipohabilitacao")
@NamedQueries({
    @NamedQuery(name = "Tipohabilitacao.findAll", query = "SELECT t FROM Tipohabilitacao t"),
    @NamedQuery(name = "Tipohabilitacao.findByIdTipoHabilitacao", query = "SELECT t FROM Tipohabilitacao t WHERE t.idTipoHabilitacao = :idTipoHabilitacao"),
    @NamedQuery(name = "Tipohabilitacao.findByTipoA", query = "SELECT t FROM Tipohabilitacao t WHERE t.tipoA = :tipoA"),
    @NamedQuery(name = "Tipohabilitacao.findByTipoB", query = "SELECT t FROM Tipohabilitacao t WHERE t.tipoB = :tipoB"),
    @NamedQuery(name = "Tipohabilitacao.findByTipoC", query = "SELECT t FROM Tipohabilitacao t WHERE t.tipoC = :tipoC"),
    @NamedQuery(name = "Tipohabilitacao.findByTipoD", query = "SELECT t FROM Tipohabilitacao t WHERE t.tipoD = :tipoD"),
    @NamedQuery(name = "Tipohabilitacao.findByTipoE", query = "SELECT t FROM Tipohabilitacao t WHERE t.tipoE = :tipoE")})
public class Tipohabilitacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoHabilitacao")
    private Integer idTipoHabilitacao;
    @Basic(optional = false)
    @Column(name = "tipoA")
    private boolean tipoA;
    @Basic(optional = false)
    @Column(name = "tipoB")
    private boolean tipoB;
    @Basic(optional = false)
    @Column(name = "tipoC")
    private boolean tipoC;
    @Basic(optional = false)
    @Column(name = "tipoD")
    private boolean tipoD;
    @Basic(optional = false)
    @Column(name = "tipoE")
    private boolean tipoE;
    @JoinColumn(name = "idCondutorHabilitacao", referencedColumnName = "idCondutor")
    @ManyToOne(optional = false)
    private Condutor idCondutorHabilitacao;

    public Tipohabilitacao() {
    }

    public Tipohabilitacao(Integer idTipoHabilitacao) {
        this.idTipoHabilitacao = idTipoHabilitacao;
    }

    public Tipohabilitacao(Integer idTipoHabilitacao, boolean tipoA, boolean tipoB, boolean tipoC, boolean tipoD, boolean tipoE) {
        this.idTipoHabilitacao = idTipoHabilitacao;
        this.tipoA = tipoA;
        this.tipoB = tipoB;
        this.tipoC = tipoC;
        this.tipoD = tipoD;
        this.tipoE = tipoE;
    }

    public Integer getIdTipoHabilitacao() {
        return idTipoHabilitacao;
    }

    public void setIdTipoHabilitacao(Integer idTipoHabilitacao) {
        this.idTipoHabilitacao = idTipoHabilitacao;
    }

    public boolean getTipoA() {
        return tipoA;
    }

    public void setTipoA(boolean tipoA) {
        this.tipoA = tipoA;
    }

    public boolean getTipoB() {
        return tipoB;
    }

    public void setTipoB(boolean tipoB) {
        this.tipoB = tipoB;
    }

    public boolean getTipoC() {
        return tipoC;
    }

    public void setTipoC(boolean tipoC) {
        this.tipoC = tipoC;
    }

    public boolean getTipoD() {
        return tipoD;
    }

    public void setTipoD(boolean tipoD) {
        this.tipoD = tipoD;
    }

    public boolean getTipoE() {
        return tipoE;
    }

    public void setTipoE(boolean tipoE) {
        this.tipoE = tipoE;
    }

    public Condutor getIdCondutorHabilitacao() {
        return idCondutorHabilitacao;
    }

    public void setIdCondutorHabilitacao(Condutor idCondutorHabilitacao) {
        this.idCondutorHabilitacao = idCondutorHabilitacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoHabilitacao != null ? idTipoHabilitacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipohabilitacao)) {
            return false;
        }
        Tipohabilitacao other = (Tipohabilitacao) object;
        if ((this.idTipoHabilitacao == null && other.idTipoHabilitacao != null) || (this.idTipoHabilitacao != null && !this.idTipoHabilitacao.equals(other.idTipoHabilitacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tipohabilitacao[ idTipoHabilitacao=" + idTipoHabilitacao + " ]";
    }
    
}
