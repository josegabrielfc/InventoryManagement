/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba_bd_mia;

import Dao.ReporteDAO;
import Entidades.Devolucion;
import Entidades.Reporte;
import Negocio.control.DevolucionC;
import java.util.Date;

/**
 *
 * @author Efranor
 */
public class Prueba_BD_Mia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DevolucionC devo = new DevolucionC();
        devo.registrarDevolucion(new Devolucion(1010, 1001, 1030, new Date(1, 2, 3), 10, "falla producto"));
    }

}
