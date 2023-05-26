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
public class Devolucion {
    private int idDevolucion;
    private Venta venta;
    private Producto producto;
    private LocalDate fechaDevolucion;
    private int cantidadDevuelta;
    private String motivoDevolucion;
    
    // Constructor
    public Devolucion(int idDevolucion, Venta venta, Producto producto, LocalDate fechaDevolucion,
                      int cantidadDevuelta, String motivoDevolucion) {
        this.idDevolucion = idDevolucion;
        this.venta = venta;
        this.producto = producto;
        this.fechaDevolucion = fechaDevolucion;
        this.cantidadDevuelta = cantidadDevuelta;
        this.motivoDevolucion = motivoDevolucion;
    }
    
    // Getters y setters
    
    public int getIdDevolucion() {
        return idDevolucion;
    }
    
    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }
    
    public Venta getVenta() {
        return venta;
    }
    
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
    
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    public int getCantidadDevuelta() {
        return cantidadDevuelta;
    }
    
    public void setCantidadDevuelta(int cantidadDevuelta) {
        this.cantidadDevuelta = cantidadDevuelta;
    }
    
    public String getMotivoDevolucion() {
        return motivoDevolucion;
    }
    
    public void setMotivoDevolucion(String motivoDevolucion) {
        this.motivoDevolucion = motivoDevolucion;
    }
}
