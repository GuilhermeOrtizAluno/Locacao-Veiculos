/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import controlesJpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author luanl
 * @param <T> uma das entidades criadas no projeto
 */
public abstract class ClasseDAO<T> {
    
    private EntityManagerFactory emf;
    
    public void setEntityManagerFactory() {
        this.emf = Persistence.createEntityManagerFactory("LocacaoVeiculoPU");
    }
    
    public abstract void add(T objeto) throws Exception;

    public abstract void edit(T objeto) throws Exception;

    public abstract void remove(int id) throws NonexistentEntityException;

    public abstract List<T> getAll();
    
    public abstract int getQuantos();
    
    /**
     *
     * @param objeto a ser persistido
     */
    public void persist(T objeto) {
        EntityManager em = getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close(); 
        }
    }

    /**
     * @return o proprio emf
     */
    public EntityManagerFactory getEmf() {
        return emf;
    }
}
