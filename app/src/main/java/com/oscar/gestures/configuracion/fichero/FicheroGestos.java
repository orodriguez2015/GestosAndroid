package com.oscar.gestures.configuracion.fichero;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.Prediction;

import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.constantes.Constantes;
import com.oscar.utilities.exception.FileException;
import com.oscar.utilities.exception.GestoException;
import com.oscar.utilities.logcat.LogCat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Clase con operaciones relativas al fichero de gestos
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class FicheroGestos {

   private static FicheroGestos instance = null;
   private String pathFicheroGestos = null;
   private boolean ficheroGestosCreado = false;
   private GestureLibrary gestureLibrary = null;

    /**
     * Constructor que crea el fichero de gestos sino existe
     * @throws FileException
     */
   private FicheroGestos(File fileParent) throws FileException {

        if(fileParent==null) {
            // Sino se pasa el fichero en el que está la ruta de instalación de la aplicación, no se puede
            // crear el fichero de gestos
            throw new FileException("Fichero de instalación de la aplicación desconocido. No se puede crear el fichero almacén de gestos");
        }

        if(!ficheroGestosCreado) {
            crearFicheroGestos(fileParent);
        }

       gestureLibrary = GestureLibraries.fromFile(pathFicheroGestos);
       boolean loaded = gestureLibrary.load();
   }

    /**
     * Devuelve la unica instancia existente de esta clase
     * @return FicheroGestos
     * @throws FileException
     */
   public static FicheroGestos getInstance(File fileParent) throws FileException {
       if(instance==null) {
           instance = new FicheroGestos(fileParent);
       }
       return instance;
   }


    /**
     * Devuelve la ruta en el que se encuentra el fichero almacén de gestos
     * @return String
     */
   public String getPathFicheroGestos() {
       return this.pathFicheroGestos;
   }



    /**
     * Crear el fichero de gestos si no existe. Es necesario pasar a la operación el objeto File con la
     * ruta de la aplicación en el disco del dispositivo
     *
     */
    private void crearFicheroGestos(File fileParent) throws FileException {
        try {

            LogCat.info(ConstantsGestures.TAG,"crearFicheroGestos ====>");

            if(!ficheroGestosCreado && fileParent!=null) {
                pathFicheroGestos = fileParent.getPath() + File.separator + ConstantsGestures.NAME_FILE_GESTURES;

                File fichero = new File(pathFicheroGestos);
                if (!fichero.exists()) {
                    fichero.createNewFile();
                }
                ficheroGestosCreado = true;
                LogCat.info(ConstantsGestures.TAG,"crearFicheroGestos ====>");
                LogCat.info(ConstantsGestures.TAG, "PAth archivo gestos: " + pathFicheroGestos);
            }

        }catch(IOException e) {
            pathFicheroGestos = null;
            ficheroGestosCreado = false;
            e.printStackTrace();
            throw new FileException("Se ha producido un error al crear el fichero almacén de gestos",e);
        }
    }


    /**
     * Almacena un gesto realizado por el usuario, en la librería de gestos
     * @param gesture Gesto

     * @param nombreGesto Nombre del gesto dentro del archivo de gestos
     * @throws FileException sino existe el archivo de gestos
     */
    public void almacenarGesto(Gesture gesture,String nombreGesto) throws GestoException {
        try {
            gestureLibrary.addGesture(nombreGesto, gesture);
            gestureLibrary.save();

        }catch(Exception e) {
            throw new GestoException("Error al guardar un gesto",e);
        }
    }



    /**
     * Borra un gesto de la librería de gestos
     * @param gesture Gesto
     * @param nombreGesto Nombre del gesto dentro del archivo de gestos
     * @throws FileException sino existe el archivo de gestos
     */
    public void borrarGesto(Gesture gesture,String nombreGesto) throws GestoException {
        try {
            gestureLibrary.removeGesture(nombreGesto, gesture);
            gestureLibrary.save();

        }catch(Exception e) {
            throw new GestoException("Error al guardar un gesto",e);
        }
    }


    public void comprobarGesto(GestureLibrary library,Gesture gesture) {
        ArrayList<Prediction> predictions = library.recognize(gesture);

        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
            String result = predictions.get(0).name;

            LogCat.info(Constantes.TAG_UTILITIES,"Gesto reconocido: " + result);
        }
    }


    /**
     * Recupera una lista de gestos con el nombre con el
     * @return
     */
    public List<Gesto> getGestos() {
        List<Gesto> gestos = new ArrayList<Gesto>();

        if(gestureLibrary!=null) {
            Set<String> conjunto= gestureLibrary.getGestureEntries();
            Iterator<String> it = conjunto.iterator();

            while(it.hasNext()) {
                Gesto g = new Gesto();
                //g.setDescripcion(it.next());
                g.setNombre(it.next());
                gestos.add(g);
            }
        }

        return gestos;
    }



    /**
     * Muestra a través de LogCat el contenido de un archivo de gestos
     */
    public void mostrarGestos() {

        try {
            if(ficheroGestosCreado && gestureLibrary!=null) {

                Set<String> entries = gestureLibrary.getGestureEntries();
                Iterator<String> it = entries.iterator();

                LogCat.info(ConstantsGestures.TAG, "Fichero gestos solo lectura: " + gestureLibrary.isReadOnly());
                LogCat.info(ConstantsGestures.TAG, "Numero de gestos del fichero: " + entries.size());


                while (it.hasNext()) {
                    String nombreGesto = it.next();
                    LogCat.info(ConstantsGestures.TAG, nombreGesto + ",");

                }// while

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }



}
