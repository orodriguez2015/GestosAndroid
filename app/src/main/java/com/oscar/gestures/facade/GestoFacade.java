package com.oscar.gestures.facade;

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
    public boolean deleteGestos(List<Gesto> gestos) throws FileException,DatabaseException {
        boolean exito = false;


        return exito;
    }

}
