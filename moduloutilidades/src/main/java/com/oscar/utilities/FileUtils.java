package com.oscar.utilities;

import com.oscar.utilities.exception.FileException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Clase FileUtils con utilidades para el trabajo con ficheros
 *
 * <a href="mailto:oscar.rodriguezbrea@gmail.com">Óscar Rodríguez</a>
 */
public class FileUtils {

    /**
     * Graba el contenido de un InputStream en un OutputStream a través de un buffer de 1024 bytes
     * @param is  InputStream
     * @param os  OutputStream
     */
    public static void writeFile(InputStream is, OutputStream os) throws FileException {
        try {

            byte[] buffer = new byte[1024];
            int bytesRead;
            //read from is to buffer
            while((bytesRead = is.read(buffer)) !=-1){
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            //flush OutputStream to write any buffered data to file
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(e);
        }
    }



}
