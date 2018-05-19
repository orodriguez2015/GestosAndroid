package com.oscar.gestures;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.oscar.gestures.configuracion.fichero.FicheroGestos;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.utilities.MessageUtils;
import com.oscar.utilities.exception.FileException;
import com.oscar.utilities.exception.GestoException;
import com.oscar.utilities.logcat.LogCat;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Clase ActividadPrincipal que detectará los gestos para almacenarlos, de modo que se le puedan
 * asociar determinadas acciones
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class ActividadPrincipal extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    private TextView mTextMessage;
    private FicheroGestos ficheroGestos = null;
    private GestureDetectorCompat mDetector = null;
    private GestureLibrary gestureLibrary;
    private final File storeFile = new File(Environment.getExternalStorageDirectory(), "gestures");
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;

                case R.id.navigation_dashboard:

                    Intent intent = new Intent(ActividadPrincipal.this,ActividadEntrada.class);
                    startActivity(intent);
                    return true;

                    /*
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                    */
            }// switch

            return false;
        }

    };

    /**
     * onCreate
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        LogCat.debug(ConstantsGestures.TAG,"onCreate");
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        try {

            ficheroGestos = FicheroGestos.getInstance(getFilesDir());
            String pathFicheroGestos = ficheroGestos.getPathFicheroGestos();

            gestureLibrary = GestureLibraries.fromFile(pathFicheroGestos);
            //gestureLibrary = GestureLibraries.fromRawResource(getBaseContext(),R.raw.gestures);
            if(!gestureLibrary.load()) {
                MessageUtils.showToast(this,"No se ha cargado el archivo de gestos",20);
                LogCat.info(ConstantsGestures.TAG,"No se ha cargado el archivo de gestos");
                //finish();
            } else {

                /*
                this.gestureLibrary.removeEntry("g");
                this.gestureLibrary.removeEntry("H");
                this.gestureLibrary.removeEntry("e");
                this.gestureLibrary.removeEntry("5");
                this.gestureLibrary.removeEntry("q");
                this.gestureLibrary.removeEntry("G");
                this.gestureLibrary.removeEntry("v");
                this.gestureLibrary.removeEntry("a");
                this.gestureLibrary.removeEntry("R");
                this.gestureLibrary.removeEntry("o");
                this.gestureLibrary.removeEntry("6");
                this.gestureLibrary.removeEntry("Z");
                this.gestureLibrary.removeEntry("4");
                this.gestureLibrary.removeEntry("7");
                this.gestureLibrary.removeEntry("T");
                this.gestureLibrary.removeEntry("1");
                this.gestureLibrary.removeEntry("z");
                this.gestureLibrary.removeEntry("x");
                this.gestureLibrary.removeEntry("0");
                this.gestureLibrary.removeEntry("C");
                this.gestureLibrary.removeEntry("clear_search_text");
                this.gestureLibrary.removeEntry("m");
                this.gestureLibrary.removeEntry("F");
                this.gestureLibrary.removeEntry("h");
                this.gestureLibrary.removeEntry("w");
                this.gestureLibrary.removeEntry("i");
                this.gestureLibrary.removeEntry("Q");
                this.gestureLibrary.removeEntry("del_one_char");
                this.gestureLibrary.removeEntry("f");
                this.gestureLibrary.removeEntry("r");
                this.gestureLibrary.removeEntry("B");
                this.gestureLibrary.removeEntry("n");
                this.gestureLibrary.removeEntry("s");
                this.gestureLibrary.removeEntry("2");
                this.gestureLibrary.removeEntry("u");
                this.gestureLibrary.removeEntry("l");
                this.gestureLibrary.removeEntry("8");
                this.gestureLibrary.removeEntry("b");
                this.gestureLibrary.removeEntry("t");
                this.gestureLibrary.removeEntry("p");
                this.gestureLibrary.removeEntry("C");
                this.gestureLibrary.removeEntry("c");
                */

                mostrarGestos(this.gestureLibrary);

            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        /**
         * Se recupera la vista GestureOverlayView y se asigna el listener para la detección de gestos
         */
        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);

    }


    /**
     * Devuelve la ruta completa del fichero en disco, que contendrá los gestos que cree el usuario en la aplicación
     * @return String
     *
    public String getNameGesturesFile() {
        String pathFinal = "";
        File filesDir = this.getFilesDir();
        String path = filesDir.getPath();
        pathFinal = filesDir.getPath() + File.separator + ConstantsGestures.NAME_FILE_GESTURES;
        LogCat.info(ConstantsGestures.TAG,"PAth archivo gestos: " + pathFinal);
        return pathFinal;
    }*/



    public String getNameGesturesFile() throws FileException {
        return FicheroGestos.getInstance(getFilesDir()).getPathFicheroGestos();
    }


    private void mostrarGestos(GestureLibrary library) {
        Set<String> entries = library.getGestureEntries();
        Iterator<String> it = entries.iterator();
        StringBuffer sb = new StringBuffer();

        Map<String,List<Gesture>> gestos = new HashMap<String,List<Gesture>>();

        LogCat.info(ConstantsGestures.TAG,"Fichero gestos solo lectura: " + library.isReadOnly());

        while(it.hasNext()) {
            String nombreGesto = it.next();
            LogCat.info(ConstantsGestures.TAG,nombreGesto + ",");
            sb.append(nombreGesto);
            sb.append("\r\n");

            if(gestos.containsKey(nombreGesto)) {
                gestos.get(nombreGesto).addAll(library.getGestures(nombreGesto));
            } else {
                List<Gesture> gestures = library.getGestures(nombreGesto);
                gestos.put(nombreGesto,gestures);
            }

        }// while

    }


    private String getName(int i) {
        String[] p = new String[] {"1","2","3","4","5","6","7","8","9","10"};

        return p[i];
    }


    /**
     * Graba un gesto en el archivo de gestos
     * @param gesture Gesture
     * @param nombreGesto String
     */
    private void grabarGesto(Gesture gesture,String nombreGesto) {
        try {
            //GestosUtilities.almacenarGesto(gesture, getNameGesturesFile(),nombreGesto);
            ficheroGestos.almacenarGesto(gesture,nombreGesto);

        } catch(GestoException e) {
            MessageUtils.showToastDuracionLarga(this,getString(R.string.error_grabar_gesto));
        }
    }

    /**
     * onGesturePerformed
     * @param overlay GestureOverlayView
     * @param gesture Gesture
     */
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        LogCat.info(ConstantsGestures.TAG,"onGesturePerformed ====>");


        if(gesture!=null) {

            try {
                /**
                 * Se obtiene la ruta en disco del dispositivo en el que almacenar el fichero con los gestos de la aplicación
                 *
                File filesDir = getFilesDir();
                String path = filesDir.getPath();
                String pathFicheroGestos = filesDir.getPath() + File.separator + ConstantsGestures.NAME_FILE_GESTURES;
                LogCat.info(ConstantsGestures.TAG,"path directorio aplicacion: " + path);
                LogCat.info(ConstantsGestures.TAG,"path fichero de gestos: " + pathFicheroGestos);


                /**
                 * Se almacena el contenido  de res/raw/gestures en un fichero de gestos
                 * en un directorio de la aplicación
                 *
                //InputStream is = getAssets().open("/res/raw/gestures");
                InputStream is = this.getResources().openRawResource(R.raw.gestures);

                FileOutputStream fos = new FileOutputStream(pathFicheroGestos);
                FileUtils.writeFile(is,fos);
                */

                Random random = new Random();


                LogCat.info(ConstantsGestures.TAG,"Número aleatorio: " + getName(random.nextInt(10)));



                //GestosUtilities.almacenarGesto(gesture,getNameGesturesFile(),getName(random.nextInt(10)));
                ficheroGestos.almacenarGesto(gesture,getName(random.nextInt(10)));
                LogCat.info(ConstantsGestures.TAG,"Gesto grabado");

                ficheroGestos.mostrarGestos();
               // this.mostrarGestos(gestureLibrary);
                /*
                GestureLibrary gestorLibreriasAuxiliar = GestureLibraries.fromFile(getNameGesturesFile());
                gestorLibreriasAuxiliar.addGesture("camara",gesture);
                gestorLibreriasAuxiliar.save();
                LogCat.info(ConstantsGestures.TAG,"Gesto grabado");
                MessageUtils.showToastDuracionLarga(this,"Gesto grabado en fichero interno de gestos");
                mostrarGestos(gestorLibreriasAuxiliar);
                */



            }catch(Exception e) {
                e.printStackTrace();;
            }

        }


        /**
         * TODO: Comprobar si se puede almacenar el gesto en algun fichero, equivalente a res/raw/gestures,
         * de modo que se pueda comparar los gestos introducidos por el usuario, con los almacenados en el fichero de gestos
         *
        ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
        LogCat.info(ConstantsGestures.TAG,"onGesturePerformed predictions: " + predictions);



        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
            String result = predictions.get(0).name;

            MessageUtils.showToast(this,"Letra introducida:" + result,5);
            if ("open".equalsIgnoreCase(result)) {
                Toast.makeText(this, "Opening the document", Toast.LENGTH_LONG).show();
            } else if ("save".equalsIgnoreCase(result)) {
                Toast.makeText(this, "Saving the document", Toast.LENGTH_LONG).show();
            }
        }
         */

    }


}
