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
import Entidades.Ubicacion;
import Entidades.Proveedor;
import Entidades.Inventario;
import Entidades.Devolucion;
import Entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import Entidades.Venta;
import Entidades.ProductoPedido;
import Entidades.ReporteProducto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Efranor
 */
public class ProductoDAO implements Serializable {

    public ProductoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ProductoDAO() {
        this.emf = Persistence.createEntityManagerFactory("conexionBD");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getDevolucionList() == null) {
            producto.setDevolucionList(new ArrayList<Devolucion>());
        }
        if (producto.getVentaList() == null) {
            producto.setVentaList(new ArrayList<Venta>());
        }
        if (producto.getProductoPedidoList() == null) {
            producto.setProductoPedidoList(new ArrayList<ProductoPedido>());
        }
        if (producto.getReporteProductoList() == null) {
            producto.setReporteProductoList(new ArrayList<ReporteProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ubicacion ubicacion = producto.getUbicacion();
            if (ubicacion != null) {
                ubicacion = em.getReference(ubicacion.getClass(), ubicacion.getIdProducto());
                producto.setUbicacion(ubicacion);
            }
            Proveedor proveedor = producto.getProveedor();
            if (proveedor != null) {
                proveedor = em.getReference(proveedor.getClass(), proveedor.getIdProveedor());
                producto.setProveedor(proveedor);
            }
            Inventario inventario = producto.getInventario();
            if (inventario != null) {
                inventario = em.getReference(inventario.getClass(), inventario.getIdProducto());
                producto.setInventario(inventario);
            }
            List<Devolucion> attachedDevolucionList = new ArrayList<Devolucion>();
            for (Devolucion devolucionListDevolucionToAttach : producto.getDevolucionList()) {
                devolucionListDevolucionToAttach = em.getReference(devolucionListDevolucionToAttach.getClass(), devolucionListDevolucionToAttach.getIdDevolucion());
                attachedDevolucionList.add(devolucionListDevolucionToAttach);
            }
            producto.setDevolucionList(attachedDevolucionList);
            List<Venta> attachedVentaList = new ArrayList<Venta>();
            for (Venta ventaListVentaToAttach : producto.getVentaList()) {
                ventaListVentaToAttach = em.getReference(ventaListVentaToAttach.getClass(), ventaListVentaToAttach.getIdVenta());
                attachedVentaList.add(ventaListVentaToAttach);
            }
            producto.setVentaList(attachedVentaList);
            List<ProductoPedido> attachedProductoPedidoList = new ArrayList<ProductoPedido>();
            for (ProductoPedido productoPedidoListProductoPedidoToAttach : producto.getProductoPedidoList()) {
                productoPedidoListProductoPedidoToAttach = em.getReference(productoPedidoListProductoPedidoToAttach.getClass(), productoPedidoListProductoPedidoToAttach.getProductoPedidoPK());
                attachedProductoPedidoList.add(productoPedidoListProductoPedidoToAttach);
            }
            producto.setProductoPedidoList(attachedProductoPedidoList);
            List<ReporteProducto> attachedReporteProductoList = new ArrayList<ReporteProducto>();
            for (ReporteProducto reporteProductoListReporteProductoToAttach : producto.getReporteProductoList()) {
                reporteProductoListReporteProductoToAttach = em.getReference(reporteProductoListReporteProductoToAttach.getClass(), reporteProductoListReporteProductoToAttach.getReporteProductoPK());
                attachedReporteProductoList.add(reporteProductoListReporteProductoToAttach);
            }
            producto.setReporteProductoList(attachedReporteProductoList);
            em.persist(producto);
            if (ubicacion != null) {
                Producto oldProductoOfUbicacion = ubicacion.getProducto();
                if (oldProductoOfUbicacion != null) {
                    oldProductoOfUbicacion.setUbicacion(null);
                    oldProductoOfUbicacion = em.merge(oldProductoOfUbicacion);
                }
                ubicacion.setProducto(producto);
                ubicacion = em.merge(ubicacion);
            }
            if (proveedor != null) {
                proveedor.getProductoList().add(producto);
                proveedor = em.merge(proveedor);
            }
            if (inventario != null) {
                Producto oldProductoOfInventario = inventario.getProducto();
                if (oldProductoOfInventario != null) {
                    oldProductoOfInventario.setInventario(null);
                    oldProductoOfInventario = em.merge(oldProductoOfInventario);
                }
                inventario.setProducto(producto);
                inventario = em.merge(inventario);
            }
            for (Devolucion devolucionListDevolucion : producto.getDevolucionList()) {
                Producto oldIdProductoOfDevolucionListDevolucion = devolucionListDevolucion.getIdProducto();
                devolucionListDevolucion.setIdProducto(producto);
                devolucionListDevolucion = em.merge(devolucionListDevolucion);
                if (oldIdProductoOfDevolucionListDevolucion != null) {
                    oldIdProductoOfDevolucionListDevolucion.getDevolucionList().remove(devolucionListDevolucion);
                    oldIdProductoOfDevolucionListDevolucion = em.merge(oldIdProductoOfDevolucionListDevolucion);
                }
            }
            for (Venta ventaListVenta : producto.getVentaList()) {
                Producto oldIdProductoOfVentaListVenta = ventaListVenta.getIdProducto();
                ventaListVenta.setIdProducto(producto);
                ventaListVenta = em.merge(ventaListVenta);
                if (oldIdProductoOfVentaListVenta != null) {
                    oldIdProductoOfVentaListVenta.getVentaList().remove(ventaListVenta);
                    oldIdProductoOfVentaListVenta = em.merge(oldIdProductoOfVentaListVenta);
                }
            }
            for (ProductoPedido productoPedidoListProductoPedido : producto.getProductoPedidoList()) {
                Producto oldProductoOfProductoPedidoListProductoPedido = productoPedidoListProductoPedido.getProducto();
                productoPedidoListProductoPedido.setProducto(producto);
                productoPedidoListProductoPedido = em.merge(productoPedidoListProductoPedido);
                if (oldProductoOfProductoPedidoListProductoPedido != null) {
                    oldProductoOfProductoPedidoListProductoPedido.getProductoPedidoList().remove(productoPedidoListProductoPedido);
                    oldProductoOfProductoPedidoListProductoPedido = em.merge(oldProductoOfProductoPedidoListProductoPedido);
                }
            }
            for (ReporteProducto reporteProductoListReporteProducto : producto.getReporteProductoList()) {
                Producto oldProductoOfReporteProductoListReporteProducto = reporteProductoListReporteProducto.getProducto();
                reporteProductoListReporteProducto.setProducto(producto);
                reporteProductoListReporteProducto = em.merge(reporteProductoListReporteProducto);
                if (oldProductoOfReporteProductoListReporteProducto != null) {
                    oldProductoOfReporteProductoListReporteProducto.getReporteProductoList().remove(reporteProductoListReporteProducto);
                    oldProductoOfReporteProductoListReporteProducto = em.merge(oldProductoOfReporteProductoListReporteProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            Ubicacion ubicacionOld = persistentProducto.getUbicacion();
            Ubicacion ubicacionNew = producto.getUbicacion();
            Proveedor proveedorOld = persistentProducto.getProveedor();
            Proveedor proveedorNew = producto.getProveedor();
            Inventario inventarioOld = persistentProducto.getInventario();
            Inventario inventarioNew = producto.getInventario();
            List<Devolucion> devolucionListOld = persistentProducto.getDevolucionList();
            List<Devolucion> devolucionListNew = producto.getDevolucionList();
            List<Venta> ventaListOld = persistentProducto.getVentaList();
            List<Venta> ventaListNew = producto.getVentaList();
            List<ProductoPedido> productoPedidoListOld = persistentProducto.getProductoPedidoList();
            List<ProductoPedido> productoPedidoListNew = producto.getProductoPedidoList();
            List<ReporteProducto> reporteProductoListOld = persistentProducto.getReporteProductoList();
            List<ReporteProducto> reporteProductoListNew = producto.getReporteProductoList();
            List<String> illegalOrphanMessages = null;
            if (ubicacionOld != null && !ubicacionOld.equals(ubicacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Ubicacion " + ubicacionOld + " since its producto field is not nullable.");
            }
            if (inventarioOld != null && !inventarioOld.equals(inventarioNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Inventario " + inventarioOld + " since its producto field is not nullable.");
            }
            for (Devolucion devolucionListOldDevolucion : devolucionListOld) {
                if (!devolucionListNew.contains(devolucionListOldDevolucion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Devolucion " + devolucionListOldDevolucion + " since its idProducto field is not nullable.");
                }
            }
            for (Venta ventaListOldVenta : ventaListOld) {
                if (!ventaListNew.contains(ventaListOldVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Venta " + ventaListOldVenta + " since its idProducto field is not nullable.");
                }
            }
            for (ProductoPedido productoPedidoListOldProductoPedido : productoPedidoListOld) {
                if (!productoPedidoListNew.contains(productoPedidoListOldProductoPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductoPedido " + productoPedidoListOldProductoPedido + " since its producto field is not nullable.");
                }
            }
            for (ReporteProducto reporteProductoListOldReporteProducto : reporteProductoListOld) {
                if (!reporteProductoListNew.contains(reporteProductoListOldReporteProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReporteProducto " + reporteProductoListOldReporteProducto + " since its producto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ubicacionNew != null) {
                ubicacionNew = em.getReference(ubicacionNew.getClass(), ubicacionNew.getIdProducto());
                producto.setUbicacion(ubicacionNew);
            }
            if (proveedorNew != null) {
                proveedorNew = em.getReference(proveedorNew.getClass(), proveedorNew.getIdProveedor());
                producto.setProveedor(proveedorNew);
            }
            if (inventarioNew != null) {
                inventarioNew = em.getReference(inventarioNew.getClass(), inventarioNew.getIdProducto());
                producto.setInventario(inventarioNew);
            }
            List<Devolucion> attachedDevolucionListNew = new ArrayList<Devolucion>();
            for (Devolucion devolucionListNewDevolucionToAttach : devolucionListNew) {
                devolucionListNewDevolucionToAttach = em.getReference(devolucionListNewDevolucionToAttach.getClass(), devolucionListNewDevolucionToAttach.getIdDevolucion());
                attachedDevolucionListNew.add(devolucionListNewDevolucionToAttach);
            }
            devolucionListNew = attachedDevolucionListNew;
            producto.setDevolucionList(devolucionListNew);
            List<Venta> attachedVentaListNew = new ArrayList<Venta>();
            for (Venta ventaListNewVentaToAttach : ventaListNew) {
                ventaListNewVentaToAttach = em.getReference(ventaListNewVentaToAttach.getClass(), ventaListNewVentaToAttach.getIdVenta());
                attachedVentaListNew.add(ventaListNewVentaToAttach);
            }
            ventaListNew = attachedVentaListNew;
            producto.setVentaList(ventaListNew);
            List<ProductoPedido> attachedProductoPedidoListNew = new ArrayList<ProductoPedido>();
            for (ProductoPedido productoPedidoListNewProductoPedidoToAttach : productoPedidoListNew) {
                productoPedidoListNewProductoPedidoToAttach = em.getReference(productoPedidoListNewProductoPedidoToAttach.getClass(), productoPedidoListNewProductoPedidoToAttach.getProductoPedidoPK());
                attachedProductoPedidoListNew.add(productoPedidoListNewProductoPedidoToAttach);
            }
            productoPedidoListNew = attachedProductoPedidoListNew;
            producto.setProductoPedidoList(productoPedidoListNew);
            List<ReporteProducto> attachedReporteProductoListNew = new ArrayList<ReporteProducto>();
            for (ReporteProducto reporteProductoListNewReporteProductoToAttach : reporteProductoListNew) {
                reporteProductoListNewReporteProductoToAttach = em.getReference(reporteProductoListNewReporteProductoToAttach.getClass(), reporteProductoListNewReporteProductoToAttach.getReporteProductoPK());
                attachedReporteProductoListNew.add(reporteProductoListNewReporteProductoToAttach);
            }
            reporteProductoListNew = attachedReporteProductoListNew;
            producto.setReporteProductoList(reporteProductoListNew);
            producto = em.merge(producto);
            if (ubicacionNew != null && !ubicacionNew.equals(ubicacionOld)) {
                Producto oldProductoOfUbicacion = ubicacionNew.getProducto();
                if (oldProductoOfUbicacion != null) {
                    oldProductoOfUbicacion.setUbicacion(null);
                    oldProductoOfUbicacion = em.merge(oldProductoOfUbicacion);
                }
                ubicacionNew.setProducto(producto);
                ubicacionNew = em.merge(ubicacionNew);
            }
            if (proveedorOld != null && !proveedorOld.equals(proveedorNew)) {
                proveedorOld.getProductoList().remove(producto);
                proveedorOld = em.merge(proveedorOld);
            }
            if (proveedorNew != null && !proveedorNew.equals(proveedorOld)) {
                proveedorNew.getProductoList().add(producto);
                proveedorNew = em.merge(proveedorNew);
            }
            if (inventarioNew != null && !inventarioNew.equals(inventarioOld)) {
                Producto oldProductoOfInventario = inventarioNew.getProducto();
                if (oldProductoOfInventario != null) {
                    oldProductoOfInventario.setInventario(null);
                    oldProductoOfInventario = em.merge(oldProductoOfInventario);
                }
                inventarioNew.setProducto(producto);
                inventarioNew = em.merge(inventarioNew);
            }
            for (Devolucion devolucionListNewDevolucion : devolucionListNew) {
                if (!devolucionListOld.contains(devolucionListNewDevolucion)) {
                    Producto oldIdProductoOfDevolucionListNewDevolucion = devolucionListNewDevolucion.getIdProducto();
                    devolucionListNewDevolucion.setIdProducto(producto);
                    devolucionListNewDevolucion = em.merge(devolucionListNewDevolucion);
                    if (oldIdProductoOfDevolucionListNewDevolucion != null && !oldIdProductoOfDevolucionListNewDevolucion.equals(producto)) {
                        oldIdProductoOfDevolucionListNewDevolucion.getDevolucionList().remove(devolucionListNewDevolucion);
                        oldIdProductoOfDevolucionListNewDevolucion = em.merge(oldIdProductoOfDevolucionListNewDevolucion);
                    }
                }
            }
            for (Venta ventaListNewVenta : ventaListNew) {
                if (!ventaListOld.contains(ventaListNewVenta)) {
                    Producto oldIdProductoOfVentaListNewVenta = ventaListNewVenta.getIdProducto();
                    ventaListNewVenta.setIdProducto(producto);
                    ventaListNewVenta = em.merge(ventaListNewVenta);
                    if (oldIdProductoOfVentaListNewVenta != null && !oldIdProductoOfVentaListNewVenta.equals(producto)) {
                        oldIdProductoOfVentaListNewVenta.getVentaList().remove(ventaListNewVenta);
                        oldIdProductoOfVentaListNewVenta = em.merge(oldIdProductoOfVentaListNewVenta);
                    }
                }
            }
            for (ProductoPedido productoPedidoListNewProductoPedido : productoPedidoListNew) {
                if (!productoPedidoListOld.contains(productoPedidoListNewProductoPedido)) {
                    Producto oldProductoOfProductoPedidoListNewProductoPedido = productoPedidoListNewProductoPedido.getProducto();
                    productoPedidoListNewProductoPedido.setProducto(producto);
                    productoPedidoListNewProductoPedido = em.merge(productoPedidoListNewProductoPedido);
                    if (oldProductoOfProductoPedidoListNewProductoPedido != null && !oldProductoOfProductoPedidoListNewProductoPedido.equals(producto)) {
                        oldProductoOfProductoPedidoListNewProductoPedido.getProductoPedidoList().remove(productoPedidoListNewProductoPedido);
                        oldProductoOfProductoPedidoListNewProductoPedido = em.merge(oldProductoOfProductoPedidoListNewProductoPedido);
                    }
                }
            }
            for (ReporteProducto reporteProductoListNewReporteProducto : reporteProductoListNew) {
                if (!reporteProductoListOld.contains(reporteProductoListNewReporteProducto)) {
                    Producto oldProductoOfReporteProductoListNewReporteProducto = reporteProductoListNewReporteProducto.getProducto();
                    reporteProductoListNewReporteProducto.setProducto(producto);
                    reporteProductoListNewReporteProducto = em.merge(reporteProductoListNewReporteProducto);
                    if (oldProductoOfReporteProductoListNewReporteProducto != null && !oldProductoOfReporteProductoListNewReporteProducto.equals(producto)) {
                        oldProductoOfReporteProductoListNewReporteProducto.getReporteProductoList().remove(reporteProductoListNewReporteProducto);
                        oldProductoOfReporteProductoListNewReporteProducto = em.merge(oldProductoOfReporteProductoListNewReporteProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Ubicacion ubicacionOrphanCheck = producto.getUbicacion();
            if (ubicacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Ubicacion " + ubicacionOrphanCheck + " in its ubicacion field has a non-nullable producto field.");
            }
            Inventario inventarioOrphanCheck = producto.getInventario();
            if (inventarioOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Inventario " + inventarioOrphanCheck + " in its inventario field has a non-nullable producto field.");
            }
            List<Devolucion> devolucionListOrphanCheck = producto.getDevolucionList();
            for (Devolucion devolucionListOrphanCheckDevolucion : devolucionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Devolucion " + devolucionListOrphanCheckDevolucion + " in its devolucionList field has a non-nullable idProducto field.");
            }
            List<Venta> ventaListOrphanCheck = producto.getVentaList();
            for (Venta ventaListOrphanCheckVenta : ventaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Venta " + ventaListOrphanCheckVenta + " in its ventaList field has a non-nullable idProducto field.");
            }
            List<ProductoPedido> productoPedidoListOrphanCheck = producto.getProductoPedidoList();
            for (ProductoPedido productoPedidoListOrphanCheckProductoPedido : productoPedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the ProductoPedido " + productoPedidoListOrphanCheckProductoPedido + " in its productoPedidoList field has a non-nullable producto field.");
            }
            List<ReporteProducto> reporteProductoListOrphanCheck = producto.getReporteProductoList();
            for (ReporteProducto reporteProductoListOrphanCheckReporteProducto : reporteProductoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the ReporteProducto " + reporteProductoListOrphanCheckReporteProducto + " in its reporteProductoList field has a non-nullable producto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor proveedor = producto.getProveedor();
            if (proveedor != null) {
                proveedor.getProductoList().remove(producto);
                proveedor = em.merge(proveedor);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
