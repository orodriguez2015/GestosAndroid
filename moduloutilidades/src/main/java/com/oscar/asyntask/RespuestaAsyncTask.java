package com.oscar.asyntask;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene la respuesta devuelta por un AsyncTask
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class RespuestaAsyncTask implements Serializable {

    private Integer status    = null;
    private String descStatus = null;
    private List items = null;

    /**
     * Constructor
     * @param status: Integer
     * @param descStatus: String
     */
    public RespuestaAsyncTask(Integer status, String descStatus) {
        this.status     = status;
        this.descStatus = descStatus;
    }

    /**
     * Devuelve la respuesta devuelta por el AsyncTask
     * @return Integer
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Establece la respuesta devuelta por el AsyncTask
     * @param status: Integer
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Devuelve la descripción del código de error
     * @return String
     */
    public String getDescStatus() {
        return descStatus;
    }

    /**
     * Permite establecer la descripción del código de error
     * @param descStatus: String
     */
    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    /**
     * Devuelve items en caso de que la respuesta recupere datos
     * @return List
     */
    public List getItems() {
        return items;
    }

    /**
     * Establece items en caso de que la respuesta recupere datos
     * @param items List
     */
    public void setItems(List items) {
        this.items = items;
    }

}
