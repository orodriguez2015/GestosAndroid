package com.oscar.asyntask;

import android.content.Context;

import com.oscar.utilities.vo.DatosUsuarioVO;

import java.io.Serializable;

/**
 * Clase ParametrosAsyncTask utilizada para el paso de parámetros en aquellas
 * tareas AsyncTask para las que sea necesaria.
 *
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class ParametrosAsyncTask<T> implements Serializable {

    private Context context        = null;
    private DatosUsuarioVO usuario = null;
    private T parametro = null;


    /**
     * Constructor
     * @param parametro T
     */
    public ParametrosAsyncTask(T parametro) {
        this.parametro = parametro;
    }


    /**
     * Devuelve el parámetro que se pase al constructor
     * @return T
     */
    public T getParametro() {
        return parametro;
    }


    /**
     * Establece el parámetro que se pase al constructor
     * @param parametro T
     */
    public void setParametro(T parametro) {
        this.parametro = parametro;
    }



    /**
     * Devuelve el Context
     * @return Context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Establece el Context
     * @param context: Context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Devuelve los datos del usuario
     * @return DatosUsuarioVO
     */
    public DatosUsuarioVO getUsuario() {
        return usuario;
    }

    /**
     * Establece los datos del usuario
     * @param usuario: Context
     */
    public void setUsuario(DatosUsuarioVO usuario) {
        this.usuario = usuario;
    }


}
