package com.oscar.utilities.exception;

/**
 * Excepción que se lanza cuando se produce un error al lanzar
 * una aplicación
 *
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class LanzarAplicacionException extends Exception {

    private String packageNameApplication = null;

    /**
     * Constructor
     * @param message String
     * @param packageNameApplication String
     */
    public LanzarAplicacionException(String message,String packageNameApplication) {
        super(message);
        this.packageNameApplication = packageNameApplication;
    }

    /**
     * Constructor
     * @param message String
     * @param packageNameApplication String
     * @param cause Throwable
     */
    public LanzarAplicacionException(String message,String packageNameApplication, Throwable cause) {
        super(message, cause);
        this.packageNameApplication = packageNameApplication;
    }

}
