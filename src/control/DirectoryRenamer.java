package control;

import model.CustomDirectory;
import model.Directory;
import model.PrefixDirectory;
import model.Util;
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
    private static final String EPISODE_SCRIPT = "\n" +
            "function printEpisodes(table) {\n" +
            "    var res = \"\"\n" +
            "    let children = table.getElementsByClassName(\"vevent\")\n" +
            "    for (i = 0; i < children.length; i++) {\n" +
            "        try {\n" +
            "            // res += table.childNodes[i].childNodes[2].innerText.replace(/\"/ig, \"\") + \"\\n\"\n" +
            "            let name = children[i].childNodes[2].innerText.replace(/\"/ig, \"\")\n" +
            "            res += name + \"\\n\"\n" +
            "            if (name.indexOf('‡') >= 0) res += name + \"\\n\"\n" +
            "        } catch (e) {\n" +
            "            console.error(i)\n" +
            "            console.error(e)\n" +
            "            console.log(table[i])\n" +
            "        }\n" +
            "    }\n" +
            "    return res;\n" +
            "}\n";

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        JFrame frame = new JDirectoryRenamer(Desktop.getDesktop(), new DirectoryRenamer());
        frame.setVisible(true);
    }

    public void rename(File chosenDirectory, String text, int pindex) {
        String[] names = parseNames(text);
        Directory directory = new PrefixDirectory(chosenDirectory, names, pindex);
        directory.rename();
    }

    public void rename(File chosenDirectory, String text, String series, int season) {
        String[] names = parseNames(text);
        Directory directory = new CustomDirectory(chosenDirectory, names, series, season);
        directory.rename();

    }

    private String[] parseNames(String text) {
        StringTokenizer tokenizer = new StringTokenizer(text, "\n");

        String[] names = new String[tokenizer.countTokens()];
        for (int i = 0; i < names.length && tokenizer.hasMoreTokens(); i++) {
            names[i] = Util.INSTANCE.fixString(tokenizer.nextToken());
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
        } catch (Exception ignore) {

        }
        return script;
    }
}
