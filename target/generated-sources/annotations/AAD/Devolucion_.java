package AAD;

import AAD.Producto;
import AAD.Venta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-01T23:34:59")
@StaticMetamodel(Devolucion.class)
public class Devolucion_ { 

    public static volatile SingularAttribute<Devolucion, Date> fechaDevolucion;
    public static volatile SingularAttribute<Devolucion, String> motivoDevolucion;
    public static volatile SingularAttribute<Devolucion, Producto> idProducto;
    public static volatile SingularAttribute<Devolucion, Integer> cantidadDevuelta;
    public static volatile SingularAttribute<Devolucion, Integer> idDevolucion;
    public static volatile SingularAttribute<Devolucion, Venta> idVenta;

}