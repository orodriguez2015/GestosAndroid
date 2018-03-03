package com.oscar.gestures;

import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.logcat.LogCat;

public class ActividadNuevoGesto extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener  {

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
        Gesto gesto = (Gesto)params.getSerializable(ConstantsGestures.PARAMETRO_GESTO_INTENT);
        LogCat.info(ConstantsGestures.TAG,"gesto.nombre: " + gesto.getDescripcion());
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
    }

}
