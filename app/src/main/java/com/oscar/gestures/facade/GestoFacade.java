package com.oscar.gestures.facade;

import android.content.Context;

import com.oscar.gestures.db.GestoHelper;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.exception.DatabaseException;
import com.oscar.utilities.exception.FileException;

import java.util.List;

/**
 * Clase con operaciones de manejo de gestos
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class GestoFacade {

    private static GestoFacade instance;

    /**
     * Constructor
     */
    private GestoFacade() {

    }

    /**
     * Devuelve la única instancia de esta clase
     * @return GestoFacade
     */
    public static GestoFacade getInstance() {
        if(instance==null) {
            instance = new GestoFacade();
        }
        return instance;
    }

    /**
     * Elimina de la base de datos los gestos
     * @param gestos
     * @return
     * @throws FileException si ocurre algún error a eliminar los gestos del ficheros de gestos
     * @throws DatabaseException si ocurre algún error al eliminar los gestos de base de datos
     */
    public boolean deleteGestos(Context context,List<Gesto> gestos) throws FileException,DatabaseException {
        boolean exito = false;

        GestoHelper helper = new GestoHelper(context);

        try {
            for (int i = 0; gestos != null && i < gestos.size(); i++) {
                helper.deleteGesto(gestos.get(i));
            }


        }catch(DatabaseException e) {

        }

        return exito;
    }


    /**
     * Recupera los gestos de la base de datos
     * @param context Context
     * @return List<Gesto>
     * @throws DatabaseException
     */
    public List<Gesto> getGestos(Context context) throws DatabaseException {
        GestoHelper helper = new GestoHelper(context);
        return helper.getGestos();
    }

}
