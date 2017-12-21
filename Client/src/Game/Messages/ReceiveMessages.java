package Game.Messages;

import java.net.*;
import java.util.ArrayList;

public class ReceiveMessages extends Thread {

    private static ArrayList<String> serverMessageQueue;
    private DatagramSocket clientSocket;
    byte[] receiveData;
    DatagramPacket receivePacket;


    public ReceiveMessages(DatagramSocket clientSocket) {
        serverMessageQueue = new ArrayList<>();
        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        this.clientSocket = clientSocket;
    }

    public void run()
    {
        while (true)
        {
            getInfo();
        }
    }

    private void getInfo() {
        String servMessage = null;
        try {
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);

            servMessage = new String(receivePacket.getData());
        } catch (Exception e) {
            System.out.println("\n\nException occured!\n\n");
            e.printStackTrace();
            serverMessageQueue.add("End");
        }

        serverMessageQueue.add(servMessage);
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
