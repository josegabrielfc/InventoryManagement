package Entidades;

import Entidades.ReporteProducto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-02T22:49:40")
@StaticMetamodel(Reporte.class)
public class Reporte_ { 

    public static volatile SingularAttribute<Reporte, String> descripcion;
    public static volatile ListAttribute<Reporte, ReporteProducto> reporteProductoList;
    public static volatile SingularAttribute<Reporte, Integer> idReporte;
    public static volatile SingularAttribute<Reporte, Date> fechaReporte;

}