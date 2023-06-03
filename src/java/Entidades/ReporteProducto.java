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
@Table(name = "Reporte_Producto")
@NamedQueries({
    @NamedQuery(name = "ReporteProducto.findAll", query = "SELECT r FROM ReporteProducto r")})
public class ReporteProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReporteProductoPK reporteProductoPK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "id_reporte", referencedColumnName = "id_reporte", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reporte reporte;

    public ReporteProducto() {
    }

    public ReporteProducto(ReporteProductoPK reporteProductoPK) {
        this.reporteProductoPK = reporteProductoPK;
    }

    public ReporteProducto(ReporteProductoPK reporteProductoPK, int cantidad) {
        this.reporteProductoPK = reporteProductoPK;
        this.cantidad = cantidad;
    }

    public ReporteProducto(int idReporte, int idProducto, int cantidad) {
        this.reporteProductoPK = new ReporteProductoPK(idReporte, idProducto);
        this.cantidad = cantidad;
    }

    public ReporteProductoPK getReporteProductoPK() {
        return reporteProductoPK;
    }

    public void setReporteProductoPK(ReporteProductoPK reporteProductoPK) {
        this.reporteProductoPK = reporteProductoPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reporteProductoPK != null ? reporteProductoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteProducto)) {
            return false;
        }
        ReporteProducto other = (ReporteProducto) object;
        if ((this.reporteProductoPK == null && other.reporteProductoPK != null) || (this.reporteProductoPK != null && !this.reporteProductoPK.equals(other.reporteProductoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ReporteProducto[ reporteProductoPK=" + reporteProductoPK + " ]";
    }
    
}
