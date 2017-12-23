package Game.Messages;

import Game.Messages.Connections.ClientThreadReceiving;
import Game.Messages.Connections.ClientThreadSending;
import Game.Messages.Connections.ServerStats;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class ConnectionHandler {

    private boolean running;
    private boolean devMode = false;

    private JTextArea input = null;
    private JTextArea logBox = null;
    private JTextArea output = null;

    private ArrayList<String> messagesBeforeOutput = new ArrayList<>();
    private ArrayList<String> messagesBeforeInput = new ArrayList<>();
    private ArrayList<String> messagesBeforeLog = new ArrayList<>();

    private ClientThreadReceiving clientReceive;
    private ClientThreadSending clientSend;

    private ArrayList<String> receiveQueue = new ArrayList<>();

    public ConnectionHandler()
    {
        this.running = true;
        Consumer<String> logToReceiveBox = (String x) -> {
            updateReceiveBox(x);
        };
        Consumer<String> logToSendBox = (String x) -> {
            updateReceiveBox(x);
        };

        clientReceive = new ClientThreadReceiving(logToReceiveBox, receiveQueue);
        try {
            clientSend = new ClientThreadSending(logToSendBox);
        } catch (IOException e) {
            log(e.getStackTrace().toString());
        }

        clientReceive.start();
        clientSend.start();
    }

    public void handle(String received)
    {
        if (received.equals("Connection established" + ServerStats.ClientID))
        {
            ServerStats.isConnected = true;
            updateReceiveBox("Connection established!");
        }
        else
        {
            updateReceiveBox("Garbage:" + received);
        }
    }

    public boolean isConnected()
    {
        return ServerStats.isConnected;
    }

    public boolean messagePending() {
        return !receiveQueue.isEmpty();
    }

    public String getMessage()
    {
        return receiveQueue.remove(0);
    }



    //Move all this shit to a log handler
    public void log(String toLog)
    {
        addToBox(toLog, logBox, messagesBeforeLog);
    }

    private void updateReceiveBox(String message)
    {
        addToBox(message, output, messagesBeforeOutput);
    }

    private void updateSendBox(String message) {
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

        updateSendBox("Input box fully initialized & synced.");
        updateReceiveBox("Output box fully initialized & synced.");
        log("Log box fully initialized & synced.");
    }

    public boolean isRunning() {
        return running;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
        log("Dev mode set to " + devMode);
    }
}
