package AAD;

import AAD.Devolucion;
import AAD.Producto;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-01T23:34:59")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Integer> cantidadVendida;
    public static volatile SingularAttribute<Venta, BigDecimal> precioUnitario;
    public static volatile CollectionAttribute<Venta, Devolucion> devolucionCollection;
    public static volatile SingularAttribute<Venta, Producto> idProducto;
    public static volatile SingularAttribute<Venta, BigDecimal> precioTotal;
    public static volatile SingularAttribute<Venta, Integer> idVenta;
    public static volatile SingularAttribute<Venta, Date> fechaVenta;

}