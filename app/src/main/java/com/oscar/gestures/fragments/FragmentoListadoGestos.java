package com.oscar.gestures.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscar.gestures.R;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.TelephoneUtil;
import com.oscar.utilities.logcat.LogCat;

import java.util.List;

/**
 * Fragmento que representa un listado de gestos
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class FragmentoListadoGestos extends FragmentoPadre {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private static OnCheckRecyclerViewSelectionListener onCheckSelectionListener;
    private List<Gesto> gestos = null;

    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentoListadoGestos() {

    }

    public static FragmentoListadoGestos newInstance(int columnCount,List<Gesto> gestos,OnCheckRecyclerViewSelectionListener listener) {
        FragmentoListadoGestos fragment = new FragmentoListadoGestos();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        fragment.setGestos(gestos);
        onCheckSelectionListener = listener;
        return fragment;
    }


    /**
     * Establece la colección de gestos que se mostrarán en el RecyclerView y que se pasan al adapter
     * @param gestos
     */
    public void setGestos(List<Gesto> gestos) {
        this.gestos = gestos;
    }


    /**
     * Devuelve la lista de gestos seleccionados
     * @return List<Gesto>
     */
    public List<Gesto> getGestosSeleccionados() {
        GestoRecyclerViewAdapter adapter = (GestoRecyclerViewAdapter)this.recyclerView.getAdapter();
        return adapter.getSeleccionados();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        /*
             * Se oculta el teclado si está visible
             */
        TelephoneUtil.hideKeyboard(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        LogCat.info(ConstantsGestures.TAG," =====> FragmentoListadoGestos.onCreateView view: " + view);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            this.recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                this.recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            this.recyclerView.setAdapter(new GestoRecyclerViewAdapter(gestos, mListener,onCheckSelectionListener));
        }
        return view;
    }


    /**
     * Devuelve el adapter
     * @return
     */
    public GestoRecyclerViewAdapter getAdapter() {
        GestoRecyclerViewAdapter adapter = null;

        if(recyclerView!=null) {
            adapter = (GestoRecyclerViewAdapter)recyclerView.getAdapter();
        }
        return adapter;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LogCat.info(ConstantsGestures.TAG," =====> FragmentoListadoGestos.onViewCreated view: " + view);
        super.onViewCreated(view, savedInstanceState);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Gesto item);
    }
}
