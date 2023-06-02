package AAD;

import AAD.Producto;
import AAD.Reporte;
import AAD.ReporteProductoPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-01T23:34:59")
@StaticMetamodel(ReporteProducto.class)
public class ReporteProducto_ { 

    public static volatile SingularAttribute<ReporteProducto, ReporteProductoPK> reporteProductoPK;
    public static volatile SingularAttribute<ReporteProducto, Integer> cantidad;
    public static volatile SingularAttribute<ReporteProducto, Producto> producto;
    public static volatile SingularAttribute<ReporteProducto, Reporte> reporte;

}