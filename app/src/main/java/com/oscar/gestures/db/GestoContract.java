package com.oscar.gestures.db;

import android.provider.BaseColumns;

/**
 * Clase GestureContract que contendrá la definición de las tablas de la base de datos
 *
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez </a>
 */
public class GestoContract  {

    /*
     * Códigos de error que se lanzan si una operación ejecutada contra la BBDD ha lanzado un error
     */
    public static Integer ERROR_INSERTAR_GESTO   = 1;
    public static Integer ERROR_RECUPERAR_GESTOS = 2;


    /**
     * Definición de las columnas para la tabla Gesto de la base de datos
     */
    public static abstract class GestoEntry implements BaseColumns {

        public static final String TABLE_NAME            ="gesto";
        public static final String ID                    = "id";
        public static final String NOMBRE_GESTO          = "nombre";
        public static final String APLICACION_GESTO      = "aplicacion";
        public static final String LOGO_APLICACION_GESTO = "logoAplicacion";
    }

}
