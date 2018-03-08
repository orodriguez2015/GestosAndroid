package com.oscar.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.oscar.utilities.constantes.Constantes;
import com.oscar.utilities.logcat.LogCat;
import com.oscar.utilities.vo.DatosUsuarioVO;

import java.util.List;

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


    /**
     * Devuelve una lista de las aplicaciones instaladas en el dispositivo
     * @return List<ApplicationInfo>
     */
    public static List<ApplicationInfo> getInstalledApplications(Activity actividad) {
        List<ApplicationInfo> packages = null;

        try {
            final PackageManager pm = actividad.getPackageManager();
            packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

            for (ApplicationInfo packageInfo : packages) {
                LogCat.info(Constantes.TAG_UTILITIES, "Installed package :" + packageInfo.packageName);
                LogCat.info(Constantes.TAG_UTILITIES, "Installed package permission :" + packageInfo.permission);


                LogCat.info(Constantes.TAG_UTILITIES, "Installed package sourceDir :" + packageInfo.sourceDir);
                LogCat.info(Constantes.TAG_UTILITIES, "Installed package dataDir :" + packageInfo.dataDir);
                LogCat.info(Constantes.TAG_UTILITIES, "packageInfo: " + packageInfo);
            }// for

        }catch(Exception e) {
            LogCat.error(Constantes.TAG_UTILITIES,"Error al recuperar la lista de apps instaladas en el dispositivo: " + e.getMessage());
        }

        return packages;
    }


}