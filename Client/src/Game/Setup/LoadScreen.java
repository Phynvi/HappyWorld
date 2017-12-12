package Game.Setup;

import Game.Messages.MessageHandler;

import javax.swing.*;
import java.awt.*;

public class LoadScreen extends JFrame {
    private JTextArea display;

    public LoadScreen()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200,100);
        display = new JTextArea();
        display.setEditable(false);
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(display);

        this.setVisible(true);
    }

    public void setDisplay(String msg) {
        this.display.setText(msg);
    }
}
