package com.oscar.gestures.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.oscar.gestures.ActividadEntrada;
import com.oscar.gestures.ActividadNuevoGesto;
import com.oscar.gestures.R;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.vo.AplicacionVO;
import com.oscar.gestures.vo.Gesto;
import com.oscar.spinner.adapter.ImageSpinnerAdapter;
import com.oscar.utilities.MessageUtils;
import com.oscar.utilities.StringUtils;
import com.oscar.utilities.TelephoneUtil;
import com.oscar.utilities.logcat.LogCat;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoFormularioNuevoGesto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 //* Use the {@link FragmentoFormularioNuevoGesto} factory method to
 * create an instance of this fragment.
 */
public class FragmentoFormularioNuevoGesto extends FragmentoPadre {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText txtNombre;
    private Spinner desplegableAplicacion;
    private Button botonSiguiente;


    private OnFragmentInteractionListener mListener;


    /**
     * Constructor. Tiene que estar vacio para los fragmentos
     */
    public FragmentoFormularioNuevoGesto() {

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
        this.desplegableAplicacion = (Spinner)getActivity().findViewById(R.id.desplegableAplicacion);
        this.botonSiguiente = (Button)getActivity().findViewById(R.id.btnSiguiente);


        /*
         * Hint para los campos de tipo EditText
         */
        txtNombre.setHint(getString(R.string.nombre_del_gesto));

        this.txtNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            if(v==txtNombre && !hasFocus) {
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
                AplicacionVO aplicacion = (AplicacionVO) desplegableAplicacion.getSelectedItem();

                View focus = null;
                if(StringUtils.isEmpty(nombre)) {
                    txtNombre.setError(getString(R.string.error_gesto_obligatorio));
                    focus = txtNombre;
                } else
                if(aplicacion==null || aplicacion.isValorPorDefecto()) {
                    ((TextView)desplegableAplicacion.getSelectedView()).setError(getString(R.string.error_app_obligatorio));
                    focus = desplegableAplicacion;
                }

                if(focus!=null) {
                    focus.setFocusable(true);
                }else {
                    vaciarFormulario();

                    Gesto gesto = new Gesto();
                    gesto.setNombre(nombre);
                    gesto.setAplicacion(aplicacion.getNombreAplicacion());
                    Drawable icono = aplicacion.getIcono();

                    /**
                     * Se comprueba si el Bitmap es un BitmapDrawable, de este
                     */
                    if(icono instanceof BitmapDrawable) {

                        Bitmap bitmap = ((BitmapDrawable) icono).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] datos = baos.toByteArray();
                        gesto.setLogoAplicacion(datos);
                    }
                    abrirActividad(gesto);
                }

            }// onClick

         });


        recargarDesplegableAplicaciones();
    }


    /**
     * Vacia el formulario
     */
    private void vaciarFormulario() {
        this.txtNombre.setText("");
        this.desplegableAplicacion.setSelection(0);
    }

    /**
     * Abre la actividad en la que se detecta el gesto, y se le pasa el gesto en el Intent
     * @param gesto Gesto
     */
    private void abrirActividad(Gesto gesto) {
        Intent intent = new Intent(getActivity(),ActividadNuevoGesto.class);
        if(gesto!=null) {
            intent.putExtra(ConstantsGestures.PARAMETRO_GESTO_INTENT,gesto);

            startActivityForResult(intent,ConstantsGestures.RESULTADO_ACTIVIDAD_ALTA_GESTO);
        }
    }


    /**
     * Rellena el contenido del desplegable/Spinner con las aplicaciones instaladas en el dispositivo
     */
    private void recargarDesplegableAplicaciones() {
        // Se recuperan las aplicaciones del teléfono para mostrar en el Spinner

        List<ApplicationInfo> appsInstalled = TelephoneUtil.getInstalledApplications(getActivity());
        List<AplicacionVO> apps = new ArrayList<AplicacionVO>();

        for (int i = 0; appsInstalled != null && i < appsInstalled.size(); i++) {
            CharSequence salida = appsInstalled.get(i).processName;
            Drawable imagen = appsInstalled.get(i).loadIcon(getActivity().getPackageManager());

            AplicacionVO app = new AplicacionVO(salida.toString(),imagen);
            app.setIcono(imagen);

            apps.add(app);
        }


        AplicacionVO app = new AplicacionVO(getString(R.string.selecciona_aplicacion),null);
        app.setValorPorDefecto(true);
        apps.add(0,app);
        ImageSpinnerAdapter adapter = new ImageSpinnerAdapter(getContext(),R.id.desplegableAplicacion,apps);
        this.desplegableAplicacion.setAdapter(adapter);
    }






    /**
     * Método que
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to

        LogCat.info(ConstantsGestures.TAG,"onActivityResult requestCode: " + requestCode + ", resultCode: " + resultCode);
        MessageUtils.showToast(getContext(),"requestCode: " + requestCode + ", resultCode: " + resultCode,20);
        if (requestCode == ConstantsGestures.RESULTADO_ACTIVIDAD_ALTA_GESTO) {
            ActividadEntrada actividad = (ActividadEntrada)getActivity();
            actividad.recargarListadoGestos();
            LogCat.info(ConstantsGestures.TAG,"El activity se trata de ActividadEntrada");

        }
    }



    /**
     * Oculta el teclado
     */
    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * Método que carga el contenido de la vista, en este caso, del fragmento
     * @param inflater    LayoutInflater
     * @param container   ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nuevo_gesto, container, false);
    }


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

        void onFragmentInteraction(Uri uri);
    }

}
