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
public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private String categoria;
    private Proveedor proveedor;
    private double precio;
    private int stockActual;
    private LocalDate fechaLlegada;
    private LocalDate fechaCaducidad;
    
    // Constructor
    public Producto(int idProducto, String nombre, String descripcion, String categoria, Proveedor proveedor,
                    double precio, int stockActual, LocalDate fechaLlegada, LocalDate fechaCaducidad) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.precio = precio;
        this.stockActual = stockActual;
        this.fechaLlegada = fechaLlegada;
        this.fechaCaducidad = fechaCaducidad;
    }
    
    // Getters y setters
    
    public int getIdProducto() {
        return idProducto;
    }
    
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public Proveedor getProveedor() {
        return proveedor;
    }
    
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public int getStockActual() {
        return stockActual;
    }
    
    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }
    
    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }
    
    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
    
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }
    
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
}

