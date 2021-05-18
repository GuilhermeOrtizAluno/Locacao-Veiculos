/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlesJpa;

import controlesJpa.exceptions.IllegalOrphanException;
import controlesJpa.exceptions.NonexistentEntityException;
import entidades.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Condutor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author luanl
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getCondutorCollection() == null) {
            cliente.setCondutorCollection(new ArrayList<Condutor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Condutor> attachedCondutorCollection = new ArrayList<Condutor>();
            for (Condutor condutorCollectionCondutorToAttach : cliente.getCondutorCollection()) {
                condutorCollectionCondutorToAttach = em.getReference(condutorCollectionCondutorToAttach.getClass(), condutorCollectionCondutorToAttach.getIdCondutor());
                attachedCondutorCollection.add(condutorCollectionCondutorToAttach);
            }
            cliente.setCondutorCollection(attachedCondutorCollection);
            em.persist(cliente);
            for (Condutor condutorCollectionCondutor : cliente.getCondutorCollection()) {
                Cliente oldIdClienteOfCondutorCollectionCondutor = condutorCollectionCondutor.getIdCliente();
                condutorCollectionCondutor.setIdCliente(cliente);
                condutorCollectionCondutor = em.merge(condutorCollectionCondutor);
                if (oldIdClienteOfCondutorCollectionCondutor != null) {
                    oldIdClienteOfCondutorCollectionCondutor.getCondutorCollection().remove(condutorCollectionCondutor);
                    oldIdClienteOfCondutorCollectionCondutor = em.merge(oldIdClienteOfCondutorCollectionCondutor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            Collection<Condutor> condutorCollectionOld = persistentCliente.getCondutorCollection();
            Collection<Condutor> condutorCollectionNew = cliente.getCondutorCollection();
            List<String> illegalOrphanMessages = null;
            for (Condutor condutorCollectionOldCondutor : condutorCollectionOld) {
                if (!condutorCollectionNew.contains(condutorCollectionOldCondutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Condutor " + condutorCollectionOldCondutor + " since its idCliente field is not nullable.");
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
            cliente.setCondutorCollection(condutorCollectionNew);
            cliente = em.merge(cliente);
            for (Condutor condutorCollectionNewCondutor : condutorCollectionNew) {
                if (!condutorCollectionOld.contains(condutorCollectionNewCondutor)) {
                    Cliente oldIdClienteOfCondutorCollectionNewCondutor = condutorCollectionNewCondutor.getIdCliente();
                    condutorCollectionNewCondutor.setIdCliente(cliente);
                    condutorCollectionNewCondutor = em.merge(condutorCollectionNewCondutor);
                    if (oldIdClienteOfCondutorCollectionNewCondutor != null && !oldIdClienteOfCondutorCollectionNewCondutor.equals(cliente)) {
                        oldIdClienteOfCondutorCollectionNewCondutor.getCondutorCollection().remove(condutorCollectionNewCondutor);
                        oldIdClienteOfCondutorCollectionNewCondutor = em.merge(oldIdClienteOfCondutorCollectionNewCondutor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Condutor> condutorCollectionOrphanCheck = cliente.getCondutorCollection();
            for (Condutor condutorCollectionOrphanCheckCondutor : condutorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Condutor " + condutorCollectionOrphanCheckCondutor + " in its condutorCollection field has a non-nullable idCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
