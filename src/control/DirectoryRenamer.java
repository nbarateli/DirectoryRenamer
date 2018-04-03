package control;

import model.Directory;
import view.JDirectoryRenamer;

import javax.swing.*;
import java.io.File;
import java.util.StringTokenizer;

public class DirectoryRenamer {
    public static final String APP_NAME = "Directory Renamer";

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        JFrame frame = new JDirectoryRenamer(new DirectoryRenamer());
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
}
