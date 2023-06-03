/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.exceptions.NonexistentEntityException;
import Entidades.Devolucion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Producto;
import Entidades.Venta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Efranor
 */
public class DevolucionDAO implements Serializable {

    public DevolucionDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DevolucionDAO() {
        this.emf = Persistence.createEntityManagerFactory("conexionBD");
    }

    public void create(Devolucion devolucion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idProducto = devolucion.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getIdProducto());
                devolucion.setIdProducto(idProducto);
            }
            Venta idVenta = devolucion.getIdVenta();
            if (idVenta != null) {
                idVenta = em.getReference(idVenta.getClass(), idVenta.getIdVenta());
                devolucion.setIdVenta(idVenta);
            }
            em.persist(devolucion);
            if (idProducto != null) {
                idProducto.getDevolucionList().add(devolucion);
                idProducto = em.merge(idProducto);
            }
            if (idVenta != null) {
                idVenta.getDevolucionList().add(devolucion);
                idVenta = em.merge(idVenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Devolucion devolucion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Devolucion persistentDevolucion = em.find(Devolucion.class, devolucion.getIdDevolucion());
            Producto idProductoOld = persistentDevolucion.getIdProducto();
            Producto idProductoNew = devolucion.getIdProducto();
            Venta idVentaOld = persistentDevolucion.getIdVenta();
            Venta idVentaNew = devolucion.getIdVenta();
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getIdProducto());
                devolucion.setIdProducto(idProductoNew);
            }
            if (idVentaNew != null) {
                idVentaNew = em.getReference(idVentaNew.getClass(), idVentaNew.getIdVenta());
                devolucion.setIdVenta(idVentaNew);
            }
            devolucion = em.merge(devolucion);
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getDevolucionList().remove(devolucion);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getDevolucionList().add(devolucion);
                idProductoNew = em.merge(idProductoNew);
            }
            if (idVentaOld != null && !idVentaOld.equals(idVentaNew)) {
                idVentaOld.getDevolucionList().remove(devolucion);
                idVentaOld = em.merge(idVentaOld);
            }
            if (idVentaNew != null && !idVentaNew.equals(idVentaOld)) {
                idVentaNew.getDevolucionList().add(devolucion);
                idVentaNew = em.merge(idVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = devolucion.getIdDevolucion();
                if (findDevolucion(id) == null) {
                    throw new NonexistentEntityException("The devolucion with id " + id + " no longer exists.");
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
            Devolucion devolucion;
            try {
                devolucion = em.getReference(Devolucion.class, id);
                devolucion.getIdDevolucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The devolucion with id " + id + " no longer exists.", enfe);
            }
            Producto idProducto = devolucion.getIdProducto();
            if (idProducto != null) {
                idProducto.getDevolucionList().remove(devolucion);
                idProducto = em.merge(idProducto);
            }
            Venta idVenta = devolucion.getIdVenta();
            if (idVenta != null) {
                idVenta.getDevolucionList().remove(devolucion);
                idVenta = em.merge(idVenta);
            }
            em.remove(devolucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Devolucion> findDevolucionEntities() {
        return findDevolucionEntities(true, -1, -1);
    }

    public List<Devolucion> findDevolucionEntities(int maxResults, int firstResult) {
        return findDevolucionEntities(false, maxResults, firstResult);
    }

    private List<Devolucion> findDevolucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Devolucion.class));
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

    public Devolucion findDevolucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Devolucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDevolucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Devolucion> rt = cq.from(Devolucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
