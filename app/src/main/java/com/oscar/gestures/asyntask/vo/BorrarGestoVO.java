package com.oscar.gestures.asyntask.vo;

import com.oscar.gestures.vo.Gesto;

import java.util.List;

/**
 * Clase con la información necesaria para borrar uno o varios gestos de la base de datos y de
 * la librería de gestos
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class BorrarGestoVO {

    private List<Gesto> gestos;

    /**
     * Constructor
     * @param gestos List<Gesto>
     */
    public BorrarGestoVO(List<Gesto> gestos) {
        this.gestos = gestos;
    }

    /**
     * Devuelve la lista de gestos a eliminar
     * @return List<Gesto>
     */
    public List<Gesto> getGestos() {
        return gestos;
    }


    /**
     * Establece la lista de gestos a eliminar
     * @param gestos List<Gesto>
     */
    public void setGestos(List<Gesto> gestos) {
        this.gestos = gestos;
    }
}
