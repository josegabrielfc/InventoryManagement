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
import Entidades.Pedido;
import Entidades.Producto;
import Entidades.ProductoPedido;
import Entidades.ProductoPedidoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Efranor
 */
public class ProductoPedidoDAO implements Serializable {

    public ProductoPedidoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ProductoPedidoDAO() {
        this.emf = Persistence.createEntityManagerFactory("conexionBD");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoPedido productoPedido) throws PreexistingEntityException, Exception {
        if (productoPedido.getProductoPedidoPK() == null) {
            productoPedido.setProductoPedidoPK(new ProductoPedidoPK());
        }
        productoPedido.getProductoPedidoPK().setIdPedido(productoPedido.getPedido().getIdPedido());
        productoPedido.getProductoPedidoPK().setIdProducto(productoPedido.getProducto().getIdProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido = productoPedido.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdPedido());
                productoPedido.setPedido(pedido);
            }
            Producto producto = productoPedido.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdProducto());
                productoPedido.setProducto(producto);
            }
            em.persist(productoPedido);
            if (pedido != null) {
                pedido.getProductoPedidoList().add(productoPedido);
                pedido = em.merge(pedido);
            }
            if (producto != null) {
                producto.getProductoPedidoList().add(productoPedido);
                producto = em.merge(producto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductoPedido(productoPedido.getProductoPedidoPK()) != null) {
                throw new PreexistingEntityException("ProductoPedido " + productoPedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoPedido productoPedido) throws NonexistentEntityException, Exception {
        productoPedido.getProductoPedidoPK().setIdPedido(productoPedido.getPedido().getIdPedido());
        productoPedido.getProductoPedidoPK().setIdProducto(productoPedido.getProducto().getIdProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoPedido persistentProductoPedido = em.find(ProductoPedido.class, productoPedido.getProductoPedidoPK());
            Pedido pedidoOld = persistentProductoPedido.getPedido();
            Pedido pedidoNew = productoPedido.getPedido();
            Producto productoOld = persistentProductoPedido.getProducto();
            Producto productoNew = productoPedido.getProducto();
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdPedido());
                productoPedido.setPedido(pedidoNew);
            }
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdProducto());
                productoPedido.setProducto(productoNew);
            }
            productoPedido = em.merge(productoPedido);
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getProductoPedidoList().remove(productoPedido);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getProductoPedidoList().add(productoPedido);
                pedidoNew = em.merge(pedidoNew);
            }
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getProductoPedidoList().remove(productoPedido);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getProductoPedidoList().add(productoPedido);
                productoNew = em.merge(productoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProductoPedidoPK id = productoPedido.getProductoPedidoPK();
                if (findProductoPedido(id) == null) {
                    throw new NonexistentEntityException("The productoPedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductoPedidoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoPedido productoPedido;
            try {
                productoPedido = em.getReference(ProductoPedido.class, id);
                productoPedido.getProductoPedidoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoPedido with id " + id + " no longer exists.", enfe);
            }
            Pedido pedido = productoPedido.getPedido();
            if (pedido != null) {
                pedido.getProductoPedidoList().remove(productoPedido);
                pedido = em.merge(pedido);
            }
            Producto producto = productoPedido.getProducto();
            if (producto != null) {
                producto.getProductoPedidoList().remove(productoPedido);
                producto = em.merge(producto);
            }
            em.remove(productoPedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductoPedido> findProductoPedidoEntities() {
        return findProductoPedidoEntities(true, -1, -1);
    }

    public List<ProductoPedido> findProductoPedidoEntities(int maxResults, int firstResult) {
        return findProductoPedidoEntities(false, maxResults, firstResult);
    }

    private List<ProductoPedido> findProductoPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoPedido.class));
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

    public ProductoPedido findProductoPedido(ProductoPedidoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoPedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoPedido> rt = cq.from(ProductoPedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
