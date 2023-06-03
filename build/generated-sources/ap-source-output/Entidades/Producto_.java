package Entidades;

import Entidades.Devolucion;
import Entidades.Inventario;
import Entidades.ProductoPedido;
import Entidades.Proveedor;
import Entidades.ReporteProducto;
import Entidades.Ubicacion;
import Entidades.Venta;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-02T22:49:40")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile SingularAttribute<Producto, Date> fechaCaducidad;
    public static volatile SingularAttribute<Producto, Ubicacion> ubicacion;
    public static volatile SingularAttribute<Producto, String> categoria;
    public static volatile ListAttribute<Producto, Devolucion> devolucionList;
    public static volatile SingularAttribute<Producto, String> nombre;
    public static volatile SingularAttribute<Producto, BigDecimal> precio;
    public static volatile SingularAttribute<Producto, Date> fechaLlegada;
    public static volatile ListAttribute<Producto, Venta> ventaList;
    public static volatile SingularAttribute<Producto, Integer> stockActual;
    public static volatile ListAttribute<Producto, ReporteProducto> reporteProductoList;
    public static volatile SingularAttribute<Producto, Proveedor> proveedor;
    public static volatile ListAttribute<Producto, ProductoPedido> productoPedidoList;
    public static volatile SingularAttribute<Producto, Integer> idProducto;
    public static volatile SingularAttribute<Producto, Inventario> inventario;

}