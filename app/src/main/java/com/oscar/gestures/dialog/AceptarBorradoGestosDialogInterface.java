package com.oscar.gestures.dialog;

import android.content.DialogInterface;
import android.support.annotation.NonNull;

import com.oscar.asyntask.ParametrosAsyncTask;
import com.oscar.asyntask.RespuestaAsyncTask;
import com.oscar.gestures.ActividadEntrada;
import com.oscar.gestures.R;
import com.oscar.gestures.asyntask.BorrarGestosAsyncTask;
import com.oscar.gestures.asyntask.vo.BorrarGestoVO;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.dialog.AlertDialogHelper;
import com.oscar.utilities.dialog.ParamsAlertDialogVO;

import java.util.List;


/**
 * Listener asociado a un FloatingButtonAction para eliminar gestos de la base de datos y del fichero de gestos
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class AceptarBorradoGestosDialogInterface implements DialogInterface.OnClickListener {

    private List<Gesto> gestos;
    private ActividadEntrada actividad = null;

    /**
     * Constructor
     * @param actividadEntrada ActividadEntrada
     * @param gestos List<Gesto>
     */
    public AceptarBorradoGestosDialogInterface(@NonNull ActividadEntrada actividadEntrada, @NonNull List<Gesto> gestos) {
        this.gestos = gestos;
        this.actividad = actividadEntrada;
    }


    /**
     * onClick
     * @param dialog DialogInterface
     * @param which int
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {

        try {

            BorrarGestoVO borrar = new BorrarGestoVO(gestos);
            ParametrosAsyncTask<BorrarGestoVO> params = new ParametrosAsyncTask<BorrarGestoVO>(borrar);
            params.setContext(actividad.getBaseContext());

            BorrarGestosAsyncTask task = new BorrarGestosAsyncTask();
            task.execute(params);
            RespuestaAsyncTask respuesta = task.get();

            ParamsAlertDialogVO paramsDialog = null;
            if(respuesta!=null && respuesta.getStatus()==0) {
                paramsDialog = new ParamsAlertDialogVO(this.actividad,"Ha sido eliminado","Atención",null,null);
                this.actividad.recargarListadoGestos();

            } else {
                paramsDialog = new ParamsAlertDialogVO(this.actividad,"Se ha producido un error",actividad.getString(R.string.txt_atencion),null,null);
                AlertDialogHelper.crearDialogoAlertaAdvertencia(paramsDialog).show();

            }



        }catch(Exception e) {
            e.printStackTrace();
            ParamsAlertDialogVO paramsDialog = new ParamsAlertDialogVO(this.actividad,"Error al eliminar: " + e.getMessage(),"Atención",null,null);
            AlertDialogHelper.crearDialogoAlertaAdvertencia(paramsDialog).show();
        }

    }
}
