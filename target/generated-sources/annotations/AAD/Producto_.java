package AAD;

import AAD.Devolucion;
import AAD.Inventario;
import AAD.ProductoPedido;
import AAD.Proveedor;
import AAD.ReporteProducto;
import AAD.Ubicacion;
import AAD.Venta;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-01T23:34:59")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile SingularAttribute<Producto, Date> fechaCaducidad;
    public static volatile SingularAttribute<Producto, Ubicacion> ubicacion;
    public static volatile SingularAttribute<Producto, String> categoria;
    public static volatile SingularAttribute<Producto, String> nombre;
    public static volatile CollectionAttribute<Producto, ReporteProducto> reporteProductoCollection;
    public static volatile SingularAttribute<Producto, BigDecimal> precio;
    public static volatile SingularAttribute<Producto, Date> fechaLlegada;
    public static volatile CollectionAttribute<Producto, Venta> ventaCollection;
    public static volatile SingularAttribute<Producto, Integer> stockActual;
    public static volatile CollectionAttribute<Producto, Devolucion> devolucionCollection;
    public static volatile SingularAttribute<Producto, Proveedor> proveedor;
    public static volatile CollectionAttribute<Producto, ProductoPedido> productoPedidoCollection;
    public static volatile SingularAttribute<Producto, Integer> idProducto;
    public static volatile SingularAttribute<Producto, Inventario> inventario;

}