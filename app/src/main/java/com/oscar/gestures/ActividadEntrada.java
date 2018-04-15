package com.oscar.gestures;

import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.oscar.asyntask.ParametrosAsyncTask;
import com.oscar.asyntask.RespuestaAsyncTask;
import com.oscar.gestures.asyntask.RecuperarGestosAsyncTask;
import com.oscar.gestures.configuracion.fichero.FicheroGestos;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.dialog.AceptarBorradoGestosDialogInterface;
import com.oscar.gestures.fragments.FragmentoFormularioNuevoGesto;
import com.oscar.gestures.fragments.FragmentoListadoGestos;
import com.oscar.gestures.fragments.FragmentoVacio;
import com.oscar.gestures.fragments.OnCheckRecyclerViewSelectionListener;
import com.oscar.gestures.fragments.SectionsPagerAdapter;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.MessageUtils;
import com.oscar.utilities.dialog.AlertDialogHelper;
import com.oscar.utilities.dialog.BtnAceptarCancelarDialogGenerico;
import com.oscar.utilities.dialog.ParamsAlertDialogVO;
import com.oscar.utilities.logcat.LogCat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Actividad principal
 *
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class ActividadEntrada extends AppCompatActivity implements FragmentoFormularioNuevoGesto.OnFragmentInteractionListener, FragmentoListadoGestos.OnListFragmentInteractionListener, FragmentoVacio.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private FicheroGestos ficheroGestos;

    private GestureLibrary gestureLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_entrada);

        /*
         * Se establece el título de la activity
         */
        setTitle(getString(R.string.title_detector_gestos));

        /*
         * FloatingActionButton
         */
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        /*
         * LISTENER PARA EL FLOATING ACTION BUTTON
         */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParamsAlertDialogVO params = new ParamsAlertDialogVO(ActividadEntrada.this,getString(R.string.pregunta_eliminar_gestos_seleccionados),getString(R.string.txt_atencion),getString(R.string.txt_dialogo_aceptar),getString(R.string.txt_dialogo_cancelar));

                AceptarBorradoGestosDialogInterface callbackAceptar = new AceptarBorradoGestosDialogInterface(ActividadEntrada.this,getGestosSeleccionados());
                AlertDialogHelper.crearDialogoAlertaConfirmacion(params,callbackAceptar,new BtnAceptarCancelarDialogGenerico()).show();

            }
        });



        /*
         * LISTENER PARA DETECTAR CUANDO EL USUARIO PULSA EL CHECK DE UN GESTO, Y HABILITAR EL FLOATING BUTTON ACTION
         */
        OnCheckRecyclerViewSelectionListener listener = new OnCheckRecyclerViewSelectionListener() {
            @Override
            public void setOnCheckSelectionListener(Object params) {
                if(fab!=null) {
                    LogCat.info(ConstantsGestures.TAG,"Se ha seleccionado objetos");
                    fab.setVisibility(View.INVISIBLE);

                    if(params instanceof Integer && ((Integer)params)>0) {
                        fab.setVisibility(View.VISIBLE);
                    }

                }
            }// setOnCheckSelectionListener
        };

        mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(),getSupportFragmentManager(),listener);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogCat.info(ConstantsGestures.TAG,"Tab seleccionada posicion: " + tab.getPosition());

                fab.setVisibility(View.INVISIBLE);
                /*
                 * Si la Tab seleccionado es distinto de cero y el FloatingActionButton está visible, entonces
                 * se oculta ya que sólo tiene que estar visible en el primer Tab
                 */
                if(tab.getPosition()!=ConstantsGestures.PRIMERA_POSICION_TAB_LAYOUT && fab.getVisibility()==View.VISIBLE) {
                    fab.setVisibility(View.INVISIBLE);
                }

                /**
                 * Si se la seleccionado el Tab de la primera posición,que muestra el listado de gestos, y
                 */
                if(tab.getPosition()==ConstantsGestures.PRIMERA_POSICION_TAB_LAYOUT && mSectionsPagerAdapter.getListaItemsSeleccionados().size()>0) {
                    fab.setVisibility(View.VISIBLE);
                }

            }// onTabSelected

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        cargarFicheroGestos();


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_entrada, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        LogCat.info(ConstantsGestures.TAG,"item menu seleccionado: " + item.getItemId());

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * Devuelve el número de gestos seleccionados por el usuario
     * @return Integer
     */
    private Integer getNumGestosSeleccionados() {
        return this.mSectionsPagerAdapter.getNumItemsSeleccionados();
    }


    /**
     * Devuelve los gestos seleccionados por el usuario
     * @return List<Gesto>
     */
    private List<Gesto> getGestosSeleccionados() {
        return (List<Gesto>)this.mSectionsPagerAdapter.getListaItemsSeleccionados();
    }



    /**
     * Operación que recarga el listado de gestos y pone el fragmento que lo contiene
     * en primer plano
     */
    public void recargarListadoGestos() {
        LogCat.info(ConstantsGestures.TAG," recargarListadoGestos ===>");

        /*
         * Se recupera de la base de datos la lista de gestos
         */
        try {
            ParametrosAsyncTask<Context> params = new ParametrosAsyncTask<Context>(getApplicationContext());
            RecuperarGestosAsyncTask task = new RecuperarGestosAsyncTask();
            task.execute(params);

            RespuestaAsyncTask respuesta = task.get();
            if(respuesta!=null && respuesta.getStatus().equals(0)) {

                List<Gesto> gestos = (List<Gesto>)respuesta.getItems();

                 /*
                  * Se obtiene el Fragmento en el que se muestran el listado de gestos
                  */
                SectionsPagerAdapter adapter = (SectionsPagerAdapter)this.mViewPager.getAdapter();
                FragmentoListadoGestos fragmentoListadoGestos = (FragmentoListadoGestos)adapter.getItem(0);

                /*
                 * Se pasan los gestos al Fragmento
                */
                fragmentoListadoGestos.getAdapter().setItems(gestos);
                fragmentoListadoGestos.getAdapter().notifyDataSetChanged();

                /*
                 * Se establece el Fragmento con el listado como fragmento actual
                 */
                this.mViewPager.setCurrentItem(0);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Esta operación carga el contenido del fichero de gestos
     */
    private void cargarFicheroGestos() {

        try {

            ficheroGestos = FicheroGestos.getInstance(getFilesDir());
            String pathFicheroGestos = ficheroGestos.getPathFicheroGestos();
            gestureLibrary = GestureLibraries.fromFile(pathFicheroGestos);

            if(!gestureLibrary.load()) {
                MessageUtils.showToast(this,"No se ha cargado el archivo de gestos",20);
                LogCat.info(ConstantsGestures.TAG,"No se ha cargado el archivo de gestos");

            } else {
                mostrarGestos(this.gestureLibrary);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarGestos(GestureLibrary library) {
        Set<String> entries = library.getGestureEntries();
        Iterator<String> it = entries.iterator();
        StringBuffer sb = new StringBuffer();

        Map<String,List<Gesture>> gestos = new HashMap<String,List<Gesture>>();

        LogCat.info(ConstantsGestures.TAG,"Fichero gestos solo lectura: " + library.isReadOnly());

        while(it.hasNext()) {
            String nombreGesto = it.next();
            LogCat.info(ConstantsGestures.TAG,nombreGesto + ",");
            sb.append(nombreGesto);
            sb.append("\r\n");

            if(gestos.containsKey(nombreGesto)) {
                gestos.get(nombreGesto).addAll(library.getGestures(nombreGesto));
            } else {
                List<Gesture> gestures = library.getGestures(nombreGesto);
                gestos.put(nombreGesto,gestures);
            }

        }// while

    }


    private String getName(int i) {
        String[] p = new String[] {"1","2","3","4","5","6","7","8","9","10"};

        return p[i];
    }


    /**
     * Método que tiene que implementar la actividad por usar el Fragment implementado
     * en la clase FragmentoListadoGestos
     *
     * @param item
     */
    public void onListFragmentInteraction(Gesto item) {
        LogCat.info(ConstantsGestures.TAG," ********** onListFragmentInteraction item: *****");
    }




    /**
     * Método que tiene que implementar la actividad por usar el Fragment FragmentoFormularioNuevoGesto
     * @param uri Uri
     */
    public void onFragmentInteraction(Uri uri){
        LogCat.info(ConstantsGestures.TAG,"************* onFragmentInteraction uri:  ************** " + uri);
    }


}
