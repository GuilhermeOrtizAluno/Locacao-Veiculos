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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author guilherme.santos
 */
@Entity
@Table(name = "veiculo")
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v"),
    @NamedQuery(name = "Veiculo.findByIdVeiculo", query = "SELECT v FROM Veiculo v WHERE v.idVeiculo = :idVeiculo"),
    @NamedQuery(name = "Veiculo.findByRenavam", query = "SELECT v FROM Veiculo v WHERE v.renavam = :renavam"),
    @NamedQuery(name = "Veiculo.findByDiaria", query = "SELECT v FROM Veiculo v WHERE v.diaria = :diaria"),
    @NamedQuery(name = "Veiculo.findBySemanal", query = "SELECT v FROM Veiculo v WHERE v.semanal = :semanal"),
    @NamedQuery(name = "Veiculo.findByQuinzenal", query = "SELECT v FROM Veiculo v WHERE v.quinzenal = :quinzenal"),
    @NamedQuery(name = "Veiculo.findByMensal", query = "SELECT v FROM Veiculo v WHERE v.mensal = :mensal"),
    @NamedQuery(name = "Veiculo.findByHora", query = "SELECT v FROM Veiculo v WHERE v.hora = :hora"),
    @NamedQuery(name = "Veiculo.findByHoraExcedente", query = "SELECT v FROM Veiculo v WHERE v.horaExcedente = :horaExcedente"),
    @NamedQuery(name = "Veiculo.findByValorSeguro", query = "SELECT v FROM Veiculo v WHERE v.valorSeguro = :valorSeguro"),
    @NamedQuery(name = "Veiculo.findByPlaca", query = "SELECT v FROM Veiculo v WHERE v.placa = :placa"),
    @NamedQuery(name = "Veiculo.findByMarca", query = "SELECT v FROM Veiculo v WHERE v.marca = :marca"),
    @NamedQuery(name = "Veiculo.findByModelo", query = "SELECT v FROM Veiculo v WHERE v.modelo = :modelo"),
    @NamedQuery(name = "Veiculo.findByAnoFabricacao", query = "SELECT v FROM Veiculo v WHERE v.anoFabricacao = :anoFabricacao"),
    @NamedQuery(name = "Veiculo.findByValorIndenizacao", query = "SELECT v FROM Veiculo v WHERE v.valorIndenizacao = :valorIndenizacao"),
    @NamedQuery(name = "Veiculo.findByProximaManutencao", query = "SELECT v FROM Veiculo v WHERE v.proximaManutencao = :proximaManutencao"),
    @NamedQuery(name = "Veiculo.findByQuilometragemAtual", query = "SELECT v FROM Veiculo v WHERE v.quilometragemAtual = :quilometragemAtual"),
    @NamedQuery(name = "Veiculo.findByInformacoesTecnicas", query = "SELECT v FROM Veiculo v WHERE v.informacoesTecnicas = :informacoesTecnicas"),
    @NamedQuery(name = "Veiculo.findByImagem", query = "SELECT v FROM Veiculo v WHERE v.imagem = :imagem")})
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVeiculo")
    private Integer idVeiculo;
    @Basic(optional = false)
    @Column(name = "renavam")
    private String renavam;
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
    @Column(name = "valorSeguro")
    private double valorSeguro;
    @Basic(optional = false)
    @Column(name = "placa")
    private String placa;
    @Basic(optional = false)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @Column(name = "anoFabricacao")
    private String anoFabricacao;
    @Basic(optional = false)
    @Column(name = "valorIndenizacao")
    private double valorIndenizacao;
    @Basic(optional = false)
    @Column(name = "proximaManutencao")
    @Temporal(TemporalType.DATE)
    private Date proximaManutencao;
    @Basic(optional = false)
    @Column(name = "quilometragemAtual")
    private int quilometragemAtual;
    @Basic(optional = false)
    @Column(name = "informacoesTecnicas")
    private String informacoesTecnicas;
    @Basic(optional = false)
    @Column(name = "imagem")
    private String imagem;

    public Veiculo() {
    }

    public Veiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Veiculo(Integer idVeiculo, String renavam, double diaria, double semanal, double quinzenal, double mensal, double hora, double horaExcedente, double valorSeguro, String placa, String marca, String modelo, String anoFabricacao, double valorIndenizacao, Date proximaManutencao, int quilometragemAtual, String informacoesTecnicas, String imagem) {
        this.idVeiculo = idVeiculo;
        this.renavam = renavam;
        this.diaria = diaria;
        this.semanal = semanal;
        this.quinzenal = quinzenal;
        this.mensal = mensal;
        this.hora = hora;
        this.horaExcedente = horaExcedente;
        this.valorSeguro = valorSeguro;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.valorIndenizacao = valorIndenizacao;
        this.proximaManutencao = proximaManutencao;
        this.quilometragemAtual = quilometragemAtual;
        this.informacoesTecnicas = informacoesTecnicas;
        this.imagem = imagem;
    }

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
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

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public double getValorIndenizacao() {
        return valorIndenizacao;
    }

    public void setValorIndenizacao(double valorIndenizacao) {
        this.valorIndenizacao = valorIndenizacao;
    }

    public Date getProximaManutencao() {
        return proximaManutencao;
    }

    public void setProximaManutencao(Date proximaManutencao) {
        this.proximaManutencao = proximaManutencao;
    }

    public int getQuilometragemAtual() {
        return quilometragemAtual;
    }

    public void setQuilometragemAtual(int quilometragemAtual) {
        this.quilometragemAtual = quilometragemAtual;
    }

    public String getInformacoesTecnicas() {
        return informacoesTecnicas;
    }

    public void setInformacoesTecnicas(String informacoesTecnicas) {
        this.informacoesTecnicas = informacoesTecnicas;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVeiculo != null ? idVeiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veiculo)) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ((this.idVeiculo == null && other.idVeiculo != null) || (this.idVeiculo != null && !this.idVeiculo.equals(other.idVeiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Veiculo[ idVeiculo=" + idVeiculo + " ]";
    }
    
}
