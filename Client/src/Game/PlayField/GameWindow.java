package Game.PlayField;

import Game.Messages.MessageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private CardLayout layout = new CardLayout();

    private JPanel panelCont = new JPanel();
    private JPanel loginScreen = new JPanel();
    private JPanel world2 = new JPanel();
    private JButton login = new JButton("Login Screen");
    private JButton world1b = new JButton("World 1");
    private JButton world2b = new JButton("World 2");

    public GameWindow(MessageHandler mh)
    {
        panelCont.setLayout(layout);

        loginScreen.add(new JLabel("LoginScreen"));
        world2.add(new JLabel("W2"));

        panelCont.add(loginScreen, "Login Screen");
        panelCont.add(new Board(mh), "World 1");
        panelCont.add(world2, "World 2");

        layout.show(panelCont, "World 1");

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

        options.add(login);
        options.add(world1b);
        options.add(world2b);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(panelCont, "Login Screen");
            }
        });
        world1b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(panelCont, "World 1");
            }
        });
        world2b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(panelCont, "World 2");
            }
        });


        return options;
    }
}
