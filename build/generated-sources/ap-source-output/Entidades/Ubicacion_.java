package Entidades;

import Entidades.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-02T22:49:40")
@StaticMetamodel(Ubicacion.class)
public class Ubicacion_ { 

    public static volatile SingularAttribute<Ubicacion, Integer> columna;
    public static volatile SingularAttribute<Ubicacion, Integer> estante;
    public static volatile SingularAttribute<Ubicacion, Integer> fila;
    public static volatile SingularAttribute<Ubicacion, Integer> idProducto;
    public static volatile SingularAttribute<Ubicacion, Producto> producto;

}