package utils;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

//~--- classes ----------------------------------------------------------------

public class InOutFilter extends FileFilter {

    /**
     * Acepta todos los directorios y los archivos *.in y *.out.
     * @param    f   el archivo que se evalua.
     * @return   <tt>true</tt> si es un directorio
     * o un archivo con extencion .in o .out.
     */
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);

        if (extension != null) {
            if (extension.equals(Utils.out) || extension.equals(Utils.in)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //~--- get methods --------------------------------------------------------

    /**
     * Descriptor del filtro
     * @return   la descripsión del filtro.
     */
    public String getDescription() {
        return "Archivos de Entrada y Salida (*.in; *.out)";
    }
}
