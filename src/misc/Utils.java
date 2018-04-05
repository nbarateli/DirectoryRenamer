package misc;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class Utils {
    public static void openInDesktop(URI uri, Desktop desktop) {
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                System.out.println("davaai");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
