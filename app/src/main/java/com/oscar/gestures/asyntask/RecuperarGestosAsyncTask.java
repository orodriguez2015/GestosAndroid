package com.oscar.gestures.asyntask;

import android.content.Context;
import android.os.AsyncTask;

import com.oscar.asyntask.ParametrosAsyncTask;
import com.oscar.asyntask.RespuestaAsyncTask;
import com.oscar.gestures.db.GestoHelper;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.exception.DatabaseException;

import java.util.List;

/**
 * Tarea asíncrona a través de la cual se puede grabar la información de un gesto en base de datos
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class RecuperarGestosAsyncTask extends AsyncTask<ParametrosAsyncTask,Void,RespuestaAsyncTask> {

    /**
     * Se ejecuta la tarea asíncrona en segundo plano
     * @param params Objeto con los parámetros necesarios que necesita la tarea para poder grabar el origen de datos
     * @return RespuestaAsyncTask
     */
    protected RespuestaAsyncTask doInBackground(ParametrosAsyncTask... params){
        RespuestaAsyncTask respuesta = null;
        Context context = params[0].getContext();

        if(context!=null) {
            GestoHelper helper = new GestoHelper(context);

            try {
                List<Gesto> gestos = helper.getGestos();
                respuesta = new RespuestaAsyncTask(0, "OK");
                respuesta.setItems(gestos);

            } catch (DatabaseException e) {
                e.printStackTrace();
                respuesta = new RespuestaAsyncTask(e.getStatus(), e.getMessage());
            }
        }

        return respuesta;
    }
}
