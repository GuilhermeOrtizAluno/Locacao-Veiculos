/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import controlesJpa.VeiculoJpaController;
import controlesJpa.exceptions.IllegalOrphanException;
import controlesJpa.exceptions.NonexistentEntityException;
import entidades.Veiculo;
import java.util.List;

/**
 *
 * @author luanl
 */
public class VeiculoDAO extends ClasseDAO<Veiculo> {

    private final VeiculoJpaController veiculoJpa;

    public VeiculoDAO() {
        this.setEntityManagerFactory();
        this.veiculoJpa = new VeiculoJpaController( this.getEmf() );
    }

    @Override
    public void add(Veiculo objeto) throws Exception {
        veiculoJpa.create(objeto);
    }

    @Override
    public void edit(Veiculo objeto) throws Exception {
        veiculoJpa.edit(objeto);
    }

    @Override
    public void remove(int id) throws NonexistentEntityException {
        try {
            veiculoJpa.destroy( (int)id );
        } catch (IllegalOrphanException ex) {
        }
    }

    @Override
    public List<Veiculo> getAll() {
        return veiculoJpa.findVeiculoEntities();
    }

    @Override
    public int getQuantos() {
        return veiculoJpa.getVeiculoCount();
    }
}
