package com.oscar.gestures.vo;

import android.gesture.Gesture;

import java.io.Serializable;

/**
 * Clase Gesto que contiene el nombre, gesture y aplicación que se abre una vez
 * que el usuario dibuja el gesto
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class Gesto implements Serializable {

    private String descripcion;
    private String aplicacion;
    private Gesture gesture;


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


    /**
     * Nombre de la aplicación que se lanzará cuando se detecte el gesto
     * @return String
     */
    public String getAplicacion() {
        return aplicacion;
    }


    /**
     * Establece el nombre de la aplicación que se lanzará cuando se detecte el gesto
     * @param aplicacion String
     */
    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    /**
     * Devuelve el gesto
     * @return Gesture
     */
    public Gesture getGesture() {
        return gesture;
    }


    /**
     * Establece el gesto
     * @param gesture Gesture
     */
    public void setGesture(Gesture gesture) {
        this.gesture = gesture;
    }
}
