package AAD;

import AAD.Producto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-01T23:34:59")
@StaticMetamodel(Inventario.class)
public class Inventario_ { 

    public static volatile SingularAttribute<Inventario, Integer> stockMinimo;
    public static volatile SingularAttribute<Inventario, Date> fechaCaducidad;
    public static volatile SingularAttribute<Inventario, Date> fechaLlegada;
    public static volatile SingularAttribute<Inventario, Integer> stockActual;
    public static volatile SingularAttribute<Inventario, Integer> idProducto;
    public static volatile SingularAttribute<Inventario, Producto> producto;
    public static volatile SingularAttribute<Inventario, Integer> stockMaximo;

}