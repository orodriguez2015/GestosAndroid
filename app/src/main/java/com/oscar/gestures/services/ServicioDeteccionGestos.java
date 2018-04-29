package com.oscar.gestures.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


/**
 * Servicio que se encargará de detectar los gestos y lanzar la aplicación correspondiente al mismo,
 * de entre las grabadas con anterioridad por el usuario
 */
public class ServicioDeteccionGestos extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
