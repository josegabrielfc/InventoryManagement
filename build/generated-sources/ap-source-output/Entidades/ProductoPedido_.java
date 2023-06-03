package Entidades;

import Entidades.Pedido;
import Entidades.Producto;
import Entidades.ProductoPedidoPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-02T22:49:40")
@StaticMetamodel(ProductoPedido.class)
public class ProductoPedido_ { 

    public static volatile SingularAttribute<ProductoPedido, ProductoPedidoPK> productoPedidoPK;
    public static volatile SingularAttribute<ProductoPedido, Pedido> pedido;
    public static volatile SingularAttribute<ProductoPedido, Integer> cantidad;
    public static volatile SingularAttribute<ProductoPedido, Producto> producto;

}