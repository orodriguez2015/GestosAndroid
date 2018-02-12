package com.oscar.utilities;

/**
 * Clase NumberOperations que contiene métodos para trabajar con números
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class NumberOperations {

    /**
     * Comprueba si un String contiene un dato de tipo Integer
     * @param dato: String
     * @return True si es un Integer y false en caso contrario
     */
    public static boolean isInteger(String dato) {
        boolean exito = false;

        try {
            Integer.parseInt(dato);
            exito = true;

        } catch(Exception e) {
            exito = false;
        }

        return exito;
    }


    /**
     * Comprueba si un String contiene un dato de tipo Double
     * @param dato: String
     * @return True si es un Integer y false en caso contrario
     */
    public static boolean isDouble(String dato) {
        boolean exito = false;

        try {
            Double.parseDouble(dato);
            exito = true;

        } catch(Exception e) {
            exito = false;
        }

        return exito;
    }

}
