/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlesJpa;

import controlesJpa.exceptions.NonexistentEntityException;
import entidades.Condutor;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Cliente;
import entidades.Condutor;
import entidades.Tipohabilitacao;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Locacao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author guilherme.santos
 */
public class CondutorJpaController implements Serializable {

    public CondutorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Condutor condutor) {
        if (condutor.getTipohabilitacaoCollection() == null) {
            condutor.setTipohabilitacaoCollection(new ArrayList<Tipohabilitacao>());
        }
        if (condutor.getLocacaoCollection() == null) {
            condutor.setLocacaoCollection(new ArrayList<Locacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = condutor.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                condutor.setIdCliente(idCliente);
            }
            Collection<Tipohabilitacao> attachedTipohabilitacaoCollection = new ArrayList<Tipohabilitacao>();
            for (Tipohabilitacao tipohabilitacaoCollectionTipohabilitacaoToAttach : condutor.getTipohabilitacaoCollection()) {
                tipohabilitacaoCollectionTipohabilitacaoToAttach = em.getReference(tipohabilitacaoCollectionTipohabilitacaoToAttach.getClass(), tipohabilitacaoCollectionTipohabilitacaoToAttach.getIdTipoHabilitacao());
                attachedTipohabilitacaoCollection.add(tipohabilitacaoCollectionTipohabilitacaoToAttach);
            }
            condutor.setTipohabilitacaoCollection(attachedTipohabilitacaoCollection);
            Collection<Locacao> attachedLocacaoCollection = new ArrayList<Locacao>();
            for (Locacao locacaoCollectionLocacaoToAttach : condutor.getLocacaoCollection()) {
                locacaoCollectionLocacaoToAttach = em.getReference(locacaoCollectionLocacaoToAttach.getClass(), locacaoCollectionLocacaoToAttach.getIdLocacao());
                attachedLocacaoCollection.add(locacaoCollectionLocacaoToAttach);
            }
            condutor.setLocacaoCollection(attachedLocacaoCollection);
            em.persist(condutor);
            if (idCliente != null) {
                idCliente.getCondutorCollection().add(condutor);
                idCliente = em.merge(idCliente);
            }
            for (Tipohabilitacao tipohabilitacaoCollectionTipohabilitacao : condutor.getTipohabilitacaoCollection()) {
                Condutor oldIdCondutorHabilitacaoOfTipohabilitacaoCollectionTipohabilitacao = tipohabilitacaoCollectionTipohabilitacao.getIdCondutorHabilitacao();
                tipohabilitacaoCollectionTipohabilitacao.setIdCondutorHabilitacao(condutor);
                tipohabilitacaoCollectionTipohabilitacao = em.merge(tipohabilitacaoCollectionTipohabilitacao);
                if (oldIdCondutorHabilitacaoOfTipohabilitacaoCollectionTipohabilitacao != null) {
                    oldIdCondutorHabilitacaoOfTipohabilitacaoCollectionTipohabilitacao.getTipohabilitacaoCollection().remove(tipohabilitacaoCollectionTipohabilitacao);
                    oldIdCondutorHabilitacaoOfTipohabilitacaoCollectionTipohabilitacao = em.merge(oldIdCondutorHabilitacaoOfTipohabilitacaoCollectionTipohabilitacao);
                }
            }
            for (Locacao locacaoCollectionLocacao : condutor.getLocacaoCollection()) {
                Condutor oldIdCondutorLocacaoOfLocacaoCollectionLocacao = locacaoCollectionLocacao.getIdCondutorLocacao();
                locacaoCollectionLocacao.setIdCondutorLocacao(condutor);
                locacaoCollectionLocacao = em.merge(locacaoCollectionLocacao);
                if (oldIdCondutorLocacaoOfLocacaoCollectionLocacao != null) {
                    oldIdCondutorLocacaoOfLocacaoCollectionLocacao.getLocacaoCollection().remove(locacaoCollectionLocacao);
                    oldIdCondutorLocacaoOfLocacaoCollectionLocacao = em.merge(oldIdCondutorLocacaoOfLocacaoCollectionLocacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Condutor condutor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Condutor persistentCondutor = em.find(Condutor.class, condutor.getIdCondutor());
            Cliente idClienteOld = persistentCondutor.getIdCliente();
            Cliente idClienteNew = condutor.getIdCliente();
            Collection<Tipohabilitacao> tipohabilitacaoCollectionOld = persistentCondutor.getTipohabilitacaoCollection();
            Collection<Tipohabilitacao> tipohabilitacaoCollectionNew = condutor.getTipohabilitacaoCollection();
            Collection<Locacao> locacaoCollectionOld = persistentCondutor.getLocacaoCollection();
            Collection<Locacao> locacaoCollectionNew = condutor.getLocacaoCollection();
            List<String> illegalOrphanMessages = null;
            for (Tipohabilitacao tipohabilitacaoCollectionOldTipohabilitacao : tipohabilitacaoCollectionOld) {
                if (!tipohabilitacaoCollectionNew.contains(tipohabilitacaoCollectionOldTipohabilitacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tipohabilitacao " + tipohabilitacaoCollectionOldTipohabilitacao + " since its idCondutorHabilitacao field is not nullable.");
                }
            }
            for (Locacao locacaoCollectionOldLocacao : locacaoCollectionOld) {
                if (!locacaoCollectionNew.contains(locacaoCollectionOldLocacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Locacao " + locacaoCollectionOldLocacao + " since its idCondutorLocacao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                condutor.setIdCliente(idClienteNew);
            }
            Collection<Tipohabilitacao> attachedTipohabilitacaoCollectionNew = new ArrayList<Tipohabilitacao>();
            for (Tipohabilitacao tipohabilitacaoCollectionNewTipohabilitacaoToAttach : tipohabilitacaoCollectionNew) {
                tipohabilitacaoCollectionNewTipohabilitacaoToAttach = em.getReference(tipohabilitacaoCollectionNewTipohabilitacaoToAttach.getClass(), tipohabilitacaoCollectionNewTipohabilitacaoToAttach.getIdTipoHabilitacao());
                attachedTipohabilitacaoCollectionNew.add(tipohabilitacaoCollectionNewTipohabilitacaoToAttach);
            }
            tipohabilitacaoCollectionNew = attachedTipohabilitacaoCollectionNew;
            condutor.setTipohabilitacaoCollection(tipohabilitacaoCollectionNew);
            Collection<Locacao> attachedLocacaoCollectionNew = new ArrayList<Locacao>();
            for (Locacao locacaoCollectionNewLocacaoToAttach : locacaoCollectionNew) {
                locacaoCollectionNewLocacaoToAttach = em.getReference(locacaoCollectionNewLocacaoToAttach.getClass(), locacaoCollectionNewLocacaoToAttach.getIdLocacao());
                attachedLocacaoCollectionNew.add(locacaoCollectionNewLocacaoToAttach);
            }
            locacaoCollectionNew = attachedLocacaoCollectionNew;
            condutor.setLocacaoCollection(locacaoCollectionNew);
            condutor = em.merge(condutor);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getCondutorCollection().remove(condutor);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getCondutorCollection().add(condutor);
                idClienteNew = em.merge(idClienteNew);
            }
            for (Tipohabilitacao tipohabilitacaoCollectionNewTipohabilitacao : tipohabilitacaoCollectionNew) {
                if (!tipohabilitacaoCollectionOld.contains(tipohabilitacaoCollectionNewTipohabilitacao)) {
                    Condutor oldIdCondutorHabilitacaoOfTipohabilitacaoCollectionNewTipohabilitacao = tipohabilitacaoCollectionNewTipohabilitacao.getIdCondutorHabilitacao();
                    tipohabilitacaoCollectionNewTipohabilitacao.setIdCondutorHabilitacao(condutor);
                    tipohabilitacaoCollectionNewTipohabilitacao = em.merge(tipohabilitacaoCollectionNewTipohabilitacao);
                    if (oldIdCondutorHabilitacaoOfTipohabilitacaoCollectionNewTipohabilitacao != null && !oldIdCondutorHabilitacaoOfTipohabilitacaoCollectionNewTipohabilitacao.equals(condutor)) {
                        oldIdCondutorHabilitacaoOfTipohabilitacaoCollectionNewTipohabilitacao.getTipohabilitacaoCollection().remove(tipohabilitacaoCollectionNewTipohabilitacao);
                        oldIdCondutorHabilitacaoOfTipohabilitacaoCollectionNewTipohabilitacao = em.merge(oldIdCondutorHabilitacaoOfTipohabilitacaoCollectionNewTipohabilitacao);
                    }
                }
            }
            for (Locacao locacaoCollectionNewLocacao : locacaoCollectionNew) {
                if (!locacaoCollectionOld.contains(locacaoCollectionNewLocacao)) {
                    Condutor oldIdCondutorLocacaoOfLocacaoCollectionNewLocacao = locacaoCollectionNewLocacao.getIdCondutorLocacao();
                    locacaoCollectionNewLocacao.setIdCondutorLocacao(condutor);
                    locacaoCollectionNewLocacao = em.merge(locacaoCollectionNewLocacao);
                    if (oldIdCondutorLocacaoOfLocacaoCollectionNewLocacao != null && !oldIdCondutorLocacaoOfLocacaoCollectionNewLocacao.equals(condutor)) {
                        oldIdCondutorLocacaoOfLocacaoCollectionNewLocacao.getLocacaoCollection().remove(locacaoCollectionNewLocacao);
                        oldIdCondutorLocacaoOfLocacaoCollectionNewLocacao = em.merge(oldIdCondutorLocacaoOfLocacaoCollectionNewLocacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = condutor.getIdCondutor();
                if (findCondutor(id) == null) {
                    throw new NonexistentEntityException("The condutor with id " + id + " no longer exists.");
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
            Condutor condutor;
            try {
                condutor = em.getReference(Condutor.class, id);
                condutor.getIdCondutor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The condutor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tipohabilitacao> tipohabilitacaoCollectionOrphanCheck = condutor.getTipohabilitacaoCollection();
            for (Tipohabilitacao tipohabilitacaoCollectionOrphanCheckTipohabilitacao : tipohabilitacaoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Condutor (" + condutor + ") cannot be destroyed since the Tipohabilitacao " + tipohabilitacaoCollectionOrphanCheckTipohabilitacao + " in its tipohabilitacaoCollection field has a non-nullable idCondutorHabilitacao field.");
            }
            Collection<Locacao> locacaoCollectionOrphanCheck = condutor.getLocacaoCollection();
            for (Locacao locacaoCollectionOrphanCheckLocacao : locacaoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Condutor (" + condutor + ") cannot be destroyed since the Locacao " + locacaoCollectionOrphanCheckLocacao + " in its locacaoCollection field has a non-nullable idCondutorLocacao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente idCliente = condutor.getIdCliente();
            if (idCliente != null) {
                idCliente.getCondutorCollection().remove(condutor);
                idCliente = em.merge(idCliente);
            }
            em.remove(condutor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Condutor> findCondutorEntities() {
        return findCondutorEntities(true, -1, -1);
    }

    public List<Condutor> findCondutorEntities(int maxResults, int firstResult) {
        return findCondutorEntities(false, maxResults, firstResult);
    }

    private List<Condutor> findCondutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Condutor.class));
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

    public Condutor findCondutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Condutor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCondutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Condutor> rt = cq.from(Condutor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
