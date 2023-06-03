package Entidades;

import Entidades.ProductoPedido;
import Entidades.Proveedor;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-02T22:49:40")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Date> fechaLlegadaPrevista;
    public static volatile SingularAttribute<Pedido, Proveedor> idProveedor;
    public static volatile SingularAttribute<Pedido, Date> fechaPedido;
    public static volatile ListAttribute<Pedido, ProductoPedido> productoPedidoList;
    public static volatile SingularAttribute<Pedido, Integer> idPedido;
    public static volatile SingularAttribute<Pedido, BigDecimal> precioTotal;

}