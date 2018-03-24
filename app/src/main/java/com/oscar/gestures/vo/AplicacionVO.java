package com.oscar.gestures.vo;

import android.graphics.drawable.Drawable;

/**
 * Clase AplicacionVO con representa a una aplicación instalada en el dispositivo
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class AplicacionVO {

    private String nombreAplicacion;
    private Drawable icono;


    /**
     * Constructor
     * @param nombreAplicacion String
     * @param icono Drawable
     */
    public AplicacionVO(String nombreAplicacion, Drawable icono) {
        this.nombreAplicacion = nombreAplicacion;
        this.icono = icono;
    }


    /**
     * Devuelve el nombre de la aplicación
     * @return String
     */
    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    /**
     * Estableceel nombre de la aplicación
     * @param nombreAplicacion String
     */
    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    /**
     * Devuelve el icono de la aplicación
     * @return Drawable
     */
    public Drawable getIcono() {
        return icono;
    }

    /**
     * Establece el icono de la aplicación
     * @param icono Drawable
     */
    public void setIcono(Drawable icono) {
        this.icono = icono;
    }
}
