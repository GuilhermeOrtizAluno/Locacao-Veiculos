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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author guilherme.santos
 */
@Entity
@Table(name = "locacao")
@NamedQueries({
    @NamedQuery(name = "Locacao.findAll", query = "SELECT l FROM Locacao l"),
    @NamedQuery(name = "Locacao.findByIdLocacao", query = "SELECT l FROM Locacao l WHERE l.idLocacao = :idLocacao"),
    @NamedQuery(name = "Locacao.findByContratoAberto", query = "SELECT l FROM Locacao l WHERE l.contratoAberto = :contratoAberto"),
    @NamedQuery(name = "Locacao.findByDiaria", query = "SELECT l FROM Locacao l WHERE l.diaria = :diaria"),
    @NamedQuery(name = "Locacao.findBySemanal", query = "SELECT l FROM Locacao l WHERE l.semanal = :semanal"),
    @NamedQuery(name = "Locacao.findByQuinzenal", query = "SELECT l FROM Locacao l WHERE l.quinzenal = :quinzenal"),
    @NamedQuery(name = "Locacao.findByMensal", query = "SELECT l FROM Locacao l WHERE l.mensal = :mensal"),
    @NamedQuery(name = "Locacao.findByHora", query = "SELECT l FROM Locacao l WHERE l.hora = :hora"),
    @NamedQuery(name = "Locacao.findByHoraExcedente", query = "SELECT l FROM Locacao l WHERE l.horaExcedente = :horaExcedente"),
    @NamedQuery(name = "Locacao.findBySeguro", query = "SELECT l FROM Locacao l WHERE l.seguro = :seguro"),
    @NamedQuery(name = "Locacao.findByAcresimo", query = "SELECT l FROM Locacao l WHERE l.acresimo = :acresimo"),
    @NamedQuery(name = "Locacao.findByTexto", query = "SELECT l FROM Locacao l WHERE l.texto = :texto"),
    @NamedQuery(name = "Locacao.findByIdCondutorLocacao", query = "SELECT l FROM Locacao l WHERE l.idCondutorLocacao = :idCondutorLocacao"),
    @NamedQuery(name = "Locacao.findByIdVeiculo", query = "SELECT l FROM Locacao l WHERE l.idVeiculo = :idVeiculo")})
public class Locacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLocacao")
    private Integer idLocacao;
    @Basic(optional = false)
    @Column(name = "contratoAberto")
    private boolean contratoAberto;
    @Basic(optional = false)
    @Column(name = "diaria")
    private double diaria;
    @Basic(optional = false)
    @Column(name = "semanal")
    private double semanal;
    @Basic(optional = false)
    @Column(name = "quinzenal")
    private double quinzenal;
    @Basic(optional = false)
    @Column(name = "mensal")
    private double mensal;
    @Basic(optional = false)
    @Column(name = "hora")
    private double hora;
    @Basic(optional = false)
    @Column(name = "horaExcedente")
    private double horaExcedente;
    @Basic(optional = false)
    @Column(name = "seguro")
    private double seguro;
    @Basic(optional = false)
    @Column(name = "acresimo")
    private double acresimo;
    @Basic(optional = false)
    @Column(name = "texto")
    private String texto;
    @Basic(optional = false)
    @Column(name = "idCondutorLocacao")
    private int idCondutorLocacao;
    @Basic(optional = false)
    @Column(name = "idVeiculo")
    private int idVeiculo;

    public Locacao() {
    }

    public Locacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Locacao(Integer idLocacao, boolean contratoAberto, double diaria, double semanal, double quinzenal, double mensal, double hora, double horaExcedente, double seguro, double acresimo, String texto, int idCondutorLocacao, int idVeiculo) {
        this.idLocacao = idLocacao;
        this.contratoAberto = contratoAberto;
        this.diaria = diaria;
        this.semanal = semanal;
        this.quinzenal = quinzenal;
        this.mensal = mensal;
        this.hora = hora;
        this.horaExcedente = horaExcedente;
        this.seguro = seguro;
        this.acresimo = acresimo;
        this.texto = texto;
        this.idCondutorLocacao = idCondutorLocacao;
        this.idVeiculo = idVeiculo;
    }

    public Integer getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public boolean getContratoAberto() {
        return contratoAberto;
    }

    public void setContratoAberto(boolean contratoAberto) {
        this.contratoAberto = contratoAberto;
    }

    public double getDiaria() {
        return diaria;
    }

    public void setDiaria(double diaria) {
        this.diaria = diaria;
    }

    public double getSemanal() {
        return semanal;
    }

    public void setSemanal(double semanal) {
        this.semanal = semanal;
    }

    public double getQuinzenal() {
        return quinzenal;
    }

    public void setQuinzenal(double quinzenal) {
        this.quinzenal = quinzenal;
    }

    public double getMensal() {
        return mensal;
    }

    public void setMensal(double mensal) {
        this.mensal = mensal;
    }

    public double getHora() {
        return hora;
    }

    public void setHora(double hora) {
        this.hora = hora;
    }

    public double getHoraExcedente() {
        return horaExcedente;
    }

    public void setHoraExcedente(double horaExcedente) {
        this.horaExcedente = horaExcedente;
    }

    public double getSeguro() {
        return seguro;
    }

    public void setSeguro(double seguro) {
        this.seguro = seguro;
    }

    public double getAcresimo() {
        return acresimo;
    }

    public void setAcresimo(double acresimo) {
        this.acresimo = acresimo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getIdCondutorLocacao() {
        return idCondutorLocacao;
    }

    public void setIdCondutorLocacao(int idCondutorLocacao) {
        this.idCondutorLocacao = idCondutorLocacao;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocacao != null ? idLocacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locacao)) {
            return false;
        }
        Locacao other = (Locacao) object;
        if ((this.idLocacao == null && other.idLocacao != null) || (this.idLocacao != null && !this.idLocacao.equals(other.idLocacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Locacao[ idLocacao=" + idLocacao + " ]";
    }
    
}
