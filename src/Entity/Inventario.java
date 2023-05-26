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
public class Inventario {
    private int idProducto;
    private int stockActual;
    private int stockMinimo;
    private int stockMaximo;
    private LocalDate fechaLlegada;
    private LocalDate fechaCaducidad;
    
    // Constructor
    public Inventario(int idProducto, int stockActual, int stockMinimo, int stockMaximo,
                      LocalDate fechaLlegada, LocalDate fechaCaducidad) {
        this.idProducto = idProducto;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
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
    
    public int getStockActual() {
        return stockActual;
    }
    
    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }
    
    public int getStockMinimo() {
        return stockMinimo;
    }
    
    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    
    public int getStockMaximo() {
        return stockMaximo;
    }
    
    public void setStockMaximo(int stockMaximo) {
        this.stockMaximo = stockMaximo;
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

