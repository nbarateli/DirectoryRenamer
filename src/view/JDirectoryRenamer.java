package view;

import control.DirectoryRenamer;
import misc.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URI;

public class JDirectoryRenamer extends JFrame {
    /**
     * Static Method: getScrollPaneFor(component, string)
     * ---------------------
     * Generates a scrollPane for given components, then wraps it within it and returns it.
     *
     * @param component A component to be wrapped
     * @param title     the title for the scroll pane, or an empty string if no border is needed.
     * @return a scrollPane-wrapped component
     */
    private static JScrollPane getScrollPaneFor(JComponent component, String title) {
        JScrollPane scrollPane = new JScrollPane(component);
        if (title.length() > 0)
            scrollPane.setBorder(BorderFactory.createTitledBorder(title));
        return scrollPane;
    }

    private Desktop desktop;
    private JFileChooser chooser;
    private DirectoryRenamer renamer;
    private JTextArea newNamesArea;
    private IntField indexField;
    private JPanel fileList;
    private File chosenDirectory;
    private File script;

    /**
     * Constructor: JDirectoryRenamer(renamer)
     * -------------------------
     * Constructs a new Directory Renamer frame for given controller.
     *
     * @param desktop
     * @param renamer a controller that will be using this frame.
     */
    public JDirectoryRenamer(Desktop desktop, DirectoryRenamer renamer) throws HeadlessException {
        super(DirectoryRenamer.APP_NAME);
        script = renamer.getEpisodeScript();
        this.desktop = desktop;
        this.renamer = renamer;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponents();
        setResizable(false);
        pack();
    }

    /**
     * Method: addComponents
     * ---------------------
     * Adds the necessary GUI components to the frame.
     */
    private void addComponents() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 5, 5));
        addInteractors(panel);
        addTextComponents(panel);
        add(panel);
    }

    /**
     * Method: addTextComponents (panel)
     * -------------------------
     * Adds the textarea and text display label to the given panel.
     *
     * @param panel a panel on which the components will be added
     */
    private void addTextComponents(JPanel panel) {
        panel.add(getScrollPaneFor(newNamesArea = new JTextArea(15, 25), "Names"));
        fileList = new JPanel();
        fileList.setLayout(new BoxLayout(fileList, BoxLayout.Y_AXIS));
        panel.add(getScrollPaneFor(fileList, "File List"));
    }


    /**
     * Method: addInteractors(panel)
     * ------------------------
     * Adds the GUI interactors to the given panel
     *
     * @param parent a panel on which the components will be added
     */
    private void addInteractors(JPanel parent) {
        JPanel grandChild = new JPanel();
        grandChild.setLayout(new BoxLayout(grandChild, BoxLayout.Y_AXIS));
        addDirectoryButton(grandChild);
        addIndexField(grandChild);
        addRenameButton(grandChild);
        addScriptFileButtons(grandChild);
        JPanel child = new JPanel();
        child.add(grandChild);
        parent.add(child);
    }

    private void addIndexField(JPanel parent) {
        JPanel child = new JPanel(new FlowLayout());
        indexField = new IntField(0);
        child.add(new JLabel("Start index"));
        child.add(indexField);

        parent.add(child);

    }

    private void addScriptFileButtons(JPanel child) {
//        JPanel child = new JPanel(new FlowLayout());
        JButton openFileButton = new JButton("Open episode \nnames script"),
                openFolderButton = new JButton("Open script folder");
        openFileButton.addActionListener(e -> Utils.openInDesktop(script.toURI(), desktop));
        openFolderButton.addActionListener(e -> Utils.openInDesktop(script.getParentFile().toURI(), desktop));
        child.add(openFileButton);
        child.add(openFolderButton);
//        parent.add(child);

    }

    private void addRenameButton(JPanel panel) {
        JButton renameButton = new JButton("Rename");
        renameButton.addActionListener(e -> {
            renamer.rename(chosenDirectory, newNamesArea.getText(), indexField.getValue());
            displayDirectory(chosenDirectory);
        });
        panel.add(renameButton);
    }

    private void addDirectoryButton(JPanel panel) {
        JButton directoryButton = new JButton("Choose a directory");
        chooser = new JFileChooser(System.getProperty("user.desktop"));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        directoryButton.addActionListener(e -> chooseDirectory());
        panel.add(directoryButton);
    }

    /**
     * Method: chooseDirectory
     * --------------------
     * Opens a file chooser dialog and the displays given directory
     */
    private void chooseDirectory() {
        int choice = chooser.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            chosenDirectory = chooser.getSelectedFile();
            displayDirectory(chosenDirectory);
        }
    }

    private void displayDirectory(File directory) {
        File[] files = directory.listFiles();
        fileList.removeAll();
        if (files == null) return;
        for (File file : files) {
            JLabel txt = new ActionLabel(file.getName(), file.toURI(), desktop);

            fileList.add(txt);
        }
        fileList.updateUI();

    }


    private class IntField extends JTextField {
        private final int DEFAULT_VAL;

        public IntField(int defaultVal) {
            super(3);
            this.DEFAULT_VAL = defaultVal;
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar()))
                        e.setKeyChar((char) 0);
                }
            });
        }

        public int getValue() {
            return this.getText().length() > 0 ? Integer.parseInt(this.getText()) : DEFAULT_VAL;
        }

    }
}