/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbpostgresql.modelo.dao;

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
import mongodbpostgresql.modelo.CursoCampus;
import mongodbpostgresql.modelo.UnidadeOrganizacional;

/**
 *
 * @author JOSE
 */
public class CursoCampusDAO implements Serializable {

    public CursoCampusDAO(/*EntityManagerFactory emf*/) {
        this.emf = Persistence.createEntityManagerFactory("MongoDBPostgreSQLPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CursoCampus cursoCampus) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadeOrganizacional fkUnidadeOrganizacionalId = cursoCampus.getFkUnidadeOrganizacionalId();
            if (fkUnidadeOrganizacionalId != null) {
                fkUnidadeOrganizacionalId = em.getReference(fkUnidadeOrganizacionalId.getClass(), fkUnidadeOrganizacionalId.getId());
                cursoCampus.setFkUnidadeOrganizacionalId(fkUnidadeOrganizacionalId);
            }
            em.persist(cursoCampus);
            if (fkUnidadeOrganizacionalId != null) {
                fkUnidadeOrganizacionalId.getCursoCampusCollection().add(cursoCampus);
                fkUnidadeOrganizacionalId = em.merge(fkUnidadeOrganizacionalId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCursoCampus(cursoCampus.getId()) != null) {
                throw new PreexistingEntityException("CursoCampus " + cursoCampus + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CursoCampus cursoCampus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CursoCampus persistentCursoCampus = em.find(CursoCampus.class, cursoCampus.getId());
            UnidadeOrganizacional fkUnidadeOrganizacionalIdOld = persistentCursoCampus.getFkUnidadeOrganizacionalId();
            UnidadeOrganizacional fkUnidadeOrganizacionalIdNew = cursoCampus.getFkUnidadeOrganizacionalId();
            if (fkUnidadeOrganizacionalIdNew != null) {
                fkUnidadeOrganizacionalIdNew = em.getReference(fkUnidadeOrganizacionalIdNew.getClass(), fkUnidadeOrganizacionalIdNew.getId());
                cursoCampus.setFkUnidadeOrganizacionalId(fkUnidadeOrganizacionalIdNew);
            }
            cursoCampus = em.merge(cursoCampus);
            if (fkUnidadeOrganizacionalIdOld != null && !fkUnidadeOrganizacionalIdOld.equals(fkUnidadeOrganizacionalIdNew)) {
                fkUnidadeOrganizacionalIdOld.getCursoCampusCollection().remove(cursoCampus);
                fkUnidadeOrganizacionalIdOld = em.merge(fkUnidadeOrganizacionalIdOld);
            }
            if (fkUnidadeOrganizacionalIdNew != null && !fkUnidadeOrganizacionalIdNew.equals(fkUnidadeOrganizacionalIdOld)) {
                fkUnidadeOrganizacionalIdNew.getCursoCampusCollection().add(cursoCampus);
                fkUnidadeOrganizacionalIdNew = em.merge(fkUnidadeOrganizacionalIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cursoCampus.getId();
                if (findCursoCampus(id) == null) {
                    throw new NonexistentEntityException("The cursoCampus with id " + id + " no longer exists.");
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
            CursoCampus cursoCampus;
            try {
                cursoCampus = em.getReference(CursoCampus.class, id);
                cursoCampus.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursoCampus with id " + id + " no longer exists.", enfe);
            }
            UnidadeOrganizacional fkUnidadeOrganizacionalId = cursoCampus.getFkUnidadeOrganizacionalId();
            if (fkUnidadeOrganizacionalId != null) {
                fkUnidadeOrganizacionalId.getCursoCampusCollection().remove(cursoCampus);
                fkUnidadeOrganizacionalId = em.merge(fkUnidadeOrganizacionalId);
            }
            em.remove(cursoCampus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CursoCampus> findCursoCampusEntities() {
        return findCursoCampusEntities(true, -1, -1);
    }

    public List<CursoCampus> findCursoCampusEntities(int maxResults, int firstResult) {
        return findCursoCampusEntities(false, maxResults, firstResult);
    }

    private List<CursoCampus> findCursoCampusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CursoCampus.class));
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

    public CursoCampus findCursoCampus(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CursoCampus.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoCampusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CursoCampus> rt = cq.from(CursoCampus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
