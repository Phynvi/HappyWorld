package Game.Player;

import Game.GameConstants;

import javax.swing.*;
import java.awt.*;

public class Player {
    private int x;
    private int y;
    private Image image;
    public Controls controls;

    public Player()
    {
        controls = new Controls();
        ImageIcon i = new ImageIcon(GameConstants.PLAYER_PINK);
        image = i.getImage();
        x = 40;
        y = 60;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Image getImage() {
        return image;
    }

    public void move()
    {
        this.x += controls.getDx();
        this.y += controls.getDy();
    }
}
