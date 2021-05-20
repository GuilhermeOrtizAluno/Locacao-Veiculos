/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import controlesJpa.CondutorJpaController;
import controlesJpa.exceptions.IllegalOrphanException;
import controlesJpa.exceptions.NonexistentEntityException;
import entidades.Condutor;
import java.util.List;

/**
 *
 * @author luanl
 */
public class CondutorDAO extends ClasseDAO<Condutor> {

    private final CondutorJpaController condutorJpa;

    public CondutorDAO() {
        this.setEntityManagerFactory();
        this.condutorJpa = new CondutorJpaController( this.getEmf() );
    }

    @Override
    public void add(Condutor objeto) throws Exception {
        condutorJpa.create(objeto);
    }

    @Override
    public void edit(Condutor objeto) throws Exception {
        condutorJpa.edit(objeto);
    }

    @Override
    public void remove(int id) throws NonexistentEntityException {
        try {
            condutorJpa.destroy( (int)id );
        } catch (IllegalOrphanException e) {
            throw new NonexistentEntityException( e.getMessage() );
        }
    }

    @Override
    public List<Condutor> getAll() {
        return condutorJpa.findCondutorEntities();
    }

    @Override
    public int getQuantos() {
        return condutorJpa.getCondutorCount();
    }
}
