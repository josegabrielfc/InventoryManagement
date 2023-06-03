/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.control;

import Entidades.Producto;
import Negocio.ProductoN;
import java.util.List;

/**
 *
 * @author Efranor
 */
public class ProductoC extends ProductoN {

    @Override
    public String actualizar(Producto p) {
        return super.actualizar(p);
    }

    @Override
    public String eliminar(int id) {
        return super.eliminar(id);
    }

    @Override
    public List<Producto> consultar() {
        return super.consultar();
    }

    @Override
    public Producto buscarProducto(int id) {
        return super.buscarProducto(id);
    }

    @Override
    public String registrarProducto(Producto p) {
        return super.registrarProducto(p);
    }

}
