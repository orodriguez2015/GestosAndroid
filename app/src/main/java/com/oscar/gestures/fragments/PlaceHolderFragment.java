package com.oscar.gestures.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.oscar.asyntask.ParametrosAsyncTask;
import com.oscar.asyntask.RespuestaAsyncTask;
import com.oscar.gestures.OnActualizarFragmentoListener;
import com.oscar.gestures.asyntask.RecuperarGestosAsyncTask;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.logcat.LogCat;

import java.util.List;

/**
 * Clase PlaceHolderFragment
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class PlaceHolderFragment {

    private Context context = null;
    private OnActualizarFragmentoListener listener = null;


    /**
     * Constructor
     * @param context Context
     * @param listener OnActualizarFragmentoListener
     */
    public PlaceHolderFragment(Context context,OnActualizarFragmentoListener listener) {
        this.context = context;
        this.listener = listener;
    }


    /**
     * Devuelve un determinado Fragment
     * @param posicion int
     * @return Fragment
     */
    public Fragment getFragment(int posicion) {
        FragmentoPadre fragmento = null;

        switch(posicion){
            case 0: {
                // Inicio
                fragmento = newInstance(TipoFragmento.LISTADO_GESTOS);
                fragmento.setOnActualizarFragmentoListener(listener);
                break;
            }

            case 1: {
                // Nuevo gesto
                fragmento = newInstance(TipoFragmento.NUEVO_GESTO);
                fragmento.setOnActualizarFragmentoListener(listener);
                break;
            }

            case 2: {
                // Fragmento vacio
                fragmento = newInstance(TipoFragmento.FRAGMENTO_VACIO);
                fragmento.setOnActualizarFragmentoListener(listener);
                break;
            }
        }// switch

        return fragmento;
    }// getFragment



    /**
     * Devuelve la instancia de un Fragment en función del tipo del mismo indicado
     * por parámetro
     * @param tipo TipoFragmento
     * @return Fragment
     */
    private FragmentoPadre newInstance(TipoFragmento tipo) {
        FragmentoPadre f = null;

        if(tipo.equals(TipoFragmento.NUEVO_GESTO)) {
            return new FragmentoFormularioNuevoGesto();
        } else
        if(tipo.equals(TipoFragmento.LISTADO_GESTOS)) {
            return instanceFragmentoListadoGestos();
        }else
        if(tipo.equals(TipoFragmento.FRAGMENTO_VACIO)) {
            return instanceFragmentoVacio();
        }

        return f;
    }


    /**
     * Devuelve una instancia del Fragment de la clase FragmentoVacio
     * @return FragmentoVacio
     */
    private static FragmentoPadre instanceFragmentoVacio() {
        return new FragmentoVacio();
    }

    /**
     * Devuelve la instancia del fragmento en el que se muestran los listados de gestos
     * @return FragmentoListadoGestos
     */
    private FragmentoListadoGestos instanceFragmentoListadoGestos() {
        FragmentoListadoGestos f = null;

        try {
            LogCat.info(ConstantsGestures.TAG,"instanceFragmentoListadoGestos.init");

            /**
             * Se recupera los gestos almacenados en el fichero de gestos
             */
            //FicheroGestos ficheroGestos = FicheroGestos.getInstance(null);
            //ficheroGestos.mostrarGestos();
            //List<Gesto> gestos = ficheroGestos.getGestos();

            ParametrosAsyncTask<Context> params = new ParametrosAsyncTask<Context>(context);
            RecuperarGestosAsyncTask task = new RecuperarGestosAsyncTask();
            task.execute(params);

            RespuestaAsyncTask respuesta = task.get();

            LogCat.info(ConstantsGestures.TAG,"getGestos respuesta: " + respuesta.getStatus());
            if(respuesta!=null && respuesta.getStatus().equals(0)) {
                List<Gesto> gestos = respuesta.getItems();

                LogCat.info(ConstantsGestures.TAG,"getGestos numero gestos recuperados: " + gestos.size());
                f = FragmentoListadoGestos.newInstance(1, gestos);
                f.setGestos(gestos);
            }

            LogCat.info(ConstantsGestures.TAG, "instanceFragmentoListadoGestos.end");

        }catch(Exception e) {
            e.printStackTrace();
        }

        return f;
    }


    /**
     * Enumerado TipoFragmento que indica el tipo de fragmentos que se pueden crear
     * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
     */
    public static enum TipoFragmento {

        NUEVO_GESTO(1),LISTADO_GESTOS(2),FRAGMENTO_VACIO(3),FRAGMENTO_DETECCION_GESTO(4);

        private int id;


        /**
         * Constructor
         * @param id Identificador el tipo de fragmento
         */
        TipoFragmento(int id) {
            this.id = id;
        }


        /**
         * Devuelve el identificador del tipo de fragmento
         * @return int
         */
        public int getId() {
            return id;
        }

        /**
         * Establece el identificador del tipo de fragmento
         * @param id int
         */
        public void setId(int id) {
            this.id = id;
        }
    }
}
