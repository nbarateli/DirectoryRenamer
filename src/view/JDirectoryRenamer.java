package view;

import control.DirectoryRenamer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

    private DirectoryRenamer renamer;
    private JTextArea newNamesArea;
    private JButton directoryButton;
    private IntField indexField;
    private JLabel fileList;

    /**
     * Constructor: JDirectoryRenamer(renamer)
     * -------------------------
     * Constructs a new Directory Renamer frame for given controller.
     *
     * @param renamer a controller that will be using this frame.
     */
    public JDirectoryRenamer(DirectoryRenamer renamer) throws HeadlessException {
        super(DirectoryRenamer.APP_NAME);
        this.renamer = renamer;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponents();
        pack();
    }

    private void addComponents() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 5, 5));
        addInteractors(panel);
        addTextAreas(panel);
        add(panel);
    }

    private void addTextAreas(JPanel panel) {
        panel.add(getScrollPaneFor(newNamesArea = new JTextArea(15, 25), "Names"));
        panel.add(getScrollPaneFor(fileList = new JLabel(""), "File List"));
    }

    private void addInteractors(JPanel parent) {
        JPanel grandChild = new JPanel();
        grandChild.setLayout(new BoxLayout(grandChild, BoxLayout.Y_AXIS));
        grandChild.add(directoryButton = new JButton("Choose a directory"));
        indexField = new IntField(0);
        indexField.setBorder(BorderFactory.createTitledBorder("Start Index"));
        grandChild.add(indexField);
        grandChild.add(new JButton("Rename"));
        JPanel child = new JPanel();
        child.add(new JLabel(""));
        child.add(grandChild);
        parent.add(child);
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

    }
}