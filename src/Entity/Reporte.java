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
public class Reporte {
    private int idReporte;
    private LocalDate fechaReporte;
    private String descripcion;
    
    // Constructor
    public Reporte(int idReporte, LocalDate fechaReporte, String descripcion) {
        this.idReporte = idReporte;
        this.fechaReporte = fechaReporte;
        this.descripcion = descripcion;
    }
    
    // Getters y setters
    
    public int getIdReporte() {
        return idReporte;
    }
    
    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }
    
    public LocalDate getFechaReporte() {
        return fechaReporte;
    }
    
    public void setFechaReporte(LocalDate fechaReporte) {
        this.fechaReporte = fechaReporte;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
