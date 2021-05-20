/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enums;

/**
 *
 * @author luanl
 */
public enum TipoTempo {
    DIARIA("Diárias"), SEMANA("Semanas"), QUINZENA("Quinzenas"), MES("Meses");
    
    public String nome;

    private TipoTempo(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
