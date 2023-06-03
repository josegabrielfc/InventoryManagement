/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.exceptions.IllegalOrphanException;
import Dao.exceptions.NonexistentEntityException;
import Dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Producto;
import Entidades.Ubicacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Efranor
 */
public class UbicacionDAO implements Serializable {

    public UbicacionDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public UbicacionDAO() {
        this.emf = Persistence.createEntityManagerFactory("conexionBD");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ubicacion ubicacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Producto productoOrphanCheck = ubicacion.getProducto();
        if (productoOrphanCheck != null) {
            Ubicacion oldUbicacionOfProducto = productoOrphanCheck.getUbicacion();
            if (oldUbicacionOfProducto != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Producto " + productoOrphanCheck + " already has an item of type Ubicacion whose producto column cannot be null. Please make another selection for the producto field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = ubicacion.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdProducto());
                ubicacion.setProducto(producto);
            }
            em.persist(ubicacion);
            if (producto != null) {
                producto.setUbicacion(ubicacion);
                producto = em.merge(producto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUbicacion(ubicacion.getIdProducto()) != null) {
                throw new PreexistingEntityException("Ubicacion " + ubicacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ubicacion ubicacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ubicacion persistentUbicacion = em.find(Ubicacion.class, ubicacion.getIdProducto());
            Producto productoOld = persistentUbicacion.getProducto();
            Producto productoNew = ubicacion.getProducto();
            List<String> illegalOrphanMessages = null;
            if (productoNew != null && !productoNew.equals(productoOld)) {
                Ubicacion oldUbicacionOfProducto = productoNew.getUbicacion();
                if (oldUbicacionOfProducto != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Producto " + productoNew + " already has an item of type Ubicacion whose producto column cannot be null. Please make another selection for the producto field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdProducto());
                ubicacion.setProducto(productoNew);
            }
            ubicacion = em.merge(ubicacion);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.setUbicacion(null);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.setUbicacion(ubicacion);
                productoNew = em.merge(productoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ubicacion.getIdProducto();
                if (findUbicacion(id) == null) {
                    throw new NonexistentEntityException("The ubicacion with id " + id + " no longer exists.");
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
            Ubicacion ubicacion;
            try {
                ubicacion = em.getReference(Ubicacion.class, id);
                ubicacion.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ubicacion with id " + id + " no longer exists.", enfe);
            }
            Producto producto = ubicacion.getProducto();
            if (producto != null) {
                producto.setUbicacion(null);
                producto = em.merge(producto);
            }
            em.remove(ubicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ubicacion> findUbicacionEntities() {
        return findUbicacionEntities(true, -1, -1);
    }

    public List<Ubicacion> findUbicacionEntities(int maxResults, int firstResult) {
        return findUbicacionEntities(false, maxResults, firstResult);
    }

    private List<Ubicacion> findUbicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ubicacion.class));
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

    public Ubicacion findUbicacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ubicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getUbicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ubicacion> rt = cq.from(Ubicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
