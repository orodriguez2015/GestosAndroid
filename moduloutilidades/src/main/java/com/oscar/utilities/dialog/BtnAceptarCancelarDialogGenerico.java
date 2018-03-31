package com.oscar.utilities.dialog;

import android.content.DialogInterface;

import com.oscar.utilities.constantes.Constantes;
import com.oscar.utilities.logcat.LogCat;

/**
 * Clase BtnAceptarDialogGenerico que se asocia a la acción de hacer click sobre un botón de un AlertDialog
 * @author oscar
 */
public class BtnAceptarCancelarDialogGenerico implements DialogInterface.OnClickListener {

    /**
     * onClick
     * @param var1: DialogInterface
     * @param var2:i nt
     */
    public void onClick(DialogInterface var1, int var2) {
        LogCat.debug(Constantes.TAG_UTILITIES,"Ha pulsado el botón Aceptar");
    }

}
