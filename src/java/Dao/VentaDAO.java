/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.exceptions.IllegalOrphanException;
import Dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Producto;
import Entidades.Devolucion;
import Entidades.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Efranor
 */
public class VentaDAO implements Serializable {

    public VentaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public VentaDAO() {
        this.emf = Persistence.createEntityManagerFactory("conexionBD");
    }

    public void create(Venta venta) {
        if (venta.getDevolucionList() == null) {
            venta.setDevolucionList(new ArrayList<Devolucion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idProducto = venta.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getIdProducto());
                venta.setIdProducto(idProducto);
            }
            List<Devolucion> attachedDevolucionList = new ArrayList<Devolucion>();
            for (Devolucion devolucionListDevolucionToAttach : venta.getDevolucionList()) {
                devolucionListDevolucionToAttach = em.getReference(devolucionListDevolucionToAttach.getClass(), devolucionListDevolucionToAttach.getIdDevolucion());
                attachedDevolucionList.add(devolucionListDevolucionToAttach);
            }
            venta.setDevolucionList(attachedDevolucionList);
            em.persist(venta);
            if (idProducto != null) {
                idProducto.getVentaList().add(venta);
                idProducto = em.merge(idProducto);
            }
            for (Devolucion devolucionListDevolucion : venta.getDevolucionList()) {
                Venta oldIdVentaOfDevolucionListDevolucion = devolucionListDevolucion.getIdVenta();
                devolucionListDevolucion.setIdVenta(venta);
                devolucionListDevolucion = em.merge(devolucionListDevolucion);
                if (oldIdVentaOfDevolucionListDevolucion != null) {
                    oldIdVentaOfDevolucionListDevolucion.getDevolucionList().remove(devolucionListDevolucion);
                    oldIdVentaOfDevolucionListDevolucion = em.merge(oldIdVentaOfDevolucionListDevolucion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getIdVenta());
            Producto idProductoOld = persistentVenta.getIdProducto();
            Producto idProductoNew = venta.getIdProducto();
            List<Devolucion> devolucionListOld = persistentVenta.getDevolucionList();
            List<Devolucion> devolucionListNew = venta.getDevolucionList();
            List<String> illegalOrphanMessages = null;
            for (Devolucion devolucionListOldDevolucion : devolucionListOld) {
                if (!devolucionListNew.contains(devolucionListOldDevolucion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Devolucion " + devolucionListOldDevolucion + " since its idVenta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getIdProducto());
                venta.setIdProducto(idProductoNew);
            }
            List<Devolucion> attachedDevolucionListNew = new ArrayList<Devolucion>();
            for (Devolucion devolucionListNewDevolucionToAttach : devolucionListNew) {
                devolucionListNewDevolucionToAttach = em.getReference(devolucionListNewDevolucionToAttach.getClass(), devolucionListNewDevolucionToAttach.getIdDevolucion());
                attachedDevolucionListNew.add(devolucionListNewDevolucionToAttach);
            }
            devolucionListNew = attachedDevolucionListNew;
            venta.setDevolucionList(devolucionListNew);
            venta = em.merge(venta);
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getVentaList().remove(venta);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getVentaList().add(venta);
                idProductoNew = em.merge(idProductoNew);
            }
            for (Devolucion devolucionListNewDevolucion : devolucionListNew) {
                if (!devolucionListOld.contains(devolucionListNewDevolucion)) {
                    Venta oldIdVentaOfDevolucionListNewDevolucion = devolucionListNewDevolucion.getIdVenta();
                    devolucionListNewDevolucion.setIdVenta(venta);
                    devolucionListNewDevolucion = em.merge(devolucionListNewDevolucion);
                    if (oldIdVentaOfDevolucionListNewDevolucion != null && !oldIdVentaOfDevolucionListNewDevolucion.equals(venta)) {
                        oldIdVentaOfDevolucionListNewDevolucion.getDevolucionList().remove(devolucionListNewDevolucion);
                        oldIdVentaOfDevolucionListNewDevolucion = em.merge(oldIdVentaOfDevolucionListNewDevolucion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venta.getIdVenta();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
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
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getIdVenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Devolucion> devolucionListOrphanCheck = venta.getDevolucionList();
            for (Devolucion devolucionListOrphanCheckDevolucion : devolucionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Venta (" + venta + ") cannot be destroyed since the Devolucion " + devolucionListOrphanCheckDevolucion + " in its devolucionList field has a non-nullable idVenta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Producto idProducto = venta.getIdProducto();
            if (idProducto != null) {
                idProducto.getVentaList().remove(venta);
                idProducto = em.merge(idProducto);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
