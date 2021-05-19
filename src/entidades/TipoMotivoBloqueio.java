/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author luanl
 */
public enum TipoMotivoBloqueio {
    INADIMPLÊNCIA("Inadimplência"),
    COBRANÇA_EXTRAJUDICIAL("Cobrança extrajudicial"),
    COBRANÇA_JUDICIAL("Cobrança judicial"),
    PROBLEMAS_CADASTRAIS("Problemas cadastrais");
    
    public String tipo;

    private TipoMotivoBloqueio(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return this.tipo;
    }
}
