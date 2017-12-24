package Game.GameWindow;

import Game.Messages.ConnectionHandler;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class Logs extends JPanel {

    private JCheckBox devMode = new JCheckBox();

    private JTextArea logs = new JTextArea();;
    private JTextArea inbound = new JTextArea();;
    private JTextArea outbound = new JTextArea();;

    public Logs(ConnectionHandler mh)
    {
        setLayout(new GridLayout(3,1));
        add(setupDefaultPanel("Game Logs", logs, " Developer mode?", devMode));
        add(setupDefaultPanel("Inbound Messages", inbound));
        add(setupDefaultPanel("Outbound Messages", outbound));

        devMode.addActionListener(e -> {
           if (devMode.isSelected())
           {
               mh.setDevMode(true);
           }
           else
           {
               mh.setDevMode(false);
           }
        });

        mh.giveBoxs(inbound, outbound, logs);
    }

    private JPanel setupDefaultPanel(String title, JTextArea box, String additionalLabel, JCheckBox additionalOption)
    {
        JPanel dfault = new JPanel();

        DefaultCaret caret = (DefaultCaret)box.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        box.setEditable(false);

        dfault.setLayout(new BorderLayout());
        if (additionalOption == null)
        {
            dfault.add(new JLabel(title), BorderLayout.PAGE_START);
        }
        else
        {
            JPanel moreFeatures = new JPanel();
            moreFeatures.setLayout(new GridLayout(1,2));
            moreFeatures.add(new JLabel(title));

            JPanel additional = new JPanel();
            additional.setLayout(new BorderLayout());
            additional.add(additionalOption, BorderLayout.WEST);
            additional.add(new JLabel(additionalLabel), BorderLayout.CENTER);

            moreFeatures.add(additional);

            dfault.add(moreFeatures, BorderLayout.PAGE_START);
        }
        JScrollPane scroll = new JScrollPane(box);
        dfault.add(scroll);

        box.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));

        revalidate();
        return dfault;
    }

    private JPanel setupDefaultPanel(String title, JTextArea box)
    {
        return setupDefaultPanel(title, box, null, null);
    }
}
