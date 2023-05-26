/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.time.LocalDate;

/**
 *
 * @author jgfch
 */
public class Venta {
    private int idVenta;
    private int idProducto;
    private LocalDate fechaVenta;
    private int cantidadVendida;
    private double precioUnitario;
    private double precioTotal;
    
    // Constructor
    public Venta(int idVenta, int idProducto, LocalDate fechaVenta, int cantidadVendida,
                 double precioUnitario, double precioTotal) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.fechaVenta = fechaVenta;
        this.cantidadVendida = cantidadVendida;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
    }
    
    // Getters y setters
    
    public int getIdVenta() {
        return idVenta;
    }
    
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    
    public int getIdProducto() {
        return idProducto;
    }
    
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public LocalDate getFechaVenta() {
        return fechaVenta;
    }
    
    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    
    public int getCantidadVendida() {
        return cantidadVendida;
    }
    
    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
    
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public double getPrecioTotal() {
        return precioTotal;
    }
    
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
