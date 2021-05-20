/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luanl
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
    @NamedQuery(name = "Locacao.findBySeguro", query = "SELECT l FROM Locacao l WHERE l.seguro = :seguro"),
    @NamedQuery(name = "Locacao.findByAcresimo", query = "SELECT l FROM Locacao l WHERE l.acresimo = :acresimo"),
    @NamedQuery(name = "Locacao.findByTexto", query = "SELECT l FROM Locacao l WHERE l.texto = :texto"),
    @NamedQuery(name = "Locacao.findByDiarias", query = "SELECT l FROM Locacao l WHERE l.diarias = :diarias"),
    @NamedQuery(name = "Locacao.findBySemanas", query = "SELECT l FROM Locacao l WHERE l.semanas = :semanas"),
    @NamedQuery(name = "Locacao.findByQuinzenas", query = "SELECT l FROM Locacao l WHERE l.quinzenas = :quinzenas"),
    @NamedQuery(name = "Locacao.findByMeses", query = "SELECT l FROM Locacao l WHERE l.meses = :meses"),
    @NamedQuery(name = "Locacao.findByDataEmprestimo", query = "SELECT l FROM Locacao l WHERE l.dataEmprestimo = :dataEmprestimo")})
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
    @Column(name = "seguro")
    private double seguro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "acresimo")
    private Double acresimo;
    @Column(name = "texto")
    private String texto;
    @Basic(optional = false)
    @Column(name = "diarias")
    private int diarias;
    @Basic(optional = false)
    @Column(name = "semanas")
    private int semanas;
    @Basic(optional = false)
    @Column(name = "quinzenas")
    private int quinzenas;
    @Basic(optional = false)
    @Column(name = "meses")
    private int meses;
    @Basic(optional = false)
    @Column(name = "dataEmprestimo")
    @Temporal(TemporalType.DATE)
    private Date dataEmprestimo;
    @JoinColumn(name = "idCondutorLocacao", referencedColumnName = "idCondutor")
    @ManyToOne(optional = false)
    private Condutor idCondutorLocacao;
    @JoinColumn(name = "idVeiculo", referencedColumnName = "idVeiculo")
    @ManyToOne(optional = false)
    private Veiculo idVeiculo;

    public Locacao() {
    }

    public Locacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Locacao(Integer idLocacao, boolean contratoAberto, double diaria, double semanal, double quinzenal, double mensal, double seguro, int diarias, int semanas, int quinzenas, int meses, Date dataEmprestimo) {
        this.idLocacao = idLocacao;
        this.contratoAberto = contratoAberto;
        this.diaria = diaria;
        this.semanal = semanal;
        this.quinzenal = quinzenal;
        this.mensal = mensal;
        this.seguro = seguro;
        this.diarias = diarias;
        this.semanas = semanas;
        this.quinzenas = quinzenas;
        this.meses = meses;
        this.dataEmprestimo = dataEmprestimo;
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

    public double getSeguro() {
        return seguro;
    }

    public void setSeguro(double seguro) {
        this.seguro = seguro;
    }

    public Double getAcresimo() {
        return acresimo;
    }

    public void setAcresimo(Double acresimo) {
        this.acresimo = acresimo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getDiarias() {
        return diarias;
    }

    public void setDiarias(int diarias) {
        this.diarias = diarias;
    }

    public int getSemanas() {
        return semanas;
    }

    public void setSemanas(int semanas) {
        this.semanas = semanas;
    }

    public int getQuinzenas() {
        return quinzenas;
    }

    public void setQuinzenas(int quinzenas) {
        this.quinzenas = quinzenas;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Condutor getIdCondutorLocacao() {
        return idCondutorLocacao;
    }

    public void setIdCondutorLocacao(Condutor idCondutorLocacao) {
        this.idCondutorLocacao = idCondutorLocacao;
    }

    public Veiculo getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Veiculo idVeiculo) {
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
