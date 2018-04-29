package com.oscar.gestures.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.oscar.gestures.R;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.fragments.FragmentoListadoGestos.OnListFragmentInteractionListener;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.ImageUtils;
import com.oscar.utilities.logcat.LogCat;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Gesto} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class GestoRecyclerViewAdapter extends RecyclerView.Adapter<GestoRecyclerViewAdapter.ViewHolder> {

    private List<Gesto> items;
    private List<Gesto> seleccionados;
    private final OnListFragmentInteractionListener mListener;
    private OnCheckRecyclerViewSelectionListener onCheckSelectionListener = null;

    /**
     * Constructor
     * @param items List<Gesto></Gesto>
     * @param listener OnListFragmentInteractionListener
     * @param onCheckSelectionListener OnCheckSelectionListener
     */
    public GestoRecyclerViewAdapter(List<Gesto> items, OnListFragmentInteractionListener listener,OnCheckRecyclerViewSelectionListener onCheckSelectionListener) {
        this.items = items;
        this.mListener = listener;
        this.onCheckSelectionListener = onCheckSelectionListener;
        seleccionados = new ArrayList<Gesto>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Establece la items
     * @param items List<Gesto>
     */
    public void setItems(List<Gesto> items) {
        this.items = items;
    }


    /**
     * Devuelve la lista de Gestos seleccionados por el usuario
     * @return List<Gesto>
     */
    public List<Gesto> getSeleccionados() {
        return seleccionados;
    }


    /**
     * Establece la lista de Gestos seleccionados por el usuario
     * @param seleccionados List<Gesto>
     */
    public void setSeleccionados(List<Gesto> seleccionados) {
        this.seleccionados = seleccionados;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.item = items.get(position);
        holder.txtNombreGesto.setText(items.get(position).getNombre());
        holder.txtNombreAplicacion.setText(items.get(position).getAplicacion());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.item);
                }
            }
        });


        holder.checkboxGesto.setChecked(false);

        /*
         * Evento de detección de selección del check de un item
         */
        holder.checkboxGesto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    seleccionados.add(items.get(position));

                } else {
                    seleccionados.remove(items.get(position));
                }

                if(onCheckSelectionListener!=null) {
                    onCheckSelectionListener.setOnCheckSelectionListener(getSeleccionados().size());
                }
            }
        });

        LogCat.info(ConstantsGestures.TAG,"holder.item.getId(): " + holder.item.getId());
        LogCat.info(ConstantsGestures.TAG,"holder.item.getNombre(): " + holder.item.getNombre());
        LogCat.info(ConstantsGestures.TAG,"holder.item.getAplicacion(): " + holder.item.getAplicacion());

        LogCat.info(ConstantsGestures.TAG,"holder.item.getLogoApliczion: " + holder.item.getLogoAplicacion());

        LogCat.info(ConstantsGestures.TAG,"holder.item.getLogoApliczion.tostring: " +holder.item.getLogoAplicacion().toString());
        if(holder.item.getLogoAplicacion()!=null) {

            holder.imageViewLogoAplicacion.setImageDrawable(ImageUtils.convert(holder.item.getLogoAplicacion()));
            holder.imageViewLogoAplicacion.refreshDrawableState();

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtNombreGesto;
        public final TextView txtNombreAplicacion;
        public Gesto item;
        public CheckBox checkboxGesto;
        public ImageView imageViewLogoAplicacion;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtNombreGesto = (TextView) view.findViewById(R.id.txtNombreGesto);
            txtNombreAplicacion = (TextView) view.findViewById(R.id.txtNombreAplicacion);
            checkboxGesto = (CheckBox)view.findViewById(R.id.gestoCheckbox);
            imageViewLogoAplicacion = (ImageView)view.findViewById(R.id.gestoLogoAplicacion);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtNombreAplicacion.getText() + "'";
        }
    }



}
