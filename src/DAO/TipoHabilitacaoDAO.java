/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import controlesJpa.TipohabilitacaoJpaController;
import controlesJpa.exceptions.IllegalOrphanException;
import controlesJpa.exceptions.NonexistentEntityException;
import entidades.Tipohabilitacao;
import java.util.List;

/**
 *
 * @author luanl
 */
public class TipoHabilitacaoDAO extends ClasseDAO<Tipohabilitacao> {

    private final TipohabilitacaoJpaController tipoHabilitacaoJpa;

    public TipoHabilitacaoDAO() {
        this.setEntityManagerFactory();
        this.tipoHabilitacaoJpa = new TipohabilitacaoJpaController( this.getEmf() );
    }

    @Override
    public void add(Tipohabilitacao objeto) throws Exception {
        tipoHabilitacaoJpa.create(objeto);
    }

    @Override
    public void edit(Tipohabilitacao objeto) throws Exception {
        tipoHabilitacaoJpa.edit(objeto);
    }

    @Override
    public void remove(int id) throws NonexistentEntityException {
        tipoHabilitacaoJpa.destroy( (int)id );
    }

    @Override
    public List<Tipohabilitacao> getAll() {
        return tipoHabilitacaoJpa.findTipohabilitacaoEntities();
    }

    @Override
    public int getQuantos() {
        return tipoHabilitacaoJpa.getTipohabilitacaoCount();
    }
}
