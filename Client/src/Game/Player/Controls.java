package Game.Player;

import java.awt.event.KeyEvent;

public class Controls {

    private Player p;
    private int dx;
    private int dy;

    public Controls(Player p)
    {
        this.p = p;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx -= 1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx += 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy -= 1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy += 1;
        }
    }

    public void move()
    {
        p.setX(p.getX() + dx);
        p.setY(p.getY() + dy);
    }
}
