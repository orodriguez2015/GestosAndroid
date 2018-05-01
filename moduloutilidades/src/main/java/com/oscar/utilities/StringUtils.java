package com.oscar.utilities;

/**
 * Clase con operaciones de manejo de Strings
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class StringUtils {

    private static final String ESPACIO_BLANCO = " ";

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


    /**
     * Concatena dos string con un espacio en blanco entre ambos
     * @param origen String
     * @param destino String
     * @return String resultado de la concatenación
     */
    public static String concatWithBlankSpace(String origen,String destino) {
        StringBuffer sb = new StringBuffer();

        if(origen!=null) {
            sb = sb.append(origen);
        }

        if(destino!=null) {
            sb = sb.append(ESPACIO_BLANCO).append(destino);
        }

        return sb.toString();

    }
}
