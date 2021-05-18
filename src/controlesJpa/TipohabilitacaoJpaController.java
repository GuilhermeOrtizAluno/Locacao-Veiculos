/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlesJpa;

import controlesJpa.exceptions.IllegalOrphanException;
import controlesJpa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Condutor;
import entidades.Tipohabilitacao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author luanl
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
        if (tipohabilitacao.getCondutorCollection() == null) {
            tipohabilitacao.setCondutorCollection(new ArrayList<Condutor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Condutor> attachedCondutorCollection = new ArrayList<Condutor>();
            for (Condutor condutorCollectionCondutorToAttach : tipohabilitacao.getCondutorCollection()) {
                condutorCollectionCondutorToAttach = em.getReference(condutorCollectionCondutorToAttach.getClass(), condutorCollectionCondutorToAttach.getIdCondutor());
                attachedCondutorCollection.add(condutorCollectionCondutorToAttach);
            }
            tipohabilitacao.setCondutorCollection(attachedCondutorCollection);
            em.persist(tipohabilitacao);
            for (Condutor condutorCollectionCondutor : tipohabilitacao.getCondutorCollection()) {
                Tipohabilitacao oldIdTipoHabilitacaoOfCondutorCollectionCondutor = condutorCollectionCondutor.getIdTipoHabilitacao();
                condutorCollectionCondutor.setIdTipoHabilitacao(tipohabilitacao);
                condutorCollectionCondutor = em.merge(condutorCollectionCondutor);
                if (oldIdTipoHabilitacaoOfCondutorCollectionCondutor != null) {
                    oldIdTipoHabilitacaoOfCondutorCollectionCondutor.getCondutorCollection().remove(condutorCollectionCondutor);
                    oldIdTipoHabilitacaoOfCondutorCollectionCondutor = em.merge(oldIdTipoHabilitacaoOfCondutorCollectionCondutor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipohabilitacao tipohabilitacao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipohabilitacao persistentTipohabilitacao = em.find(Tipohabilitacao.class, tipohabilitacao.getIdTipoHabilitacao());
            Collection<Condutor> condutorCollectionOld = persistentTipohabilitacao.getCondutorCollection();
            Collection<Condutor> condutorCollectionNew = tipohabilitacao.getCondutorCollection();
            List<String> illegalOrphanMessages = null;
            for (Condutor condutorCollectionOldCondutor : condutorCollectionOld) {
                if (!condutorCollectionNew.contains(condutorCollectionOldCondutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Condutor " + condutorCollectionOldCondutor + " since its idTipoHabilitacao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Condutor> attachedCondutorCollectionNew = new ArrayList<Condutor>();
            for (Condutor condutorCollectionNewCondutorToAttach : condutorCollectionNew) {
                condutorCollectionNewCondutorToAttach = em.getReference(condutorCollectionNewCondutorToAttach.getClass(), condutorCollectionNewCondutorToAttach.getIdCondutor());
                attachedCondutorCollectionNew.add(condutorCollectionNewCondutorToAttach);
            }
            condutorCollectionNew = attachedCondutorCollectionNew;
            tipohabilitacao.setCondutorCollection(condutorCollectionNew);
            tipohabilitacao = em.merge(tipohabilitacao);
            for (Condutor condutorCollectionNewCondutor : condutorCollectionNew) {
                if (!condutorCollectionOld.contains(condutorCollectionNewCondutor)) {
                    Tipohabilitacao oldIdTipoHabilitacaoOfCondutorCollectionNewCondutor = condutorCollectionNewCondutor.getIdTipoHabilitacao();
                    condutorCollectionNewCondutor.setIdTipoHabilitacao(tipohabilitacao);
                    condutorCollectionNewCondutor = em.merge(condutorCollectionNewCondutor);
                    if (oldIdTipoHabilitacaoOfCondutorCollectionNewCondutor != null && !oldIdTipoHabilitacaoOfCondutorCollectionNewCondutor.equals(tipohabilitacao)) {
                        oldIdTipoHabilitacaoOfCondutorCollectionNewCondutor.getCondutorCollection().remove(condutorCollectionNewCondutor);
                        oldIdTipoHabilitacaoOfCondutorCollectionNewCondutor = em.merge(oldIdTipoHabilitacaoOfCondutorCollectionNewCondutor);
                    }
                }
            }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Condutor> condutorCollectionOrphanCheck = tipohabilitacao.getCondutorCollection();
            for (Condutor condutorCollectionOrphanCheckCondutor : condutorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipohabilitacao (" + tipohabilitacao + ") cannot be destroyed since the Condutor " + condutorCollectionOrphanCheckCondutor + " in its condutorCollection field has a non-nullable idTipoHabilitacao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
