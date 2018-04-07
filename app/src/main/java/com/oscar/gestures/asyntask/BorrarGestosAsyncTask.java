package com.oscar.gestures.asyntask;

import android.content.Context;
import android.os.AsyncTask;

import com.oscar.asyntask.ParametrosAsyncTask;
import com.oscar.asyntask.RespuestaAsyncTask;
import com.oscar.gestures.asyntask.vo.BorrarGestoVO;
import com.oscar.gestures.facade.GestoFacade;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.exception.DatabaseException;
import com.oscar.utilities.exception.FileException;

import java.util.List;

/**
 * Tarea asincrona para borrar gestos de la base de datos y del fichero de gestos
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class BorrarGestosAsyncTask extends AsyncTask<ParametrosAsyncTask<BorrarGestoVO>,Void,RespuestaAsyncTask> {

    public BorrarGestosAsyncTask() {

    }

    @Override
    protected RespuestaAsyncTask doInBackground(ParametrosAsyncTask<BorrarGestoVO>... params) {
        RespuestaAsyncTask respuesta = null;
        Context context = params[0].getContext();
        List<Gesto> gestos = ((BorrarGestoVO)params[0].getParametro()).getGestos();

        try {
            GestoFacade.getInstance().deleteGestos(context,gestos);
            respuesta = new RespuestaAsyncTask(0, "OK");

        }catch(DatabaseException e) {
            respuesta = new RespuestaAsyncTask(1, "Error al borrar los gestos de la base de datos");

        } catch(FileException e) {

        }

        return respuesta;
    }
}
