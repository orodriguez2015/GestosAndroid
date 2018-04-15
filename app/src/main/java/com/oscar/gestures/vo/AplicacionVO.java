package com.oscar.gestures.vo;

import android.graphics.drawable.Drawable;

/**
 * Clase AplicacionVO con representa a una aplicación instalada en el dispositivo
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class AplicacionVO {

    private Boolean valorPorDefecto = false;
    private String nombreAplicacion;
    private transient Drawable icono;


    /**
     * Constructor
     * @param nombreAplicacion String
     * @param icono Drawable
     */
    public AplicacionVO(String nombreAplicacion, Drawable icono) {
        this.nombreAplicacion = nombreAplicacion;
        this.icono = icono;
        this.valorPorDefecto = false;
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


    /**
     * True si el objeto es un valor por defecto de un conjunto de objetos de tipo AplicacionVO
     * @return boolean
     */
    public Boolean isValorPorDefecto() {
        return valorPorDefecto;
    }

    /**
     * Establece el valor por defecto
     * @param valorPorDefecto boolean
     */
    public void setValorPorDefecto(Boolean valorPorDefecto) {
        this.valorPorDefecto = valorPorDefecto;
    }

}
