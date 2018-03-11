package com.oscar.gestures.asyntask;

import android.content.Context;
import android.gesture.Gesture;
import android.os.AsyncTask;

import com.oscar.asyntask.ParametrosAsyncTask;
import com.oscar.asyntask.RespuestaAsyncTask;
import com.oscar.gestures.asyntask.vo.InfoAltaGestoVO;
import com.oscar.gestures.configuracion.fichero.FicheroGestos;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.db.GestoHelper;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.exception.DatabaseException;
import com.oscar.utilities.exception.GestoException;

/**
 * Tarea asíncrona a través de la cual se puede grabar la información de un gesto en base de datos
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class NuevoGestoAsyncTask extends AsyncTask<ParametrosAsyncTask<InfoAltaGestoVO>,Void,RespuestaAsyncTask> {



    /**
     * Se ejecuta la tarea asíncrona en segundo plano
     * @param params Objeto con los parámetros necesarios que necesita la tarea para poder grabar el origen de datos
     * @return RespuestaAsyncTask
     */
    protected RespuestaAsyncTask doInBackground(ParametrosAsyncTask<InfoAltaGestoVO>... params){
        RespuestaAsyncTask respuesta = null;

        Context context = params[0].getContext();
        InfoAltaGestoVO aux = params[0].getParametro();


        if(context!=null && aux!=null && aux.getGesto()!=null && aux.getFicheroGestos()!=null && aux.getGesture()!=null) {

            GestoHelper helper = new GestoHelper(context);

            Gesto gesto = aux.getGesto();
            Gesture gesture = aux.getGesture();
            FicheroGestos ficheroGestos = aux.getFicheroGestos();

            try {
                /*
                 * Se almacena el gesto en el fichero de gestos
                 */
                ficheroGestos.almacenarGesto(gesture,gesto.getNombre());

                /**
                 * Se almacena el detalle del gesto en base de datos
                 */
                helper.saveGesto(aux.getGesto());

                respuesta = new RespuestaAsyncTask(0, "OK");

            } catch(GestoException e) {
                e.printStackTrace();
                respuesta = new RespuestaAsyncTask(ConstantsGestures.ERROR_ALMACENAR_GESTURE_FICHERO, e.getMessage());
            }
            catch (DatabaseException e) {
                e.printStackTrace();
                respuesta = new RespuestaAsyncTask(e.getStatus(), e.getMessage());

                try {
                    ficheroGestos.borrarGesto(gesture, gesto.getNombre());

                }catch(GestoException f) {
                    e.printStackTrace();
                }
            }
        }

        return respuesta;
    }
}