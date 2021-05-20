/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import controlesJpa.LocacaoJpaController;
import controlesJpa.exceptions.NonexistentEntityException;
import entidades.Locacao;
import java.util.List;

/**
 *
 * @author luanl
 */
public class LocacaoDAO extends ClasseDAO<Locacao> {

    private final LocacaoJpaController locacaoJpa;

    public LocacaoDAO() {
        this.setEntityManagerFactory();
        this.locacaoJpa = new LocacaoJpaController( this.getEmf() );
    }

    @Override
    public void add(Locacao objeto) throws Exception {
        locacaoJpa.create(objeto);
    }

    @Override
    public void edit(Locacao objeto) throws Exception {
        locacaoJpa.edit(objeto);
    }

    @Override
    public void remove(int id) throws NonexistentEntityException {
        locacaoJpa.destroy( (int)id );
    }

    @Override
    public List<Locacao> getAll() {
        return locacaoJpa.findLocacaoEntities();
    }

    @Override
    public int getQuantos() {
        return locacaoJpa.getLocacaoCount();
    }
}
