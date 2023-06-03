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
import java.util.ArrayList;
import java.util.List;
import Entidades.Pedido;
import Entidades.Proveedor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Efranor
 */
public class ProveedorDAO implements Serializable {

    public ProveedorDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ProveedorDAO() {
        this.emf = Persistence.createEntityManagerFactory("conexionBD");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) {
        if (proveedor.getProductoList() == null) {
            proveedor.setProductoList(new ArrayList<Producto>());
        }
        if (proveedor.getPedidoList() == null) {
            proveedor.setPedidoList(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : proveedor.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getIdProducto());
                attachedProductoList.add(productoListProductoToAttach);
            }
            proveedor.setProductoList(attachedProductoList);
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : proveedor.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdPedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            proveedor.setPedidoList(attachedPedidoList);
            em.persist(proveedor);
            for (Producto productoListProducto : proveedor.getProductoList()) {
                Proveedor oldProveedorOfProductoListProducto = productoListProducto.getProveedor();
                productoListProducto.setProveedor(proveedor);
                productoListProducto = em.merge(productoListProducto);
                if (oldProveedorOfProductoListProducto != null) {
                    oldProveedorOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldProveedorOfProductoListProducto = em.merge(oldProveedorOfProductoListProducto);
                }
            }
            for (Pedido pedidoListPedido : proveedor.getPedidoList()) {
                Proveedor oldIdProveedorOfPedidoListPedido = pedidoListPedido.getIdProveedor();
                pedidoListPedido.setIdProveedor(proveedor);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldIdProveedorOfPedidoListPedido != null) {
                    oldIdProveedorOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldIdProveedorOfPedidoListPedido = em.merge(oldIdProveedorOfPedidoListPedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getIdProveedor());
            List<Producto> productoListOld = persistentProveedor.getProductoList();
            List<Producto> productoListNew = proveedor.getProductoList();
            List<Pedido> pedidoListOld = persistentProveedor.getPedidoList();
            List<Pedido> pedidoListNew = proveedor.getPedidoList();
            List<String> illegalOrphanMessages = null;
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoListOldProducto + " since its proveedor field is not nullable.");
                }
            }
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoListOldPedido + " since its idProveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getIdProducto());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            proveedor.setProductoList(productoListNew);
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdPedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            proveedor.setPedidoList(pedidoListNew);
            proveedor = em.merge(proveedor);
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Proveedor oldProveedorOfProductoListNewProducto = productoListNewProducto.getProveedor();
                    productoListNewProducto.setProveedor(proveedor);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldProveedorOfProductoListNewProducto != null && !oldProveedorOfProductoListNewProducto.equals(proveedor)) {
                        oldProveedorOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldProveedorOfProductoListNewProducto = em.merge(oldProveedorOfProductoListNewProducto);
                    }
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Proveedor oldIdProveedorOfPedidoListNewPedido = pedidoListNewPedido.getIdProveedor();
                    pedidoListNewPedido.setIdProveedor(proveedor);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldIdProveedorOfPedidoListNewPedido != null && !oldIdProveedorOfPedidoListNewPedido.equals(proveedor)) {
                        oldIdProveedorOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldIdProveedorOfPedidoListNewPedido = em.merge(oldIdProveedorOfPedidoListNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedor.getIdProveedor();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Producto> productoListOrphanCheck = proveedor.getProductoList();
            for (Producto productoListOrphanCheckProducto : productoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Producto " + productoListOrphanCheckProducto + " in its productoList field has a non-nullable proveedor field.");
            }
            List<Pedido> pedidoListOrphanCheck = proveedor.getPedidoList();
            for (Pedido pedidoListOrphanCheckPedido : pedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Pedido " + pedidoListOrphanCheckPedido + " in its pedidoList field has a non-nullable idProveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
