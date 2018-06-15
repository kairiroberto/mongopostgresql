/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mongodbpostgresql.controle.exceptions.NonexistentEntityException;
import mongodbpostgresql.controle.exceptions.PreexistingEntityException;
import mongodbpostgresql.modelo.CursoCampus;
import mongodbpostgresql.modelo.UnidadeOrganizacional;

/**
 *
 * @author JOSE
 */
public class UnidadeOrganizacionalJpaController implements Serializable {

    public UnidadeOrganizacionalJpaController(/*EntityManagerFactory emf*/) {
        this.emf = Persistence.createEntityManagerFactory("MongoDBPostgreSQLPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UnidadeOrganizacional unidadeOrganizacional) throws PreexistingEntityException, Exception {
        if (unidadeOrganizacional.getCursoCampusCollection() == null) {
            unidadeOrganizacional.setCursoCampusCollection(new ArrayList<CursoCampus>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CursoCampus> attachedCursoCampusCollection = new ArrayList<CursoCampus>();
            for (CursoCampus cursoCampusCollectionCursoCampusToAttach : unidadeOrganizacional.getCursoCampusCollection()) {
                cursoCampusCollectionCursoCampusToAttach = em.getReference(cursoCampusCollectionCursoCampusToAttach.getClass(), cursoCampusCollectionCursoCampusToAttach.getId());
                attachedCursoCampusCollection.add(cursoCampusCollectionCursoCampusToAttach);
            }
            unidadeOrganizacional.setCursoCampusCollection(attachedCursoCampusCollection);
            em.persist(unidadeOrganizacional);
            for (CursoCampus cursoCampusCollectionCursoCampus : unidadeOrganizacional.getCursoCampusCollection()) {
                UnidadeOrganizacional oldFkUnidadeOrganizacionalIdOfCursoCampusCollectionCursoCampus = cursoCampusCollectionCursoCampus.getFkUnidadeOrganizacionalId();
                cursoCampusCollectionCursoCampus.setFkUnidadeOrganizacionalId(unidadeOrganizacional);
                cursoCampusCollectionCursoCampus = em.merge(cursoCampusCollectionCursoCampus);
                if (oldFkUnidadeOrganizacionalIdOfCursoCampusCollectionCursoCampus != null) {
                    oldFkUnidadeOrganizacionalIdOfCursoCampusCollectionCursoCampus.getCursoCampusCollection().remove(cursoCampusCollectionCursoCampus);
                    oldFkUnidadeOrganizacionalIdOfCursoCampusCollectionCursoCampus = em.merge(oldFkUnidadeOrganizacionalIdOfCursoCampusCollectionCursoCampus);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUnidadeOrganizacional(unidadeOrganizacional.getId()) != null) {
                throw new PreexistingEntityException("UnidadeOrganizacional " + unidadeOrganizacional + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadeOrganizacional unidadeOrganizacional) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadeOrganizacional persistentUnidadeOrganizacional = em.find(UnidadeOrganizacional.class, unidadeOrganizacional.getId());
            Collection<CursoCampus> cursoCampusCollectionOld = persistentUnidadeOrganizacional.getCursoCampusCollection();
            Collection<CursoCampus> cursoCampusCollectionNew = unidadeOrganizacional.getCursoCampusCollection();
            Collection<CursoCampus> attachedCursoCampusCollectionNew = new ArrayList<CursoCampus>();
            for (CursoCampus cursoCampusCollectionNewCursoCampusToAttach : cursoCampusCollectionNew) {
                cursoCampusCollectionNewCursoCampusToAttach = em.getReference(cursoCampusCollectionNewCursoCampusToAttach.getClass(), cursoCampusCollectionNewCursoCampusToAttach.getId());
                attachedCursoCampusCollectionNew.add(cursoCampusCollectionNewCursoCampusToAttach);
            }
            cursoCampusCollectionNew = attachedCursoCampusCollectionNew;
            unidadeOrganizacional.setCursoCampusCollection(cursoCampusCollectionNew);
            unidadeOrganizacional = em.merge(unidadeOrganizacional);
            for (CursoCampus cursoCampusCollectionOldCursoCampus : cursoCampusCollectionOld) {
                if (!cursoCampusCollectionNew.contains(cursoCampusCollectionOldCursoCampus)) {
                    cursoCampusCollectionOldCursoCampus.setFkUnidadeOrganizacionalId(null);
                    cursoCampusCollectionOldCursoCampus = em.merge(cursoCampusCollectionOldCursoCampus);
                }
            }
            for (CursoCampus cursoCampusCollectionNewCursoCampus : cursoCampusCollectionNew) {
                if (!cursoCampusCollectionOld.contains(cursoCampusCollectionNewCursoCampus)) {
                    UnidadeOrganizacional oldFkUnidadeOrganizacionalIdOfCursoCampusCollectionNewCursoCampus = cursoCampusCollectionNewCursoCampus.getFkUnidadeOrganizacionalId();
                    cursoCampusCollectionNewCursoCampus.setFkUnidadeOrganizacionalId(unidadeOrganizacional);
                    cursoCampusCollectionNewCursoCampus = em.merge(cursoCampusCollectionNewCursoCampus);
                    if (oldFkUnidadeOrganizacionalIdOfCursoCampusCollectionNewCursoCampus != null && !oldFkUnidadeOrganizacionalIdOfCursoCampusCollectionNewCursoCampus.equals(unidadeOrganizacional)) {
                        oldFkUnidadeOrganizacionalIdOfCursoCampusCollectionNewCursoCampus.getCursoCampusCollection().remove(cursoCampusCollectionNewCursoCampus);
                        oldFkUnidadeOrganizacionalIdOfCursoCampusCollectionNewCursoCampus = em.merge(oldFkUnidadeOrganizacionalIdOfCursoCampusCollectionNewCursoCampus);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = unidadeOrganizacional.getId();
                if (findUnidadeOrganizacional(id) == null) {
                    throw new NonexistentEntityException("The unidadeOrganizacional with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadeOrganizacional unidadeOrganizacional;
            try {
                unidadeOrganizacional = em.getReference(UnidadeOrganizacional.class, id);
                unidadeOrganizacional.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadeOrganizacional with id " + id + " no longer exists.", enfe);
            }
            Collection<CursoCampus> cursoCampusCollection = unidadeOrganizacional.getCursoCampusCollection();
            for (CursoCampus cursoCampusCollectionCursoCampus : cursoCampusCollection) {
                cursoCampusCollectionCursoCampus.setFkUnidadeOrganizacionalId(null);
                cursoCampusCollectionCursoCampus = em.merge(cursoCampusCollectionCursoCampus);
            }
            em.remove(unidadeOrganizacional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidadeOrganizacional> findUnidadeOrganizacionalEntities() {
        return findUnidadeOrganizacionalEntities(true, -1, -1);
    }

    public List<UnidadeOrganizacional> findUnidadeOrganizacionalEntities(int maxResults, int firstResult) {
        return findUnidadeOrganizacionalEntities(false, maxResults, firstResult);
    }

    private List<UnidadeOrganizacional> findUnidadeOrganizacionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadeOrganizacional.class));
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

    public UnidadeOrganizacional findUnidadeOrganizacional(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadeOrganizacional.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadeOrganizacionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadeOrganizacional> rt = cq.from(UnidadeOrganizacional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
