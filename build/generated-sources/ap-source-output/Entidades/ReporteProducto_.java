package Entidades;

import Entidades.Producto;
import Entidades.Reporte;
import Entidades.ReporteProductoPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-02T22:49:40")
@StaticMetamodel(ReporteProducto.class)
public class ReporteProducto_ { 

    public static volatile SingularAttribute<ReporteProducto, ReporteProductoPK> reporteProductoPK;
    public static volatile SingularAttribute<ReporteProducto, Integer> cantidad;
    public static volatile SingularAttribute<ReporteProducto, Producto> producto;
    public static volatile SingularAttribute<ReporteProducto, Reporte> reporte;

}