package Game;

import Game.Messages.MessageHandler;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private Image img;
    private Timer timer;
    private MessageHandler mh;

    public Board(MessageHandler mh) {
        initBoard();
        this.mh = mh;
    }

    private void initBoard() {
        setBackground(Color.BLACK);
        loadImage();

        int w = img.getWidth(this);
        int h =  img.getHeight(this);
        setPreferredSize(new Dimension(w, h));

        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(),
                0, GameConstants.MS_TICK_TIME);
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("Cache/purpleSquare.png");
        img = ii.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run()
        {
            System.out.println("Still receiving packages: " + mh.isRunning());
        }
    }
}
