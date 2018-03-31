package com.oscar.gestures.asyntask.vo;

import android.gesture.Gesture;

import com.oscar.gestures.configuracion.fichero.FicheroGestos;
import com.oscar.gestures.vo.Gesto;

public class BorrarGestoVO extends InfoAltaGestoVO {

    public BorrarGestoVO(Gesto gesto, FicheroGestos ficheroGestos, Gesture gesture) {
        super(gesto, ficheroGestos, gesture);
    }
}
