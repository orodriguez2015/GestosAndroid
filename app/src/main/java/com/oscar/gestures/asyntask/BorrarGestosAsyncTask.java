package com.oscar.gestures.asyntask;

import android.os.AsyncTask;

import com.oscar.asyntask.ParametrosAsyncTask;
import com.oscar.asyntask.RespuestaAsyncTask;
import com.oscar.gestures.asyntask.vo.BorrarGestoVO;

/**
 * Tarea asincrona para borrar gestos de la base de datos y del fichero de gestos
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class BorrarGestosAsyncTask extends AsyncTask<ParametrosAsyncTask<BorrarGestoVO>,Void,RespuestaAsyncTask> {

    @Override
    protected RespuestaAsyncTask doInBackground(ParametrosAsyncTask<BorrarGestoVO>... parametrosAsyncTasks) {
        return null;
    }
}
