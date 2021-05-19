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
public enum TipoCliente {
    FISICA("Física"), JURIDICA("Jurídica");
    
    public String pessoa;

    private TipoCliente(String pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return this.pessoa;
    }
}
