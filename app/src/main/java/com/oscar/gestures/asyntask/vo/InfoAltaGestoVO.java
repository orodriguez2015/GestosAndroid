package com.oscar.gestures.asyntask.vo;

import android.gesture.Gesture;

import com.oscar.gestures.configuracion.fichero.FicheroGestos;
import com.oscar.gestures.vo.Gesto;

/**
 * Clase InfoAltaGestoVO
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class InfoAltaGestoVO {

    private Gesto gesto;
    private FicheroGestos ficheroGestos;
    private Gesture gesture;

    public InfoAltaGestoVO(Gesto gesto, FicheroGestos ficheroGestos, Gesture gesture) {
        this.gesto = gesto;
        this.ficheroGestos = ficheroGestos;
        this.gesture = gesture;
    }

    public Gesto getGesto() {
        return gesto;
    }

    public void setGesto(Gesto gesto) {
        this.gesto = gesto;
    }

    public FicheroGestos getFicheroGestos() {
        return ficheroGestos;
    }

    public void setFicheroGestos(FicheroGestos ficheroGestos) {
        this.ficheroGestos = ficheroGestos;
    }

    /**
     * Devuelve el Gesture
     * @return Gesture
     */
    public Gesture getGesture() {
        return gesture;
    }

    /**
     * Establece el Gesture
     * @return Gesture
     */
    public void setGesture(Gesture gesture) {
        this.gesture = gesture;
    }

}
