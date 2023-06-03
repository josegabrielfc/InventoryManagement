/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Efranor
 */
@Entity
@Table(name = "Producto_Pedido")
@NamedQueries({
    @NamedQuery(name = "ProductoPedido.findAll", query = "SELECT p FROM ProductoPedido p")})
public class ProductoPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductoPedidoPK productoPedidoPK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    public ProductoPedido() {
    }

    public ProductoPedido(int idPedido, int idProducto) {
        this.productoPedidoPK = new ProductoPedidoPK(idPedido, idProducto);
    }

    public ProductoPedido(int idPedido, int idProducto, int cantidad) {

        this.productoPedidoPK = new ProductoPedidoPK(idPedido, idProducto);
        this.cantidad = cantidad;
    }

    public ProductoPedidoPK getProductoPedidoPK() {
        return productoPedidoPK;
    }

    public void setProductoPedidoPK(ProductoPedidoPK productoPedidoPK) {
        this.productoPedidoPK = productoPedidoPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoPedidoPK != null ? productoPedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoPedido)) {
            return false;
        }
        ProductoPedido other = (ProductoPedido) object;
        if ((this.productoPedidoPK == null && other.productoPedidoPK != null) || (this.productoPedidoPK != null && !this.productoPedidoPK.equals(other.productoPedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ProductoPedido[ productoPedidoPK=" + productoPedidoPK + " ]";
    }

}
