package com.oscar.gestures.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.oscar.gestures.ActividadNuevoGesto;
import com.oscar.gestures.R;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.StringUtils;
import com.oscar.utilities.logcat.LogCat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoFormularioNuevoGesto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoFormularioNuevoGesto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoFormularioNuevoGesto extends FragmentoPadre {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText txtNombre;
    private EditText txtDescripcion;
    private Button botonSiguiente;


    private OnFragmentInteractionListener mListener;


    /**
     * Constructor. Tiene que estar vacio para los fragmentos
     */
    public FragmentoFormularioNuevoGesto() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoFormularioNuevoGesto.
     */
    public static FragmentoFormularioNuevoGesto newInstance(String param1, String param2) {
        FragmentoFormularioNuevoGesto fragment = new FragmentoFormularioNuevoGesto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    /**
     * Método desde el que se recuperan los elementos de la interfaz de usuario.
     * Es necesario sobreescribirlo
     * @param view View
     * @param savedInstanceState Bundle
     */
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LogCat.info(ConstantsGestures.TAG," =====> FragmentoFormularioNuevoGesto.onViewCreated view: " + view);

        this.txtNombre = (EditText)getActivity().findViewById(R.id.txtNombreGesto);
        this.txtDescripcion = (EditText) getActivity().findViewById(R.id.txtAplicacionGesto);
        this.botonSiguiente = (Button)getActivity().findViewById(R.id.btnSiguiente);

        /*
         * Hint para los campos de tipo EditText
         */
        txtNombre.setHint(getString(R.string.nombre_del_gesto));
        txtDescripcion.setHint(R.string.nombre_aplicacion);


        this.txtNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(v==txtNombre && !hasFocus) {
                    //TelephoneUtil.hideKeyboard(getActivity());
                    hideKeyboard();
                }
            }
        });


        this.txtDescripcion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(v==txtDescripcion && !hasFocus) {
                    //TelephoneUtil.hideKeyboard(getActivity());
                    hideKeyboard();
                }
            }
        });


        /*
         * Listener para el botón Siguiente
         */
        this.botonSiguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hideKeyboard();
                String nombre = txtNombre.getText().toString();
                String descripcion = txtDescripcion.getText().toString();

                View focus = null;
                if(StringUtils.isEmpty(nombre)) {
                    txtNombre.setError(getString(R.string.error_gesto_obligatorio));
                    focus = txtNombre;
                } else
                if(StringUtils.isEmpty(descripcion)) {
                    txtDescripcion.setError(getString(R.string.error_app_obligatorio));
                    focus = txtDescripcion;
                }

                if(focus!=null) {
                    focus.setFocusable(true);
                }else {
                    Gesto gesto = new Gesto();
                    gesto.setDescripcion(txtNombre.getText().toString());
                    gesto.setAplicacion(txtDescripcion.getText().toString());

                    abrirActividad(gesto);
                }

            }// onClick

        });

    }


    /**
     * Abre la actividad en la que se detecta el gesto, y se le pasa el gesto en el Intent
     * @param gesto Gesto
     */
    private void abrirActividad(Gesto gesto) {
        Intent intent = new Intent(getActivity(),ActividadNuevoGesto.class);
        if(gesto!=null) {
            intent.putExtra(ConstantsGestures.PARAMETRO_GESTO_INTENT,gesto);
        }
        startActivity(intent);
    }



    /**
     * Abre el fragmento a partir del cual se puede detectar un gesto para almacenarlo en
     * la librería de gestos
     *
    private void abrirFragmentoDeteccionGesto() {

        Fragment fragment2=new FragmentoDeteccionGesto();
        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.container,fragment2,"tag");
        fragmentTransaction.replace(R.id.content,fragment2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
    */


    /**
     * Oculta el teclado
     */
    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nuevo_gesto, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
