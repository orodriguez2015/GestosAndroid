package com.oscar.gestures.adapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oscar.gestures.R;
import com.oscar.gestures.vo.Gesto;

import java.util.List;

/**
 * Clase GestosAdapter que se usa como adapter para mostrar los gestos almacenados en el archivo de gestos
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class GestosAdapter extends RecyclerView.Adapter<GestosAdapter.GestoViewHolder> implements View.OnClickListener {

    private List<Gesto> items = null;
    private String origen = null;

    private Resources resources = null;
    private View.OnClickListener listener = null;

    /**
     * Constructor
     * @param items: List<Noticia>
     */
    public GestosAdapter(List<Gesto> items, String origen, Resources resources) {
        this.items  = items;
        this.origen = origen;
        this.resources   = resources;
    }

    /**
     * Devuelve la colección de gestos que se muestran en el adapter
     * @return List<Gesto>
     */
    public List<Gesto> getGestos() {
        return this.items;
    }

    /**
     * Establece una colección de gestos
     * @param gestos Colección de gestos
     */
    public void setGestos(List<Gesto> gestos) {
        this.items = gestos;
    }


    /**
     * Devuelve el número de noticias que se muestran
     * @return int
     */
    @Override
    public int getItemCount() {
        return items.size();
    }


    /**
     * Añade una gesto
     * @param gesto Gesto
     */
    public void addItem(Gesto gesto) {

    }

    /**
     * Elimina un elemento del adapter de noticia
     * @param pos Posición del elemento a borrar
     */
    public void removeItem(int pos) {
        getGestos().remove(pos);
    }


    /**
     * Devuelve una determinada noticia
     * @param pos int que indica una posición válida de la colección de noticias
     * @return Noticia
     */
    public Gesto getGesto(int pos) {
        Gesto noticia = null;
        if(pos>=0 && pos<getGestos().size()) {
            noticia = getGestos().get(pos);
        }
        return noticia;
    }


    /**
     * Clase NoticiaViewHolder que contiene los componentes que forman
     * parte de la vista a renderizar para cada componente
     *
     */
    public static class GestoViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView descripcion;
        public TextView fechaPublicacion;
        public TextView origen;


        /**
         * Constructor
         * @param v: View
         */
        public GestoViewHolder(View v) {
            super(v);
            descripcion      = (TextView) v.findViewById(R.id.descripcionGesto);
        }
    }


    /**
     * Este método onCreateViewHolder
     * @param viewGroup
     * @param i
     * @return
     */
    @Override
    public GestoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        /** Se carga el layout noticia.xml para mostrar la información de cada noticia **/
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_gestos, viewGroup, false);
        v.setOnClickListener(this);
        return new GestoViewHolder(v);
    }

    /**
     * onBindViewHolder
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(GestoViewHolder viewHolder, int i) {
        viewHolder.descripcion.setText(items.get(i).getDescripcion());
    }


    /**
     * Establecer el listener de tipo OnClickListener
     * @param listener
     */
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    /**
     * Cuando el usuario ejecuta el método onClick sobre la vista
     * @param view: View
     */
    public void onClick(View view) {
        if(listener != null) {
            listener.onClick(view);
        }
    }
}