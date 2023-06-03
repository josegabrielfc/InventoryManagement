/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.exceptions.NonexistentEntityException;
import Dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Producto;
import Entidades.Reporte;
import Entidades.ReporteProducto;
import Entidades.ReporteProductoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Efranor
 */
public class ReporteProductoDAO implements Serializable {

    public ReporteProductoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ReporteProductoDAO() {
        this.emf = Persistence.createEntityManagerFactory("conexionBD");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReporteProducto reporteProducto) throws PreexistingEntityException, Exception {
        if (reporteProducto.getReporteProductoPK() == null) {
            reporteProducto.setReporteProductoPK(new ReporteProductoPK());
        }
        reporteProducto.getReporteProductoPK().setIdReporte(reporteProducto.getReporte().getIdReporte());
        reporteProducto.getReporteProductoPK().setIdProducto(reporteProducto.getProducto().getIdProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = reporteProducto.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdProducto());
                reporteProducto.setProducto(producto);
            }
            Reporte reporte = reporteProducto.getReporte();
            if (reporte != null) {
                reporte = em.getReference(reporte.getClass(), reporte.getIdReporte());
                reporteProducto.setReporte(reporte);
            }
            em.persist(reporteProducto);
            if (producto != null) {
                producto.getReporteProductoList().add(reporteProducto);
                producto = em.merge(producto);
            }
            if (reporte != null) {
                reporte.getReporteProductoList().add(reporteProducto);
                reporte = em.merge(reporte);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReporteProducto(reporteProducto.getReporteProductoPK()) != null) {
                throw new PreexistingEntityException("ReporteProducto " + reporteProducto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReporteProducto reporteProducto) throws NonexistentEntityException, Exception {
        reporteProducto.getReporteProductoPK().setIdReporte(reporteProducto.getReporte().getIdReporte());
        reporteProducto.getReporteProductoPK().setIdProducto(reporteProducto.getProducto().getIdProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReporteProducto persistentReporteProducto = em.find(ReporteProducto.class, reporteProducto.getReporteProductoPK());
            Producto productoOld = persistentReporteProducto.getProducto();
            Producto productoNew = reporteProducto.getProducto();
            Reporte reporteOld = persistentReporteProducto.getReporte();
            Reporte reporteNew = reporteProducto.getReporte();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdProducto());
                reporteProducto.setProducto(productoNew);
            }
            if (reporteNew != null) {
                reporteNew = em.getReference(reporteNew.getClass(), reporteNew.getIdReporte());
                reporteProducto.setReporte(reporteNew);
            }
            reporteProducto = em.merge(reporteProducto);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getReporteProductoList().remove(reporteProducto);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getReporteProductoList().add(reporteProducto);
                productoNew = em.merge(productoNew);
            }
            if (reporteOld != null && !reporteOld.equals(reporteNew)) {
                reporteOld.getReporteProductoList().remove(reporteProducto);
                reporteOld = em.merge(reporteOld);
            }
            if (reporteNew != null && !reporteNew.equals(reporteOld)) {
                reporteNew.getReporteProductoList().add(reporteProducto);
                reporteNew = em.merge(reporteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ReporteProductoPK id = reporteProducto.getReporteProductoPK();
                if (findReporteProducto(id) == null) {
                    throw new NonexistentEntityException("The reporteProducto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ReporteProductoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReporteProducto reporteProducto;
            try {
                reporteProducto = em.getReference(ReporteProducto.class, id);
                reporteProducto.getReporteProductoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reporteProducto with id " + id + " no longer exists.", enfe);
            }
            Producto producto = reporteProducto.getProducto();
            if (producto != null) {
                producto.getReporteProductoList().remove(reporteProducto);
                producto = em.merge(producto);
            }
            Reporte reporte = reporteProducto.getReporte();
            if (reporte != null) {
                reporte.getReporteProductoList().remove(reporteProducto);
                reporte = em.merge(reporte);
            }
            em.remove(reporteProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReporteProducto> findReporteProductoEntities() {
        return findReporteProductoEntities(true, -1, -1);
    }

    public List<ReporteProducto> findReporteProductoEntities(int maxResults, int firstResult) {
        return findReporteProductoEntities(false, maxResults, firstResult);
    }

    private List<ReporteProducto> findReporteProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReporteProducto.class));
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

    public ReporteProducto findReporteProducto(ReporteProductoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReporteProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getReporteProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReporteProducto> rt = cq.from(ReporteProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
