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
public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String historialPedidos;
    
    // Constructor
    public Proveedor(int idProveedor, String nombre, String direccion, String telefono, String correoElectronico,
                     String historialPedidos) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.historialPedidos = historialPedidos;
    }
    
    // Getters y setters
    
    public int getIdProveedor() {
        return idProveedor;
    }
    
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    public String getHistorialPedidos() {
        return historialPedidos;
    }
    
    public void setHistorialPedidos(String historialPedidos) {
        this.historialPedidos = historialPedidos;
    }
}

