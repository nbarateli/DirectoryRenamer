package control;

import model.Directory;
import view.JDirectoryRenamer;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class DirectoryRenamer {
    public static final String APP_NAME = "Directory Renamer";
    private static final String EPISODE_SCRIPT =
            "function episodes() {\n" +
                    "    var res = \"\";\n" +
                    "    for (i = 1; i < 15; i++)\n" +
                    "        res += $($('.vevent')[i]).children()[2].childNodes[1].innerText + \"\\n\"\n" +
                    "    return res;\n" +
                    "}";

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        JFrame frame = new JDirectoryRenamer(Desktop.getDesktop(), new DirectoryRenamer());
        frame.setVisible(true);
    }

    public void rename(File chosenDirectory, String text, int value) {
        String[] names = parseNames(text);
        Directory directory = new Directory(chosenDirectory, names, value);
        directory.rename();
    }

    private String[] parseNames(String text) {
        StringTokenizer tokenizer = new StringTokenizer(text, "\n");

        String[] names = new String[tokenizer.countTokens()];
        for (int i = 0; i < names.length && tokenizer.hasMoreTokens(); i++) {
            names[i] = tokenizer.nextToken();
        }
        return names;
    }

    public File getEpisodeScript() {
        File script = new File(System.getProperty("user.dir") + "/script.js");
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(script)));
            writer.write(EPISODE_SCRIPT);
            writer.flush();
            writer.close();
        } catch (Exception e) {

        }
        return script;
    }
}
