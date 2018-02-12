package com.oscar.gestures.vo;

import java.io.Serializable;

/**
 * Clase Gesto
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class Gesto implements Serializable {

    private String descripcion;

    /**
     * Constructor
     */
    public Gesto() {
    }

    /**
     * Constructor
     * @param descripcion String
     */
    public Gesto(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la descripción del gesto
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }


    /**
     * Establece una descripción al gesto
     * @param descripcion String
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
