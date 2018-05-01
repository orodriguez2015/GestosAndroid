package com.oscar.utilities.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Clase con operaciones de utilidad
 * Created by oscar on 27/08/16.
 */
public class AlertDialogHelper  {


    /**
     * Operación que crea un AlertDialog de Android simple con un determinado mensaje
     * @param params Parámetros del AlertDialog que contiene el activity, título, mensaje
     * @param aceptar Clase que implementa la interfaz DialogInterface.OnClickListener para la acción asociada
     *                 al botón de Aceptar
     * @param cancelar Clase que implementa la interfaz DialogInterface.OnClickListener para la acción asociada
     *                 al botón de Cancelar
     * @return AlertDialog
     */
    public static AlertDialog crearDialogoAlertaConfirmacion(ParamsAlertDialogVO params, DialogInterface.OnClickListener aceptar,DialogInterface.OnClickListener cancelar) {

        AlertDialog.Builder builder = new AlertDialog.Builder(params.getActivity());
        builder.setTitle(params.getTitulo());
        builder.setMessage(params.getMensaje());
        // Acción para el botón de "Aceptar"

        builder.setPositiveButton(params.getAceptar(),aceptar);
        // Acción para el botón de "Cancelar"
        builder.setNegativeButton(params.getCancelar(),cancelar);

        return builder.create();

    }




    /**
     * Operación que crea un AlertDialog de Android para mostrar únicamente un mensaje de advertencia al usuario.
     * Sólo muestra un botón de [Aceptar] al cual no se le puede asociar ningún listener
     *
     * @param params Parámetros del AlertDialog que contiene el activity, título, mensaje
     * @return AlertDialog
     */
    public static AlertDialog crearDialogoAlertaAdvertencia(ParamsAlertDialogVO params) {

        AlertDialog.Builder builder = new AlertDialog.Builder(params.getActivity());
        builder.setTitle(params.getTitulo());
        builder.setMessage(params.getMensaje());
        // Acción para el botón de "Aceptar"
        builder.setPositiveButton(params.getAceptar(),new BtnAceptarCancelarDialogGenerico());

        return builder.create();
    }



    /**
     * Operación que crea un AlertDialog de Android simple con un determinado mensaje
     * @param activity: Actividad padre sobre la que se mostrará el AlertDialog
     * @param titulo: Título del activity
     * @param mensaje: Mensaje a mostrar al usuario
     * @param aceptar: Clase que implementa la interfaz DialogInterface.OnClickListener para la acción asociada
     *                 al botón de Aceptar
     * @return AlertDialog
     */
    public static AlertDialog crearDialogoAlertaSimple(ParamsAlertDialogVO params,final Activity activity, String titulo, String mensaje, DialogInterface.OnClickListener aceptar) {

        AlertDialog.Builder builder = new AlertDialog.Builder(params.getActivity());
        builder.setTitle(params.getTitulo());
        builder.setMessage(params.getMensaje());
        // Acción para el botón de "Aceptar"
        builder.setPositiveButton(params.getAceptar(),aceptar);

        return builder.create();

    }



}

