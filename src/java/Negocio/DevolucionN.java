/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dao.DevolucionDAO;
import Entidades.Devolucion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Efranor
 */
public class DevolucionN {

    private DevolucionDAO dao;

    public DevolucionN() {
        dao = new DevolucionDAO();
    }

    public String registrarDevolucion(Devolucion d) {
        String msg = "";
        try {
            Devolucion prueba = dao.findDevolucion(d.getIdDevolucion());
            if (prueba == null) {
                dao.create(d);
                msg = "Devolucion registrada";
            } else {
                msg = "Error, la devolucion ya está registrada";
            }
        } catch (Exception ex) {
            msg = ex.getMessage();
        }
        return msg;
    }

    public Devolucion buscarDevolucion(int id) {
        Devolucion c = null;
        try {
            c = dao.findDevolucion(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return c;
    }

    public List<Devolucion> consultar() {
        List<Devolucion> devoluciones = new ArrayList();
        try {
            devoluciones = dao.findDevolucionEntities();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return devoluciones;
    }

    public String eliminar(int id) {
        String msg = "";
        try {
            dao.destroy(id);
            msg = "Devolucion eliminada";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    public String actualizar(Devolucion d) {
        String msg = "";
        try {
            Devolucion prueba = dao.findDevolucion(d.getIdDevolucion());
            if (prueba == null) {
                msg = "Error: No existe la devolucion con id: " + d.getIdDevolucion();
            } else {
                dao.edit(prueba);
                msg = "Devolucion actualizada";
            }
        } catch (Exception e) {
            msg = e.getMessage();
        }

        return msg;
    }
}
