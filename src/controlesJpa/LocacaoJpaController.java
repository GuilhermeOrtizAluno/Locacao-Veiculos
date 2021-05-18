/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlesJpa;

import controlesJpa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Condutor;
import entidades.Locacao;
import entidades.Veiculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author luanl
 */
public class LocacaoJpaController implements Serializable {

    public LocacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Locacao locacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Condutor idCondutor = locacao.getIdCondutor();
            if (idCondutor != null) {
                idCondutor = em.getReference(idCondutor.getClass(), idCondutor.getIdCondutor());
                locacao.setIdCondutor(idCondutor);
            }
            Veiculo idVeiculo = locacao.getIdVeiculo();
            if (idVeiculo != null) {
                idVeiculo = em.getReference(idVeiculo.getClass(), idVeiculo.getIdVeiculo());
                locacao.setIdVeiculo(idVeiculo);
            }
            em.persist(locacao);
            if (idCondutor != null) {
                idCondutor.getLocacaoCollection().add(locacao);
                idCondutor = em.merge(idCondutor);
            }
            if (idVeiculo != null) {
                idVeiculo.getLocacaoCollection().add(locacao);
                idVeiculo = em.merge(idVeiculo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Locacao locacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locacao persistentLocacao = em.find(Locacao.class, locacao.getIdLocacao());
            Condutor idCondutorOld = persistentLocacao.getIdCondutor();
            Condutor idCondutorNew = locacao.getIdCondutor();
            Veiculo idVeiculoOld = persistentLocacao.getIdVeiculo();
            Veiculo idVeiculoNew = locacao.getIdVeiculo();
            if (idCondutorNew != null) {
                idCondutorNew = em.getReference(idCondutorNew.getClass(), idCondutorNew.getIdCondutor());
                locacao.setIdCondutor(idCondutorNew);
            }
            if (idVeiculoNew != null) {
                idVeiculoNew = em.getReference(idVeiculoNew.getClass(), idVeiculoNew.getIdVeiculo());
                locacao.setIdVeiculo(idVeiculoNew);
            }
            locacao = em.merge(locacao);
            if (idCondutorOld != null && !idCondutorOld.equals(idCondutorNew)) {
                idCondutorOld.getLocacaoCollection().remove(locacao);
                idCondutorOld = em.merge(idCondutorOld);
            }
            if (idCondutorNew != null && !idCondutorNew.equals(idCondutorOld)) {
                idCondutorNew.getLocacaoCollection().add(locacao);
                idCondutorNew = em.merge(idCondutorNew);
            }
            if (idVeiculoOld != null && !idVeiculoOld.equals(idVeiculoNew)) {
                idVeiculoOld.getLocacaoCollection().remove(locacao);
                idVeiculoOld = em.merge(idVeiculoOld);
            }
            if (idVeiculoNew != null && !idVeiculoNew.equals(idVeiculoOld)) {
                idVeiculoNew.getLocacaoCollection().add(locacao);
                idVeiculoNew = em.merge(idVeiculoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = locacao.getIdLocacao();
                if (findLocacao(id) == null) {
                    throw new NonexistentEntityException("The locacao with id " + id + " no longer exists.");
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
            Locacao locacao;
            try {
                locacao = em.getReference(Locacao.class, id);
                locacao.getIdLocacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The locacao with id " + id + " no longer exists.", enfe);
            }
            Condutor idCondutor = locacao.getIdCondutor();
            if (idCondutor != null) {
                idCondutor.getLocacaoCollection().remove(locacao);
                idCondutor = em.merge(idCondutor);
            }
            Veiculo idVeiculo = locacao.getIdVeiculo();
            if (idVeiculo != null) {
                idVeiculo.getLocacaoCollection().remove(locacao);
                idVeiculo = em.merge(idVeiculo);
            }
            em.remove(locacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Locacao> findLocacaoEntities() {
        return findLocacaoEntities(true, -1, -1);
    }

    public List<Locacao> findLocacaoEntities(int maxResults, int firstResult) {
        return findLocacaoEntities(false, maxResults, firstResult);
    }

    private List<Locacao> findLocacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Locacao.class));
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

    public Locacao findLocacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Locacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Locacao> rt = cq.from(Locacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
