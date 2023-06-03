/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.control;

import Entidades.Devolucion;
import Negocio.DevolucionN;
import java.util.List;

/**
 *
 * @author Efranor
 */
public class DevolucionC extends DevolucionN {

    @Override
    public String actualizar(Devolucion d) {
        return super.actualizar(d); 
    }

    @Override
    public String eliminar(int id) {
        return super.eliminar(id);
    }

    @Override
    public List<Devolucion> consultar() {
        return super.consultar();
    }

    @Override
    public Devolucion buscarDevolucion(int id) {
        return super.buscarDevolucion(id);
    }

    @Override
    public String registrarDevolucion(Devolucion d) {
        return super.registrarDevolucion(d);
    }

}
