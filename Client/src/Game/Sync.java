package Game;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Sync extends Thread {

    private static DataInputStream in;
    private static ArrayList<String> serverMessageQueue;

    public Sync(DataInputStream in) {
        serverMessageQueue = new ArrayList<>();
        this.in = in;
    }

    public void run()
    {
        getInfo();
    }

    private void getInfo() {
        String servMessage = null;
        System.out.println(this.getName() + ": Looking for messages..");
        try {
            servMessage = in.readUTF();
        } catch (IOException e) {
            System.out.println("No more messages waiting to be received.");
            //Tell the client to shut off
            serverMessageQueue.add("End");
            return;
        }

        serverMessageQueue.add(servMessage);
        getInfo();
    }

    /**
     * Function to tell if there's a message waiting to be handled
     * @return true if there's a message, false otherwise
     */
    public boolean messagePending() {
        return !serverMessageQueue.isEmpty();
    }

    public String getMessage() {
        if (serverMessageQueue.isEmpty())
        {
            return null;
        }
        return serverMessageQueue.remove(0);
    }
}
