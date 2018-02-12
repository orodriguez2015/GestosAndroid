package com.oscar.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.oscar.utilities.logcat.LogCat;

import java.io.BufferedInputStream;
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

}
