package utils;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;

//~--- classes ----------------------------------------------------------------

public class Utils {
    public final static String in  = "in";
    public final static String out = "out";

    //~--- get methods --------------------------------------------------------

    /**
     * Obtiene la Extension del archivo.
     * @return   la extencion del archivo.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s   = f.getName();
        int    i   = s.lastIndexOf('.');

        if ((i > 0) && (i < s.length() - 1)) {
            ext = s.substring(i + 1).toLowerCase();
        }

        return ext;
    }
}
