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
    private byte[] logoAplicacion;


    /**
     * Constructor
     */
    public Gesto() {

    }

    /**
     * Constructor
     * @param id Id del gesto
     * @param nombre String
     * @param aplicacion String
     */
    public Gesto(Long id, String nombre, String aplicacion) {
        this.id = id;
        this.nombre = nombre;
        this.aplicacion = aplicacion;
    }


    /**
     * Constructor
     * @param id Id del gesto
     * @param nombre String
     * @param aplicacion String
     * @param logoAplicacion byte[]
     */
    public Gesto(Long id, String nombre, String aplicacion, byte[] logoAplicacion) {
        this.id = id;
        this.nombre = nombre;
        this.aplicacion = aplicacion;
        this.logoAplicacion = logoAplicacion;
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

    /**
     * Devuelve el logo de la aplicación
     * @return byte[]
     */
    public byte[] getLogoAplicacion() {
        return logoAplicacion;
    }


    /**
     * Establece el logo de la aplicación
     * @param logoAplicacion Drawable
     */
    public void setLogoAplicacion(byte[] logoAplicacion) {
        this.logoAplicacion = logoAplicacion;
    }

}
