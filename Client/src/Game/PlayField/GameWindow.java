package Game.PlayField;

import Game.Messages.MessageHandler;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow(MessageHandler mh)
    {
        setSize(800, 600);
        setTitle("HappyWorld");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new Board(mh));
    }
}
