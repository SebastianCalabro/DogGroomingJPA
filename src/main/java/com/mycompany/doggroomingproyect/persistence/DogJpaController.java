package com.mycompany.doggroomingproyect.persistence;

import com.mycompany.doggroomingproyect.logic.Dog;
import com.mycompany.doggroomingproyect.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DogJpaController implements Serializable {

    public DogJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public DogJpaController() {
        emf = Persistence.createEntityManagerFactory("DogGroomingPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dog dog) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dog);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dog dog) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dog = em.merge(dog);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = dog.getCustomerNum();
                if (findDog(id) == null) {
                    throw new NonexistentEntityException("The dog with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dog dog;
            try {
                dog = em.getReference(Dog.class, id);
                dog.getCustomerNum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dog with id " + id + " no longer exists.", enfe);
            }
            em.remove(dog);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dog> findDogEntities() {
        return findDogEntities(true, -1, -1);
    }

    public List<Dog> findDogEntities(int maxResults, int firstResult) {
        return findDogEntities(false, maxResults, firstResult);
    }

    private List<Dog> findDogEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dog.class));
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

    public Dog findDog(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dog.class, id);
        } finally {
            em.close();
        }
    }

    public int getDogCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dog> rt = cq.from(Dog.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
