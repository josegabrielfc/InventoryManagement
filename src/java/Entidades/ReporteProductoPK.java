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
public class ReporteProductoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_reporte")
    private int idReporte;
    @Basic(optional = false)
    @Column(name = "id_producto")
    private int idProducto;

    public ReporteProductoPK() {
    }

    public ReporteProductoPK(int idReporte, int idProducto) {
        this.idReporte = idReporte;
        this.idProducto = idProducto;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
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
        hash += (int) idReporte;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteProductoPK)) {
            return false;
        }
        ReporteProductoPK other = (ReporteProductoPK) object;
        if (this.idReporte != other.idReporte) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ReporteProductoPK[ idReporte=" + idReporte + ", idProducto=" + idProducto + " ]";
    }
    
}
