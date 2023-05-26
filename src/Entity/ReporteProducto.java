/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author jgfch
 */
public class ReporteProducto {
    private int idReporte;
    private int idProducto;
    private int cantidad;
    
    // Constructor
    public ReporteProducto(int idReporte, int idProducto, int cantidad) {
        this.idReporte = idReporte;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
    
    // Getters y setters
    
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
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

