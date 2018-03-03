package com.oscar.utilities;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.oscar.utilities.constantes.Constantes;
import com.oscar.utilities.vo.DatosUsuarioVO;

/**
 * Clase TelephoneUtil
 *
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class TelephoneUtil {


    /**
     * Devuelve la info del dispositivo
     * @param context: Context
     * @return DatosUsuarioVO
     */
    public static DatosUsuarioVO getInfoDispositivo(Context context){

        DatosUsuarioVO salida = new DatosUsuarioVO();
        StringBuffer sb = new StringBuffer();
        TelephonyManager telephoneManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);


        sb.append("Número teléfono: ").append(telephoneManager.getLine1Number());
        sb.append(Constantes.COMMA);
        sb.append("Device id: ").append(telephoneManager.getDeviceId());
        sb.append(Constantes.COMMA);
        sb.append("Network operator: ").append(telephoneManager.getNetworkOperator());
        sb.append(Constantes.COMMA);
        sb.append("Sim Serial Number: ").append(telephoneManager.getSimSerialNumber());
        sb.append(Constantes.COMMA);
        sb.append("Voice mail number: " ).append(telephoneManager.getVoiceMailNumber());
        sb.append(Constantes.COMMA);
        sb.append("Device software version: " ).append(telephoneManager.getDeviceSoftwareVersion());
        sb.append(Constantes.COMMA);
        sb.append("Subscriber id: " ).append(telephoneManager.getSubscriberId());
        sb.append(Constantes.COMMA);
        sb.append("Sim operator: " ).append(telephoneManager.getSimOperator());
        sb.append(Constantes.COMMA);
        sb.append("Sim country iso: " ).append(telephoneManager.getSimCountryIso());
        sb.append(Constantes.COMMA);
        sb.append("getVoidceMailAlphaTag: ").append(telephoneManager.getVoiceMailAlphaTag());


        salida.setNumeroTelefono(telephoneManager.getLine1Number());
        salida.setImei(telephoneManager.getDeviceId());
        salida.setRegionIso(telephoneManager.getSimCountryIso());
        salida.setMarcaDispositivo(Build.BRAND);
        salida.setNumeroSerieDispositivo(Build.SERIAL);
        salida.setModeloDispositivo(Build.MODEL);
        salida.setHardwareDispositivo(Build.HARDWARE);

        return salida;
    }


    /**
     * Devuelve información sobre el teléfono como sim, número de serie, etcétera en formato String
     * @param activity Activity desde la que se hace la petición
     * @return String
     */
    public static String getPhoneNumber(Activity activity){

        StringBuffer sb = new StringBuffer();
        TelephonyManager telephoneManager = (TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);

        sb.append("Número teléfono: ").append(telephoneManager.getLine1Number());
        sb.append("Device id: ").append(telephoneManager.getDeviceId());
        sb.append("Network operator: ").append(telephoneManager.getNetworkOperator());
        sb.append("Sim Serial Number: ").append(telephoneManager.getSimSerialNumber());
        sb.append("Voice mail number: " ).append(telephoneManager.getVoiceMailNumber());
        sb.append("Device software version: " ).append(telephoneManager.getDeviceSoftwareVersion());
        sb.append("Subscriber id: " ).append(telephoneManager.getSubscriberId());

        return sb.toString();
    }


    /**
     * Método que oculta el teclado
     * @param activity Activity
     */
    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
