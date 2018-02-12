package com.oscar.utilities.exception;

/**
 * Clase FileUtils con utilidades para el trabajo con ficheros
 *
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class FileException extends Exception {

    /**
     * Constructor
     */
    public FileException() {
        super();
    }

    /**
     * Constructor
     * @param message String
     */
    public FileException(String message) {
        super(message);
    }


    /**
     * Constructor
     * @param message String
     * @param t Throwable
     */
    public FileException(String message,Throwable t) {
        super(message,t);
    }

    /**
     * Constructor
     * @param t Throwable
     */
    public FileException(Throwable t) {
        super(t);
    }
}
