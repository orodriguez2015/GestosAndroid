package com.oscar.gestures.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.exception.DatabaseException;
import com.oscar.utilities.logcat.LogCat;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase con operaciones de manejo de la base de datos SQLLite de la aplicación
 *
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */

public class GestoHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DeteccionGestos.db";


    /**
     * Constructor
     * @param context: Objeto de la clase Context
     */
    public GestoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Método onCreate que se encarga de crear la base de datos
     * @param sqLiteDatabase: Manejador de la base de datos
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

        try {
            LogCat.info(ConstantsGestures.TAG,"onCreate init()");

            // Se crea la tabla Gesto
            sqLiteDatabase.execSQL("CREATE TABLE " + GestoContract.GestoEntry.TABLE_NAME + " ("
                    + GestoContract.GestoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + GestoContract.GestoEntry.NOMBRE_GESTO + " TEXT,"
                    + GestoContract.GestoEntry.LOGO_APLICACION_GESTO + " BLOB,"
                    + GestoContract.GestoEntry.APLICACION_GESTO + " TEXT)");


            LogCat.info(ConstantsGestures.TAG,"onCreate end()");
        }catch(Exception e) {
            e.printStackTrace();
            LogCat.error(ConstantsGestures.TAG,"Se ha producido un error al crear la BD en el método onCreate: ".concat(e.getMessage()));
        }
    }


    /**
     * Método onUpgrade que se encarga de actualizar la base de datos
     * @param sqLiteDatabase: Manejador de la base de datos
     * @param i: Versión antigua de la base de datos
     * @param i1: Nueva versión de la base de datos
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        LogCat.debug(ConstantsGestures.TAG,"onUpgrade init");

        LogCat.debug(ConstantsGestures.TAG,"onUpgrade end");
    }


    /**
     * Graba una gesto en la base de datos
     * @param gesto Gesto
     */
    public void saveGesto(Gesto gesto) throws DatabaseException {
        SQLiteDatabase db = getWritableDatabase();

        try {
            LogCat.info(ConstantsGestures.TAG,"saveGesto init");
            Long id = db.insert(GestoContract.GestoEntry.TABLE_NAME, null,ModelConversorUtil.toContentValues(gesto));
            gesto.setId(gesto.getId());
            LogCat.info(ConstantsGestures.TAG,"saveGesto end");


        } catch(Exception e) {
            e.printStackTrace();
            throw new DatabaseException(GestoContract.ERROR_INSERTAR_GESTO,"Error al grabar el gesto en la base de datos: ".concat(e.getMessage()));
        } finally {
            if(db!=null) {
                db.close();
            }
        }
    }


    /**
     * Graba una gesto en la base de datos
     * @param gesto Gesto
     */
    public void deleteGesto(Gesto gesto) throws DatabaseException {
        SQLiteDatabase db = getWritableDatabase();

        try {
            LogCat.info(ConstantsGestures.TAG,"deleteGesto init");
            db.delete(GestoContract.GestoEntry.TABLE_NAME, GestoContract.GestoEntry._ID + "=" + gesto.getId().intValue() ,null);
            LogCat.info(ConstantsGestures.TAG,"deleteGesto end");

        } catch(Exception e) {
            e.printStackTrace();
            throw new DatabaseException(GestoContract.ERROR_INSERTAR_GESTO,"Error al grabar el gesto en la base de datos: ".concat(e.getMessage()));
        } finally {
            if(db!=null) {
                db.close();
            }
        }
    }


    /**
     * Recupera las noticias de la base de datos
     * @return List<Noticia>
     * @throws SQLiteException
     */
    public List<Gesto> getGestos() throws DatabaseException {
        List<Gesto> gestos = new ArrayList<Gesto>();
        SQLiteDatabase db = null;
        Cursor rs = null;

        try {
            LogCat.info(ConstantsGestures.TAG,"getNoticias() init");


            String sql = "select _id,nombre,aplicacion from gesto order by nombre asc";
            LogCat.info(ConstantsGestures.TAG,"sql: " + sql);

            db = getReadableDatabase();
            rs = db.rawQuery(sql,null);

            if(rs!=null && rs.getCount()>0 && rs.moveToFirst()) {
                LogCat.info(ConstantsGestures.TAG,"Numero noticias recuperadas: " + rs.getCount());

                do {
                    Gesto gesto = new Gesto();
                    gesto.setId(rs.getLong(0));
                    gesto.setNombre(rs.getString(1));
                    gesto.setAplicacion(rs.getString(2));
                    //gesto.setLogoAplicacion(rs.getBlob(2));
                    gestos.add(gesto);

                } while(rs.moveToNext());

            } else
                LogCat.debug(ConstantsGestures.TAG,"No existen gestos en la base de datos");

            LogCat.info(ConstantsGestures.TAG,"getGestos() end");

        } catch(Exception e) {
            e.printStackTrace();
            throw new DatabaseException(GestoContract.ERROR_RECUPERAR_GESTOS,"Error al recuperar los gestos de BBDD: " + e.getMessage());
        } finally {
            if(rs!=null) rs.close();
            if(db!=null) db.close();
        }

        return gestos;
    }



}
