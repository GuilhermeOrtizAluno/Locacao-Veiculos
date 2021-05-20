/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import controlesJpa.ClienteJpaController;
import controlesJpa.exceptions.IllegalOrphanException;
import controlesJpa.exceptions.NonexistentEntityException;
import entidades.Cliente;
import java.util.List;

/**
 *
 * @author luanl
 */
public class ClienteDAO extends ClasseDAO<Cliente> {

    private final ClienteJpaController clienteJpa;

    public ClienteDAO() {
        this.setEntityManagerFactory();
        this.clienteJpa = new ClienteJpaController( this.getEmf() );
    }

    @Override
    public void add(Cliente objeto) throws Exception {
        clienteJpa.create(objeto);
    }

    @Override
    public void edit(Cliente objeto) throws Exception {
        clienteJpa.edit(objeto);
    }

    @Override
    public void remove(int id) throws NonexistentEntityException {
        try {
            clienteJpa.destroy( (int)id );
        } catch (IllegalOrphanException e) {
            throw new NonexistentEntityException( e.getMessage() );
        }
    }

    @Override
    public List<Cliente> getAll() {
        return clienteJpa.findClienteEntities();
    }

    @Override
    public int getQuantos() {
        return clienteJpa.getClienteCount();
    }
}
