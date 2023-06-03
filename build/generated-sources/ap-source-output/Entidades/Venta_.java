package Entidades;

import Entidades.Devolucion;
import Entidades.Producto;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-02T22:49:40")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Integer> cantidadVendida;
    public static volatile SingularAttribute<Venta, BigDecimal> precioUnitario;
    public static volatile ListAttribute<Venta, Devolucion> devolucionList;
    public static volatile SingularAttribute<Venta, Producto> idProducto;
    public static volatile SingularAttribute<Venta, BigDecimal> precioTotal;
    public static volatile SingularAttribute<Venta, Integer> idVenta;
    public static volatile SingularAttribute<Venta, Date> fechaVenta;

}