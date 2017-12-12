package Game.Messages;

import javax.swing.*;
import java.util.ArrayList;

public class MessageHandler {

    private boolean running;
    private JTextArea input = null;
    private JTextArea logBox = null;
    private JTextArea output = null;

    private ArrayList<String> messagesBeforeOutput = new ArrayList<>();
    private ArrayList<String> messagesBeforeInput = new ArrayList<>();
    private ArrayList<String> messagesBeforeLog = new ArrayList<>();

    public MessageHandler()
    {
        this.running = true;
    }

    public void handle(String message)
    {
        updateInputBox(message);

        if (message.equals("End"))
        {
            this.running = false;
        }
    }

    public void log(String toLog)
    {
        addToBox(toLog, logBox, messagesBeforeLog);
    }

    private void updateOutputBox (String message)
    {
        addToBox(message, output, messagesBeforeOutput);
    }

    private void updateInputBox(String message) {
        addToBox(message, input, messagesBeforeInput);
    }

    private void addToBox(String message, JTextArea boxToAdd, ArrayList<String> q)
    {
        if (boxToAdd != null)
        {
            while (!q.isEmpty())
            {
                boxToAdd.append(q.remove(0) + "\n");
            }
            boxToAdd.append(message + "\n");
        }
        else
        {
            q.add(message);
        }
    }

    public void giveBoxs(JTextArea input, JTextArea output, JTextArea log)
    {
        this.input = input;
        this.output = output;
        this.logBox = log;

        updateInputBox("Input box fully initialized & synced.");
        updateOutputBox("Output box fully initialized & synced.");
        log("Log box fully initialized & synced.");
    }

    public boolean isRunning() {
        return running;
    }

}
