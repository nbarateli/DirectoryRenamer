package control;

import view.JDirectoryRenamer;

import javax.swing.*;

public class DirectoryRenamer {
    public static final String APP_NAME = "Directory Renamer";

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        JFrame frame = new JDirectoryRenamer(null);
        frame.setVisible(true);
    }
}
