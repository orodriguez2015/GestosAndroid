package com.oscar.gestures.vo;

import java.io.Serializable;

/**
 * Clase Gesto que contiene el nombre, gesture y aplicación que se abre una vez
 * que el usuario dibuja el gesto
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class Gesto implements Serializable {

    private Long id;
    private String nombre;
    private String aplicacion;

    /**
     * Constructor
     */
    public Gesto() {

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
     * Devuelve el nombre del gesto
     * @return String
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * Establece el nombre del gesto
     * @param nombre String
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el id del gesto
     * @return Long
     */
    public Long getId() {
        return id;
    }


    /**
     * Establece el id del gesto
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
    }
}
