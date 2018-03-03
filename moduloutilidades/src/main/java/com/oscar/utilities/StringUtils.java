package com.oscar.utilities;

/**
 * Clase con operaciones de manejo de Strings
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class StringUtils {

    /**
     * Comprueba si una cadena de texto es nula o está vacía
     * @param dato String
     * @return True sino contiene datos y false en caso contrario
     */
    public static Boolean isEmpty(String dato) {
        Boolean exito = Boolean.FALSE;

        if(dato==null || dato.length()==0) {
            exito = Boolean.TRUE;
        }

        return exito;
    }
}
