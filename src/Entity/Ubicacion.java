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
public class Ubicacion {
    private int idProducto;
    private int estante;
    private int fila;
    private int columna;
    
    // Constructor
    public Ubicacion(int idProducto, int estante, int fila, int columna) {
        this.idProducto = idProducto;
        this.estante = estante;
        this.fila = fila;
        this.columna = columna;
    }
    
    // Getters y setters
    
    public int getIdProducto() {
        return idProducto;
    }
    
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public int getEstante() {
        return estante;
    }
    
    public void setEstante(int estante) {
        this.estante = estante;
    }
    
    public int getFila() {
        return fila;
    }
    
    public void setFila(int fila) {
        this.fila = fila;
    }
    
    public int getColumna() {
        return columna;
    }
    
    public void setColumna(int columna) {
        this.columna = columna;
    }
}
