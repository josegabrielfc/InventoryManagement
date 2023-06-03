/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.exceptions.IllegalOrphanException;
import Dao.exceptions.NonexistentEntityException;
import Entidades.Reporte;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.ReporteProducto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Efranor
 */
public class ReporteDAO implements Serializable {

    public ReporteDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ReporteDAO() {
        this.emf = Persistence.createEntityManagerFactory("conexionBD");
    }
    
    

    public void create(Reporte reporte) {
        if (reporte.getReporteProductoList() == null) {
            reporte.setReporteProductoList(new ArrayList<ReporteProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ReporteProducto> attachedReporteProductoList = new ArrayList<ReporteProducto>();
            for (ReporteProducto reporteProductoListReporteProductoToAttach : reporte.getReporteProductoList()) {
                reporteProductoListReporteProductoToAttach = em.getReference(reporteProductoListReporteProductoToAttach.getClass(), reporteProductoListReporteProductoToAttach.getReporteProductoPK());
                attachedReporteProductoList.add(reporteProductoListReporteProductoToAttach);
            }
            reporte.setReporteProductoList(attachedReporteProductoList);
            em.persist(reporte);
            for (ReporteProducto reporteProductoListReporteProducto : reporte.getReporteProductoList()) {
                Reporte oldReporteOfReporteProductoListReporteProducto = reporteProductoListReporteProducto.getReporte();
                reporteProductoListReporteProducto.setReporte(reporte);
                reporteProductoListReporteProducto = em.merge(reporteProductoListReporteProducto);
                if (oldReporteOfReporteProductoListReporteProducto != null) {
                    oldReporteOfReporteProductoListReporteProducto.getReporteProductoList().remove(reporteProductoListReporteProducto);
                    oldReporteOfReporteProductoListReporteProducto = em.merge(oldReporteOfReporteProductoListReporteProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reporte reporte) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reporte persistentReporte = em.find(Reporte.class, reporte.getIdReporte());
            List<ReporteProducto> reporteProductoListOld = persistentReporte.getReporteProductoList();
            List<ReporteProducto> reporteProductoListNew = reporte.getReporteProductoList();
            List<String> illegalOrphanMessages = null;
            for (ReporteProducto reporteProductoListOldReporteProducto : reporteProductoListOld) {
                if (!reporteProductoListNew.contains(reporteProductoListOldReporteProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReporteProducto " + reporteProductoListOldReporteProducto + " since its reporte field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ReporteProducto> attachedReporteProductoListNew = new ArrayList<ReporteProducto>();
            for (ReporteProducto reporteProductoListNewReporteProductoToAttach : reporteProductoListNew) {
                reporteProductoListNewReporteProductoToAttach = em.getReference(reporteProductoListNewReporteProductoToAttach.getClass(), reporteProductoListNewReporteProductoToAttach.getReporteProductoPK());
                attachedReporteProductoListNew.add(reporteProductoListNewReporteProductoToAttach);
            }
            reporteProductoListNew = attachedReporteProductoListNew;
            reporte.setReporteProductoList(reporteProductoListNew);
            reporte = em.merge(reporte);
            for (ReporteProducto reporteProductoListNewReporteProducto : reporteProductoListNew) {
                if (!reporteProductoListOld.contains(reporteProductoListNewReporteProducto)) {
                    Reporte oldReporteOfReporteProductoListNewReporteProducto = reporteProductoListNewReporteProducto.getReporte();
                    reporteProductoListNewReporteProducto.setReporte(reporte);
                    reporteProductoListNewReporteProducto = em.merge(reporteProductoListNewReporteProducto);
                    if (oldReporteOfReporteProductoListNewReporteProducto != null && !oldReporteOfReporteProductoListNewReporteProducto.equals(reporte)) {
                        oldReporteOfReporteProductoListNewReporteProducto.getReporteProductoList().remove(reporteProductoListNewReporteProducto);
                        oldReporteOfReporteProductoListNewReporteProducto = em.merge(oldReporteOfReporteProductoListNewReporteProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reporte.getIdReporte();
                if (findReporte(id) == null) {
                    throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.");
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
            Reporte reporte;
            try {
                reporte = em.getReference(Reporte.class, id);
                reporte.getIdReporte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ReporteProducto> reporteProductoListOrphanCheck = reporte.getReporteProductoList();
            for (ReporteProducto reporteProductoListOrphanCheckReporteProducto : reporteProductoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reporte (" + reporte + ") cannot be destroyed since the ReporteProducto " + reporteProductoListOrphanCheckReporteProducto + " in its reporteProductoList field has a non-nullable reporte field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(reporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reporte> findReporteEntities() {
        return findReporteEntities(true, -1, -1);
    }

    public List<Reporte> findReporteEntities(int maxResults, int firstResult) {
        return findReporteEntities(false, maxResults, firstResult);
    }

    private List<Reporte> findReporteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reporte.class));
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

    public Reporte findReporte(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reporte.class, id);
        } finally {
            em.close();
        }
    }

    public int getReporteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reporte> rt = cq.from(Reporte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
