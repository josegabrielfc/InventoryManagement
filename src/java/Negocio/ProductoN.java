/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dao.ProductoDAO;
import Entidades.Devolucion;
import Entidades.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Efranor
 */
public class ProductoN {

    private ProductoDAO dao;

    public ProductoN() {
        dao = new ProductoDAO();
    }

    public String registrarProducto(Producto p) {
        String msg = "";
        try {
            Producto prueba = dao.findProducto(p.getIdProducto());
            if (prueba == null) {
                dao.create(p);
                msg = "Producto registrado";
            } else {
                msg = "Error, el producto ya esta registrado";
            }
        } catch (Exception ex) {
            msg = ex.getMessage();
        }
        return msg;
    }

    public Producto buscarProducto(int id) {
        Producto p = null;
        try {
            p = dao.findProducto(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return p;
    }

    public List<Producto> consultar() {
        List<Producto> productos = new ArrayList();
        try {
            productos = dao.findProductoEntities();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return productos;
    }

    public String eliminar(int id) {
        String msg = "";
        try {
            dao.destroy(id);
            msg = "Producto eliminada";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    public String actualizar(Producto p) {
        String msg = "";
        try {
            Producto prueba = dao.findProducto(p.getIdProducto());
            if (prueba == null) {
                msg = "Error: No existe el producto con id: " + p.getIdProducto();
            } else {
                dao.edit(prueba);
                msg = "Producto actualizado";
            }
        } catch (Exception e) {
            msg = e.getMessage();
        }

        return msg;
    }
}
