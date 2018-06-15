/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.controle;

import java.io.Serializable;
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
import mongodbpostgresql.modelo.AlunoCursoCampusId;
import mongodbpostgresql.modelo.AlunoCursoCampusIdPK;

/**
 *
 * @author JOSE
 */
public class AlunoCursoCampusIdJpaController implements Serializable {

    public AlunoCursoCampusIdJpaController(/*EntityManagerFactory emf*/) {
        this.emf = Persistence.createEntityManagerFactory("MongoDBPostgreSQLPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AlunoCursoCampusId alunoCursoCampusId) throws PreexistingEntityException, Exception {
        if (alunoCursoCampusId.getAlunoCursoCampusIdPK() == null) {
            alunoCursoCampusId.setAlunoCursoCampusIdPK(new AlunoCursoCampusIdPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(alunoCursoCampusId);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlunoCursoCampusId(alunoCursoCampusId.getAlunoCursoCampusIdPK()) != null) {
                throw new PreexistingEntityException("AlunoCursoCampusId " + alunoCursoCampusId + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AlunoCursoCampusId alunoCursoCampusId) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            alunoCursoCampusId = em.merge(alunoCursoCampusId);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AlunoCursoCampusIdPK id = alunoCursoCampusId.getAlunoCursoCampusIdPK();
                if (findAlunoCursoCampusId(id) == null) {
                    throw new NonexistentEntityException("The alunoCursoCampusId with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AlunoCursoCampusIdPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AlunoCursoCampusId alunoCursoCampusId;
            try {
                alunoCursoCampusId = em.getReference(AlunoCursoCampusId.class, id);
                alunoCursoCampusId.getAlunoCursoCampusIdPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alunoCursoCampusId with id " + id + " no longer exists.", enfe);
            }
            em.remove(alunoCursoCampusId);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AlunoCursoCampusId> findAlunoCursoCampusIdEntities() {
        return findAlunoCursoCampusIdEntities(true, -1, -1);
    }

    public List<AlunoCursoCampusId> findAlunoCursoCampusIdEntities(int maxResults, int firstResult) {
        return findAlunoCursoCampusIdEntities(false, maxResults, firstResult);
    }

    private List<AlunoCursoCampusId> findAlunoCursoCampusIdEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AlunoCursoCampusId.class));
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

    public AlunoCursoCampusId findAlunoCursoCampusId(AlunoCursoCampusIdPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AlunoCursoCampusId.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlunoCursoCampusIdCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AlunoCursoCampusId> rt = cq.from(AlunoCursoCampusId.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
