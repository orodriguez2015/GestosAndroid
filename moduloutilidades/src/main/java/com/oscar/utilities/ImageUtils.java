package com.oscar.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.oscar.utilities.logcat.LogCat;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Clase ImageUtils
 *
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class ImageUtils {

    public static final String TAG = "ImageUtils";

    public static Bitmap getImageBitmap(String url) {
        Bitmap bm = null;

        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            LogCat.debug(TAG,"Error getting bitmap: " + e.getMessage());
        }
        return bm;
    }


    /**
     * Convierte un array de bytes en un BitmapDrawable
     * @param image byte[]
     * @return BitmapDrawable
     */
    public static Drawable convert(byte[] image) {
        Drawable salida = null;
        if(image!=null && image.length>0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);

            ByteArrayInputStream bais = new ByteArrayInputStream(image);
            salida  = BitmapDrawable.createFromStream(bais,"logo");
        }

        return salida;
    }
}
