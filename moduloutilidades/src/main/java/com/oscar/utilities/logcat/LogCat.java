package com.oscar.utilities.logcat;

import android.util.Log;

/**
 * Clase LogCat con operaciones que permitan mostrar mensajes de log
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class LogCat {

    /**
     * Muestra mensajes de tipo debug
     * @param TAG String
     * @param message: String
     */
    public static void debug(String TAG,String message){
        Log.d(TAG,message);
    }

    /**
     * Muestra mensajes de tipo error
     * @param TAG String
     * @param message: String
     */
    public static void error(String TAG,String message){
        Log.e(TAG,message);
    }

    /**
     * Muestra mensajes de tipo info
     * @param TAG String
     * @param message: String
     */
    public static void info(String TAG,String message){
        Log.i(TAG,message);
    }
}
