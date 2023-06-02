package AAD;

import AAD.ReporteProducto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-01T23:34:59")
@StaticMetamodel(Reporte.class)
public class Reporte_ { 

    public static volatile SingularAttribute<Reporte, String> descripcion;
    public static volatile SingularAttribute<Reporte, Integer> idReporte;
    public static volatile SingularAttribute<Reporte, Date> fechaReporte;
    public static volatile CollectionAttribute<Reporte, ReporteProducto> reporteProductoCollection;

}