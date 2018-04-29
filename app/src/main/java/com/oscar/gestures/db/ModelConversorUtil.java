package com.oscar.gestures.db;

import android.content.ContentValues;

import com.oscar.gestures.vo.Gesto;

/**
 * Clase ModelConversorUtil que dispone de operaciones que convierte un objeto VO en
 * uno de tipo ContentValues necesario para persistir el objeto original en la base de datos SQLite
 *
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class ModelConversorUtil {

    /**
     * Convierte un objeto de la clase Noticia en el ContentValues que se usará para
     * persistir la información en la base de datos
     * @param gesto Gesto
     * @return ContentValues
     */
    public static ContentValues toContentValues(Gesto gesto) {

        ContentValues values = new ContentValues();
        values.put(GestoContract.GestoEntry.NOMBRE_GESTO,gesto.getNombre());
        values.put(GestoContract.GestoEntry.APLICACION_GESTO,gesto.getAplicacion());
        values.put(GestoContract.GestoEntry.LOGO_APLICACION_GESTO,gesto.getLogoAplicacion());
        return values;
    }


}

