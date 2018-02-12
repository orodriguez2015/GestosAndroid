package com.oscar.utilities.exception;

/**
 *  Excepción GestoException que se lanza cuando se produce algún error durante
 *  el manejo de gestos
 *
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class GestoException extends Exception  {

    /**
     * Constructor
     */
    public GestoException() {
        super();
    }

    /**
     * Constructor
     * @param message String
     */
    public GestoException(String message) {
        super(message);
    }

    /**
     * Constructor
     * @param message String
     * @param t Throwable
     */
    public GestoException(String message,Throwable t) {
        super(message,t);
    }
}
