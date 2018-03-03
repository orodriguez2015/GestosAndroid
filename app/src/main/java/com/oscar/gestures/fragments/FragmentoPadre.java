package com.oscar.gestures.fragments;

import android.support.v4.app.Fragment;

import com.oscar.gestures.OnActualizarFragmentoListener;

/**
 * Clase padre que hereda de Fragment
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */

public class FragmentoPadre extends Fragment {

    protected OnActualizarFragmentoListener listener = null;

    public void setOnActualizarFragmentoListener(OnActualizarFragmentoListener listener) {
        this.listener = listener;
    }

}
