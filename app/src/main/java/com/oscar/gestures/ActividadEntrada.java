package com.oscar.gestures;

import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.oscar.gestures.configuracion.fichero.FicheroGestos;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.fragments.FragmentoFormularioNuevoGesto;
import com.oscar.gestures.fragments.FragmentoListadoGestos;
import com.oscar.gestures.fragments.FragmentoVacio;
import com.oscar.gestures.fragments.PlaceHolderFragment;
import com.oscar.gestures.vo.Gesto;
import com.oscar.utilities.MessageUtils;
import com.oscar.utilities.logcat.LogCat;

import java.util.ArrayList;
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

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(),getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     *
     * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Context context = null;
        OnActualizarFragmentoListener listener = null;
        List<Fragment> fragmentos =  new ArrayList<Fragment>();


        /**
         *
         * @param context Contexto de la actividad en la que se muestra el FragmentPagerAdapter
         * @param fm FragmentManager
         */
        public SectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.context = context;

            /**
             * Se implementa el listener que permite actualizar el contenido del ViewPager modificando
             * un fragmento
             */
            listener = new OnActualizarFragmentoListener() {

                @Override
                public void actualizarFragmento(int posicion, Fragment fragment) {
                    LogCat.info(ConstantsGestures.TAG,"OnActualizarFragmentoListener.actualizarFragmento ====>");

                    /*
                     * Se actualiza el contenido del FragmentPageAdapter
                     */
                    mSectionsPagerAdapter.setItem(posicion,fragment);
                    mSectionsPagerAdapter.notifyDataSetChanged();

                    /*
                     * Se establece la nueva posición ViewPager
                     */
                    mViewPager.setCurrentItem(posicion);
                    mViewPager.setAdapter(mSectionsPagerAdapter);

                }
            };
        }


        /**
         * Devuelve el Fragment asociado a la posición indicada por parámetro. La posición se corresponde
         * con la página seleccionada por el usuario en el FragmentPagerAdapter
         * @param position Posición
         * @return Fragment
         */
        @Override
        public Fragment getItem(int position) {
            LogCat.info(ConstantsGestures.TAG," ============> getItem listener:  " + listener);
            fragmentos.add(PlaceHolderFragment.getFragment(position,listener));
            return fragmentos.get(position);
        }


        /**
         * Establece un nuevo fragment en el FragmentPagerAdapter
         * @param position
         * @param fragment
         */
        public void setItem(int position,Fragment fragment) {
            if(position<fragmentos.size()) {
                fragmentos.set(position, fragment);
            }
        }


        /**
         * Número total de páginas del componente
         * @return
         */
        @Override
        public int getCount() {
            // Show 3 total pages.
            return ConstantsGestures.NUMERO_OPCIONES_FRAGMENTOS;

        }

        /**
         * Devuelve el título
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.listado_gestos);
                case 1:
                    return getString(R.string.nuevo_gesto);
                case 2:
                    //return getString(R.string.title_notifications);
                    return "";
            }
            return null;
        }
    }


    /**
     * Operación que recarga el listado de gestos y pone el fragmento que lo contiene
     * en primer plano
     */
    public void recargarListadoGestos() {
        LogCat.info(ConstantsGestures.TAG," recargarListadoGestos ===>");
        cargarFicheroGestos();

        /*
         * Se obtiene el Fragmento en el que se muestran el listado de gestos
         */
        SectionsPagerAdapter adapter = (SectionsPagerAdapter)this.mViewPager.getAdapter();
        FragmentoListadoGestos fragmentoListadoGestos = (FragmentoListadoGestos)adapter.getItem(0);

        /*
         * Se pasan los gestos al Fragmento
         */
        fragmentoListadoGestos.getAdapter().setItems(this.ficheroGestos.getGestos());
        fragmentoListadoGestos.getAdapter().notifyDataSetChanged();

        /*
         * Se establece el Fragmento con el listado como fragmento actual
         */
        this.mViewPager.setCurrentItem(0);
    }


    /**
     * Esta operación carga el contenido del fichero de gestos
     */
    private void cargarFicheroGestos() {

        try {

            ficheroGestos = FicheroGestos.getInstance(getFilesDir());
            String pathFicheroGestos = ficheroGestos.getPathFicheroGestos();

            gestureLibrary = GestureLibraries.fromFile(pathFicheroGestos);
            //gestureLibrary = GestureLibraries.fromRawResource(getBaseContext(),R.raw.gestures);
            if(!gestureLibrary.load()) {
                MessageUtils.showToast(this,"No se ha cargado el archivo de gestos",20);
                LogCat.info(ConstantsGestures.TAG,"No se ha cargado el archivo de gestos");
                //finish();
            } else {

                /*
                this.gestureLibrary.removeEntry("g");
                this.gestureLibrary.removeEntry("H");
                this.gestureLibrary.removeEntry("e");
                this.gestureLibrary.removeEntry("5");
                this.gestureLibrary.removeEntry("q");
                this.gestureLibrary.removeEntry("G");
                this.gestureLibrary.removeEntry("v");
                this.gestureLibrary.removeEntry("a");
                this.gestureLibrary.removeEntry("R");
                this.gestureLibrary.removeEntry("o");
                this.gestureLibrary.removeEntry("6");
                this.gestureLibrary.removeEntry("Z");
                this.gestureLibrary.removeEntry("4");
                this.gestureLibrary.removeEntry("7");
                this.gestureLibrary.removeEntry("T");
                this.gestureLibrary.removeEntry("1");
                this.gestureLibrary.removeEntry("z");
                this.gestureLibrary.removeEntry("x");
                this.gestureLibrary.removeEntry("0");
                this.gestureLibrary.removeEntry("C");
                this.gestureLibrary.removeEntry("clear_search_text");
                this.gestureLibrary.removeEntry("m");
                this.gestureLibrary.removeEntry("F");
                this.gestureLibrary.removeEntry("h");
                this.gestureLibrary.removeEntry("w");
                this.gestureLibrary.removeEntry("i");
                this.gestureLibrary.removeEntry("Q");
                this.gestureLibrary.removeEntry("del_one_char");
                this.gestureLibrary.removeEntry("f");
                this.gestureLibrary.removeEntry("r");
                this.gestureLibrary.removeEntry("B");
                this.gestureLibrary.removeEntry("n");
                this.gestureLibrary.removeEntry("s");
                this.gestureLibrary.removeEntry("2");
                this.gestureLibrary.removeEntry("u");
                this.gestureLibrary.removeEntry("l");
                this.gestureLibrary.removeEntry("8");
                this.gestureLibrary.removeEntry("b");
                this.gestureLibrary.removeEntry("t");
                this.gestureLibrary.removeEntry("p");
                this.gestureLibrary.removeEntry("C");
                this.gestureLibrary.removeEntry("c");
                */

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
        LogCat.info(ConstantsGestures.TAG,"onListFragmentInteraction");
    }




    /**
     * Método que tiene que implementar la actividad por usar el Fragment FragmentoFormularioNuevoGesto
     * @param uri Uri
     */
    public void onFragmentInteraction(Uri uri){
        LogCat.info(ConstantsGestures.TAG,"onFragmentInteraction uri: " + uri);
    }


}
