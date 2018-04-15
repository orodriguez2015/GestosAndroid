package com.oscar.gestures.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oscar.gestures.OnActualizarFragmentoListener;
import com.oscar.gestures.R;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.utilities.logcat.LogCat;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 *
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context context = null;
    private PlaceHolderFragment placeHolderFragment = null;

    OnActualizarFragmentoListener listener = null;
    List<Fragment> fragmentos =  new ArrayList<Fragment>();


    /**
     *
     * @param context Contexto de la actividad en la que se muestra el FragmentPagerAdapter
     * @param fm FragmentManager
     */
    public SectionsPagerAdapter(Context context, FragmentManager fm,OnCheckRecyclerViewSelectionListener onCheckRecyclerViewSelectionListener) {
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
                setItem(posicion,fragment);
                notifyDataSetChanged();

            }
        };

        placeHolderFragment = new PlaceHolderFragment(context,listener,onCheckRecyclerViewSelectionListener);
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
        fragmentos.add(placeHolderFragment.getFragment(position));
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
     * Número total de páginas/fragmento del componente
     * @return int
     */
    @Override
    public int getCount() {
        // Show 3 total pages.
        return ConstantsGestures.NUMERO_OPCIONES_FRAGMENTOS;
    }


    /**
     * Devuelve una lista de items seleccionados
     * @return List
     */
    public List getListaItemsSeleccionados() {
        FragmentoListadoGestos fragment = (FragmentoListadoGestos) getItem(ConstantsGestures.POSICION_FRAGMENTO_LISTADO_GESTOS);
        return fragment.getGestosSeleccionados();
    }

    /**
     * Devuelve el número de items seleccinoados
     * @return Integer
     */
    public Integer getNumItemsSeleccionados() {
        FragmentoListadoGestos fragment = (FragmentoListadoGestos) getItem(ConstantsGestures.POSICION_FRAGMENTO_LISTADO_GESTOS);
        return fragment.getGestosSeleccionados().size();
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
                return context.getString(R.string.listado_gestos);
            case 1:
                return context.getString(R.string.nuevo_gesto);
            case 2:
                return "";
        }
        return null;
    }
}
