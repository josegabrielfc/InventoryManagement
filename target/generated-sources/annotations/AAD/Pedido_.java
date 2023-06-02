package AAD;

import AAD.ProductoPedido;
import AAD.Proveedor;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-01T23:34:59")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Date> fechaLlegadaPrevista;
    public static volatile SingularAttribute<Pedido, Proveedor> idProveedor;
    public static volatile SingularAttribute<Pedido, Date> fechaPedido;
    public static volatile CollectionAttribute<Pedido, ProductoPedido> productoPedidoCollection;
    public static volatile SingularAttribute<Pedido, Integer> idPedido;
    public static volatile SingularAttribute<Pedido, BigDecimal> precioTotal;

}