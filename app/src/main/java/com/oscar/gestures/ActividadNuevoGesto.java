package com.oscar.gestures;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.oscar.asyntask.ParametrosAsyncTask;
import com.oscar.asyntask.RespuestaAsyncTask;
import com.oscar.gestures.asyntask.NuevoGestoAsyncTask;
import com.oscar.gestures.asyntask.vo.InfoAltaGestoVO;
import com.oscar.gestures.configuracion.fichero.FicheroGestos;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.MessageUtils;
import com.oscar.utilities.logcat.LogCat;


/**
 * Esta actividad se utiliza para que el usuario dibuje el gesto. Una vez hecho se almacenerá en el fichero
 * de gestos, y se cierra la actividad.
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class ActividadNuevoGesto extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener  {

    private Gesto gesto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_nuevo_gesto);
        setTitle(getString(R.string.nuevo_gesto));

        /*
         * Se activa la flecha en la barra de estado para poder volver hacia la actividad inmediatamente anterior
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*
         * Se recupera la vista GestureOverlayView y se asigna el listener para la detección de gestos
         */
        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);


        /*
         * Se recupera el objeto de la clase Gesto pasado en el Intent
         */
        Bundle params = getIntent().getExtras();
        gesto = (Gesto)params.getSerializable(ConstantsGestures.PARAMETRO_GESTO_INTENT);
        if(gesto!=null) {
            LogCat.info(ConstantsGestures.TAG, "gesto.nombre: " + gesto.getNombre());
        }
    }

    /**
     * Se sobreescribe este método para permitir ir hacia atrás al pulsar la fecha que se encuentra en la
     * barra de estado
     * @return Boolean
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


    /**
     * Cuando el usuario finaliza el gesto, se captura en este método
     * @param overlay
     * @param gesture
     */
    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        LogCat.info(ConstantsGestures.TAG," onGesturePerformed ===>");

        if(gesture!=null) {

            try {
                LogCat.info(ConstantsGestures.TAG,"Grabando el gesto del id: " + gesture.getID());
                this.gesto.setId(gesture.getID());

                FicheroGestos ficheroGestos = FicheroGestos.getInstance(getFilesDir());

                InfoAltaGestoVO alta = new InfoAltaGestoVO(this.gesto,ficheroGestos,gesture);

                // Se ejecuta la tarea asíncrona
                ParametrosAsyncTask<InfoAltaGestoVO> paramAsyncTask = new ParametrosAsyncTask<InfoAltaGestoVO>(alta);
                paramAsyncTask.setContext(getApplicationContext());
                NuevoGestoAsyncTask task = new NuevoGestoAsyncTask();

                task.execute(paramAsyncTask);
                RespuestaAsyncTask respuesta = task.get();
                if(respuesta!=null && respuesta.getStatus().equals(0)) {
                    setResult(Activity.RESULT_OK);
                    finish();
                }

            }catch(Exception e) {
                e.printStackTrace();
                MessageUtils.showToastDuracionCorta(this,getString(R.string.error_grabar_gesto));
            }

        }
    }

}
