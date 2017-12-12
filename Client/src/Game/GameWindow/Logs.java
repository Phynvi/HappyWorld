package Game.GameWindow;

import Game.Messages.MessageHandler;

import javax.swing.*;
import java.awt.*;

public class Logs extends JPanel {

    private JTextArea logs;
    private JTextArea inbound;
    private JTextArea outbound;

    public Logs(MessageHandler mh)
    {
        setLayout(new GridLayout(3,1));
        add(setupDefaultPanel("Game Logs", logs));
        add(setupDefaultPanel("Inbound Messages", inbound));
        add(setupDefaultPanel("Outbound Messages", outbound));
    }

    private JPanel setupDefaultPanel(String title, JTextArea box)
    {
        JPanel dfault = new JPanel();

        box = new JTextArea();

        dfault.setLayout(new BorderLayout());
        dfault.add(new JLabel(title), BorderLayout.PAGE_START);
        dfault.add(box);

        return dfault;
    }
}
