package com.oscar.gestures.dialog;

import android.content.DialogInterface;

import com.oscar.gestures.facade.GestoFacade;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.exception.DatabaseException;
import com.oscar.utilities.exception.FileException;

import java.util.List;

public class AceptarBorradoGestosDialogInterface implements DialogInterface.OnClickListener {

    private List<Gesto> gestos;

    /**
     * Constructor
     * @param gestos List<Gesto>
     */
    public AceptarBorradoGestosDialogInterface(List<Gesto> gestos) {
        this.gestos = gestos;
    }


    /**
     * onClick
     * @param dialog DialogInterface
     * @param which int
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {

        try {
            GestoFacade.getInstance().deleteGestos(this.gestos);

        }catch(FileException e) {
            e.printStackTrace();
        }
        catch(DatabaseException e) {
            e.printStackTrace();
        }
    }
}
