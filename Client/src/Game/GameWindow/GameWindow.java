package Game.GameWindow;

import Game.Messages.MessageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private CardLayout layout = new CardLayout();

    private JPanel panelCont = new JPanel();
    private JPanel highscoresPanel = new JPanel();
    private JPanel logsPanel = new JPanel();
    private JButton game = new JButton("Game");
    private JButton highscoresButton = new JButton("Highscores");
    private JButton logsButton = new JButton("Logs");

    public GameWindow(MessageHandler mh)
    {
        panelCont.setLayout(layout);

        highscoresPanel.add(new JLabel("Highscores"));
        logsPanel.add(new JLabel("Logs"));

        panelCont.add(new Board(mh), "Game");
        panelCont.add(highscoresPanel, "Highscores");
        panelCont.add(logsPanel, "Logs");

        layout.show(panelCont, "Game");

        add(panelCont);

        setTitle("HappyWorld");
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        add(options(), BorderLayout.PAGE_START);
        add(panelCont);
    }

    private JMenuBar options()
    {
        JMenuBar options = new JMenuBar();
        options.setLayout(new GridLayout(1,5));

        options.add(game);
        options.add(highscoresButton);
        options.add(logsButton);

        game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(panelCont, "Game");
            }
        });
        highscoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(panelCont, "Highscores");
            }
        });
        logsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(panelCont, "Logs");
            }
        });


        return options;
    }
}
