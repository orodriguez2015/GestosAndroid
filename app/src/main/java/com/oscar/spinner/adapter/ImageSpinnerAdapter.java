package com.oscar.spinner.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oscar.gestures.R;
import com.oscar.gestures.constantes.ConstantsGestures;
import com.oscar.gestures.vo.AplicacionVO;
import com.oscar.utilities.logcat.LogCat;

import java.util.List;

/**
 * Adapter para un desplegable de tipo Spinner, que en cada
 * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class ImageSpinnerAdapter extends ArrayAdapter<AplicacionVO> {

    private List<AplicacionVO> items = null;
    private Context context = null;

    /**
     * Constructor
     * @param context    Context
     * @param resource   int
     * @param items      List<AplicacionVO>
     */
    public ImageSpinnerAdapter(@NonNull Context context, int resource,List<AplicacionVO> items) {
        super(context, R.layout.spinner_item,items);
        this.items = items;
        this.context = context;
    }


    /**
     * Método que se invoca al pintar el spinner la primera vez
     * @param position       Posicion
     * @param convertView    Vista en la que se inyectar el spinner_item para pintar el contenido de cada item del Spinner
     * @param parent         ViewGroup
     * @return View
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.spinner_item,null);
        }

        AplicacionVO app = items.get(position);
        LogCat.info(ConstantsGestures.TAG,"getView position: " + position + ", app: " +app.getNombreAplicacion());

        ((TextView) convertView.findViewById(R.id.textoDesplegable)).setText(app.getNombreAplicacion());
        ((ImageView) convertView.findViewById(R.id.imagenDesplegable)).setImageDrawable(app.getIcono());

        return convertView;
    }


    /**
     * Método que se invoca al desplegar el Spinner/Desplegable
     * @param position       Posicion
     * @param convertView    Vista en la que se inyectar el spinner_item para pintar el contenido de cada item del Spinner
     * @param parent         ViewGroup
     * @return View
     */
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LogCat.info(ConstantsGestures.TAG,"getDropDownView position: " + position);

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.spinner_item, parent, false);
            LogCat.info(ConstantsGestures.TAG,"getDropDownView row: " + row);
        }

        if (row.getTag() == null) {
            LogCat.info(ConstantsGestures.TAG,"getDropDownView row.getTag()==null");
            ImageSpinnerHolder redSocialHolder = new ImageSpinnerHolder();
            redSocialHolder.setIcono((ImageView) row.findViewById(R.id.imagenDesplegable));
            redSocialHolder.setTextView((TextView) row.findViewById(R.id.textoDesplegable));
            row.setTag(redSocialHolder);
        }

        //rellenamos el layout con los datos de la fila que se está procesando
        AplicacionVO app = items.get(position);
        ((ImageSpinnerHolder) row.getTag()).getTextView().setText(app.getNombreAplicacion());

        if(app.getIcono()!=null) {
            ((ImageSpinnerHolder) row.getTag()).getIcono().setImageDrawable(app.getIcono());
        }

        return row;
    }

    /**
     * Holder para el Adapter del Spinner
     * @author <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
     */
    public static class ImageSpinnerHolder {

        private ImageView icono;

        private TextView textView;

        public ImageView getIcono()
        {
            return icono;
        }

        public void setIcono(ImageView icono)
        {
            this.icono = icono;
        }

        public TextView getTextView()
        {
            return textView;
        }

        public void setTextView(TextView textView)
        {
            this.textView = textView;
        }

    }// ImageSpinnerHolder


}
