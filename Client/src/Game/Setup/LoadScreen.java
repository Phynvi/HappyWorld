package Game.Setup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LoadScreen extends JFrame {
    private JButton display;
    private String prevText = "Hehe";
    private boolean show = false;
    private Random Rand = new Random();

    public LoadScreen()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,80);
        setBackground(Color.BLACK);
        display = new JButton();
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(display);
        setLocationRelativeTo(null);
        display.setFocusPainted(false);

        display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show = !show;
                if (show)
                {
                    if (Rand.nextInt(5) == 1)
                    {
                        //A little easter egg never hurt anyone
                        display.setText("Stop pressing me!");
                    }
                    else
                    {
                        display.setText("This isn't a button!");
                    }
                }
                else
                    display.setText(prevText);
            }
        });

        this.setVisible(true);
    }

    public void setDisplay(String msg) {
        this.display.setText(msg);
        prevText = msg;
        show = false;
    }
}
