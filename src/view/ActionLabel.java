package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static misc.Utils.openInDesktop;

public class ActionLabel extends JLabel {
    private static final Cursor POINTER = new Cursor(Cursor.HAND_CURSOR);
    private URI action;
    private Desktop desktop;

    public ActionLabel(String text, URI action, Desktop desktop) {
        super(text);
        this.action = action;
        this.desktop = desktop;
        setForeground(Color.BLUE);
        setFont(new Font(getFont().getFontName(), Font.BOLD, getFont().getSize()));
        addMouseListeners();
    }

    public void goToURL() {

        openInDesktop(action, desktop);

    }

    public Desktop getDesktop() {
        return desktop;
    }

    public void setDesktop(Desktop desktop) {
        this.desktop = desktop;
    }

    private void addMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToURL();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                ActionLabel.this.setCursor(POINTER);

            }
        });
    }


}
