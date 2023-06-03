/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Efranor
 */
@Embeddable
public class ProductoPedidoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_pedido")
    private int idPedido;
    @Basic(optional = false)
    @Column(name = "id_producto")
    private int idProducto;

    public ProductoPedidoPK() {
    }

    public ProductoPedidoPK(int idPedido, int idProducto) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPedido;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoPedidoPK)) {
            return false;
        }
        ProductoPedidoPK other = (ProductoPedidoPK) object;
        if (this.idPedido != other.idPedido) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ProductoPedidoPK[ idPedido=" + idPedido + ", idProducto=" + idProducto + " ]";
    }
    
}
