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
public class Pedido {
    private int idPedido;
    private Proveedor proveedor;
    private LocalDate fechaPedido;
    private LocalDate fechaLlegadaPrevista;
    private double precioTotal;
    
    // Constructor
    public Pedido(int idPedido, Proveedor proveedor, LocalDate fechaPedido, LocalDate fechaLlegadaPrevista,
                  double precioTotal) {
        this.idPedido = idPedido;
        this.proveedor = proveedor;
        this.fechaPedido = fechaPedido;
        this.fechaLlegadaPrevista = fechaLlegadaPrevista;
        this.precioTotal = precioTotal;
    }
    
    // Getters y setters
    
    public int getIdPedido() {
        return idPedido;
    }
    
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
    public Proveedor getProveedor() {
        return proveedor;
    }
    
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    public LocalDate getFechaPedido() {
        return fechaPedido;
    }
    
    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    
    public LocalDate getFechaLlegadaPrevista() {
        return fechaLlegadaPrevista;
    }
    
    public void setFechaLlegadaPrevista(LocalDate fechaLlegadaPrevista) {
        this.fechaLlegadaPrevista = fechaLlegadaPrevista;
    }
    
    public double getPrecioTotal() {
        return precioTotal;
    }
    
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
