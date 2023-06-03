/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.exceptions.IllegalOrphanException;
import Dao.exceptions.NonexistentEntityException;
import Entidades.Pedido;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Proveedor;
import Entidades.ProductoPedido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Efranor
 */
public class PedidoDAO implements Serializable {

    public PedidoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PedidoDAO() {
        this.emf = Persistence.createEntityManagerFactory("conexionBD");
    }

    
    public void create(Pedido pedido) {
        if (pedido.getProductoPedidoList() == null) {
            pedido.setProductoPedidoList(new ArrayList<ProductoPedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor idProveedor = pedido.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getIdProveedor());
                pedido.setIdProveedor(idProveedor);
            }
            List<ProductoPedido> attachedProductoPedidoList = new ArrayList<ProductoPedido>();
            for (ProductoPedido productoPedidoListProductoPedidoToAttach : pedido.getProductoPedidoList()) {
                productoPedidoListProductoPedidoToAttach = em.getReference(productoPedidoListProductoPedidoToAttach.getClass(), productoPedidoListProductoPedidoToAttach.getProductoPedidoPK());
                attachedProductoPedidoList.add(productoPedidoListProductoPedidoToAttach);
            }
            pedido.setProductoPedidoList(attachedProductoPedidoList);
            em.persist(pedido);
            if (idProveedor != null) {
                idProveedor.getPedidoList().add(pedido);
                idProveedor = em.merge(idProveedor);
            }
            for (ProductoPedido productoPedidoListProductoPedido : pedido.getProductoPedidoList()) {
                Pedido oldPedidoOfProductoPedidoListProductoPedido = productoPedidoListProductoPedido.getPedido();
                productoPedidoListProductoPedido.setPedido(pedido);
                productoPedidoListProductoPedido = em.merge(productoPedidoListProductoPedido);
                if (oldPedidoOfProductoPedidoListProductoPedido != null) {
                    oldPedidoOfProductoPedidoListProductoPedido.getProductoPedidoList().remove(productoPedidoListProductoPedido);
                    oldPedidoOfProductoPedidoListProductoPedido = em.merge(oldPedidoOfProductoPedidoListProductoPedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getIdPedido());
            Proveedor idProveedorOld = persistentPedido.getIdProveedor();
            Proveedor idProveedorNew = pedido.getIdProveedor();
            List<ProductoPedido> productoPedidoListOld = persistentPedido.getProductoPedidoList();
            List<ProductoPedido> productoPedidoListNew = pedido.getProductoPedidoList();
            List<String> illegalOrphanMessages = null;
            for (ProductoPedido productoPedidoListOldProductoPedido : productoPedidoListOld) {
                if (!productoPedidoListNew.contains(productoPedidoListOldProductoPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductoPedido " + productoPedidoListOldProductoPedido + " since its pedido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getIdProveedor());
                pedido.setIdProveedor(idProveedorNew);
            }
            List<ProductoPedido> attachedProductoPedidoListNew = new ArrayList<ProductoPedido>();
            for (ProductoPedido productoPedidoListNewProductoPedidoToAttach : productoPedidoListNew) {
                productoPedidoListNewProductoPedidoToAttach = em.getReference(productoPedidoListNewProductoPedidoToAttach.getClass(), productoPedidoListNewProductoPedidoToAttach.getProductoPedidoPK());
                attachedProductoPedidoListNew.add(productoPedidoListNewProductoPedidoToAttach);
            }
            productoPedidoListNew = attachedProductoPedidoListNew;
            pedido.setProductoPedidoList(productoPedidoListNew);
            pedido = em.merge(pedido);
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getPedidoList().remove(pedido);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getPedidoList().add(pedido);
                idProveedorNew = em.merge(idProveedorNew);
            }
            for (ProductoPedido productoPedidoListNewProductoPedido : productoPedidoListNew) {
                if (!productoPedidoListOld.contains(productoPedidoListNewProductoPedido)) {
                    Pedido oldPedidoOfProductoPedidoListNewProductoPedido = productoPedidoListNewProductoPedido.getPedido();
                    productoPedidoListNewProductoPedido.setPedido(pedido);
                    productoPedidoListNewProductoPedido = em.merge(productoPedidoListNewProductoPedido);
                    if (oldPedidoOfProductoPedidoListNewProductoPedido != null && !oldPedidoOfProductoPedidoListNewProductoPedido.equals(pedido)) {
                        oldPedidoOfProductoPedidoListNewProductoPedido.getProductoPedidoList().remove(productoPedidoListNewProductoPedido);
                        oldPedidoOfProductoPedidoListNewProductoPedido = em.merge(oldPedidoOfProductoPedidoListNewProductoPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedido.getIdPedido();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
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
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getIdPedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductoPedido> productoPedidoListOrphanCheck = pedido.getProductoPedidoList();
            for (ProductoPedido productoPedidoListOrphanCheckProductoPedido : productoPedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedido (" + pedido + ") cannot be destroyed since the ProductoPedido " + productoPedidoListOrphanCheckProductoPedido + " in its productoPedidoList field has a non-nullable pedido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor idProveedor = pedido.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getPedidoList().remove(pedido);
                idProveedor = em.merge(idProveedor);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
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

    public Pedido findPedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
