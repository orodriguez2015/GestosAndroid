package com.oscar.utilities.dialog;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Clase con los parámetros necesarios para poder crear un AlertDialog
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class ParamsAlertDialogVO {

    @NonNull
    private Activity activity;

    @NonNull
    private String mensaje;

    @NonNull
    private String titulo;

    private String aceptar;

    private String cancelar;


    /**
     * Constructor
     * @param activity Activity sobre el que se muestra el AlertDialog
     * @param mensaje  Mensaje a mostrar en el AlertDialog
     * @param titulo  Titulo del AlertDialog
     * @param aceptar  Texto para el botón [Aceptar]
     * @param cancelar Texto para el botón [Cancelar]
     */
    public ParamsAlertDialogVO(@NonNull Activity activity, @NonNull String mensaje, @NonNull String titulo, String aceptar,String cancelar) {
        this.activity = activity;
        this.mensaje = mensaje;
        this.titulo = titulo;
        this.aceptar = aceptar;
        this.cancelar = cancelar;
    }



    /**
     * Devuelve el mensaje a mostrar en el AlertDialog
     * @return String
     */
    @NonNull
    public String getMensaje() {
        return mensaje;
    }


    /**
     * Establece el mensaje a mostrar en el AlertDialog
     * @param mensaje String
     */
    public void setMensaje(@NonNull String mensaje) {
        this.mensaje = mensaje;
    }


    /**
     * Devuelve el texto del botón [Aceptar]
     * @return String
     */
    @NonNull
    public String getAceptar() {

        return aceptar;
    }

    /**
     * Establece el texto del botón [Aceptar]
     * @param aceptar String
     */
    public void setAceptar(@NonNull String aceptar) {
        this.aceptar = aceptar;
    }

    /**
     * Devuelve el texto del botón [Cancelar]
     * @return String
     */
    @NonNull
    public String getCancelar() {
        return cancelar;
    }

    /**
     * Establece el texto del botón [Cancelar]
     * @param cancelar String
     */
    public void setCancelar(@NonNull String cancelar) {
        this.cancelar = cancelar;
    }


    /**
     * Devuelve el activity sobre el que se muestra el AlertDialog
     * @return Activity
     */
    @NonNull
    public Activity getActivity() {
        return activity;
    }


    /**
     * Establece el activity sobre el que se muestra el AlertDialog
     * @param activity Activity
     */
    public void setActivity(@NonNull Activity activity) {
        this.activity = activity;
    }


    /**
     * Devuelve el título del AlertDialog
     * @return String
     */
    @NonNull
    public String getTitulo() {
        return titulo;
    }


    /**
     * Establece el título del AlertDialog
     * @param titulo String
     */
    public void setTitulo(@NonNull String titulo) {
        this.titulo = titulo;
    }
}
