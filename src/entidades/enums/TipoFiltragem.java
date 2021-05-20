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
public enum TipoFiltragem {
    QUANTIDADE("Por quantidade"), TEMPO("Por tempo");
    
    public String descricao;

    private TipoFiltragem(String pessoa) {
        this.descricao = pessoa;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
