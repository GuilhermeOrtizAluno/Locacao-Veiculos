/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlesJpa;

import controlesJpa.exceptions.NonexistentEntityException;
import entidades.Tipohabilitacao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author guilherme.santos
 */
public class TipohabilitacaoJpaController implements Serializable {

    public TipohabilitacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipohabilitacao tipohabilitacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipohabilitacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipohabilitacao tipohabilitacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipohabilitacao = em.merge(tipohabilitacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipohabilitacao.getIdTipoHabilitacao();
                if (findTipohabilitacao(id) == null) {
                    throw new NonexistentEntityException("The tipohabilitacao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipohabilitacao tipohabilitacao;
            try {
                tipohabilitacao = em.getReference(Tipohabilitacao.class, id);
                tipohabilitacao.getIdTipoHabilitacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipohabilitacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipohabilitacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipohabilitacao> findTipohabilitacaoEntities() {
        return findTipohabilitacaoEntities(true, -1, -1);
    }

    public List<Tipohabilitacao> findTipohabilitacaoEntities(int maxResults, int firstResult) {
        return findTipohabilitacaoEntities(false, maxResults, firstResult);
    }

    private List<Tipohabilitacao> findTipohabilitacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipohabilitacao.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tipohabilitacao findTipohabilitacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipohabilitacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipohabilitacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipohabilitacao> rt = cq.from(Tipohabilitacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
