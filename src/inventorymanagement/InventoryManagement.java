/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import java.util.Scanner;

/**
 *
 * @author jgfch
 */
public class InventoryManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);

        // Menú principal
        int opcion = 0;
        while (opcion != 9) {
            System.out.println("==== Gestión de Inventario ====");
            System.out.println("1. Devolución");
            System.out.println("2. Inventario");
            System.out.println("3. Pedido");
            System.out.println("4. Producto");
            System.out.println("5. Producto-Pedido");
            System.out.println("6. Proveedor");
            System.out.println("7. Reporte");
            System.out.println("8. Reporte-Producto");
            System.out.println("9. Ubicación");
            System.out.println("10. Venta");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    gestionarDevolucion();
                    break;
                case 2:
                    gestionarInventario();
                    break;
                case 3:
                    gestionarPedido();
                    break;
                case 4:
                    gestionarProducto();
                    break;
                case 5:
                    gestionarProductoPedido();
                    break;
                case 6:
                    gestionarProveedor();
                    break;
                case 7:
                    gestionarReporte();
                    break;
                case 8:
                    gestionarReporteProducto();
                    break;
                case 9:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione nuevamente.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void gestionarDevolucion() {
        // Lógica para la entidad Devolución
        // ...
        System.out.println("Gestión de Devolución");
        // Agrega aquí la lógica específica para la entidad Devolución
    }

    private static void gestionarInventario() {
        // Lógica para la entidad Inventario
        // ...
        System.out.println("Gestión de Inventario");
        // Agrega aquí la lógica específica para la entidad Inventario
    }

    private static void gestionarPedido() {
        // Lógica para la entidad Pedido
        // ...
        System.out.println("Gestión de Pedido");
        // Agrega aquí la lógica específica para la entidad Pedido
    }

    private static void gestionarProducto() {
        // Lógica para la entidad Producto
        // ...
        System.out.println("Gestión de Producto");
        // Agrega aquí la lógica específica para la entidad Producto
    }

    private static void gestionarProductoPedido() {
        // Lógica para la entidad Producto-Pedido
        // ...
        System.out.println("Gestión de Producto-Pedido");
        // Agrega aquí la lógica específica para la entidad Producto-Pedido
    }

    private static void gestionarProveedor() {
        // Lógica para la entidad Proveedor
        // ...
        System.out.println("Gestión de Proveedor");
        // Agrega aquí la lógica específica para la entidad Proveedor
    }

    private static void gestionarReporte() {
        // Lógica para la entidad Reporte
        // ...
        System.out.println("Gestión de Reporte");
        // Agrega aquí la lógica específica para la entidad Reporte
    }

    private static void gestionarReporteProducto() {
        // Lógica para la entidad Reporte-Producto
        // ...
        System.out.println("Gestión de Reporte-Producto");
        // Agrega aquí la lógica específica para la entidad Reporte-Producto
    }

    private static void gestionarUbicacion() {

    }

    private static void gestionarVentas() {
    }
}
