package Engine;

import com.sun.security.ntlm.Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;


public class ServerThreadSending extends Thread {

    private long FIVE_SECONDS = 5000;
    private DatagramSocket socket = null;
    private BufferedReader in = null;
    private boolean moreQuotes = true;
    private ArrayList<String> receivedQueue;

    public static final String SERVER_SIG = new String("~SERVER");

    public ServerThreadSending(ArrayList<String> received) throws IOException {
        this.receivedQueue = received;

        socket = new DatagramSocket();

        try {
            in = new BufferedReader(new FileReader("one-liners.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not open quote file. Serving time instead.");
        }
    }


    public void run() {
        while (moreQuotes || !receivedQueue.isEmpty()) {
            try {
                String dString;
                while (!receivedQueue.isEmpty())
                {
                    sendData(receivedQueue.remove(0));
                }

                dString = getNextQuote();

                sendData(dString);

                // sleep for a while
                sleep(FIVE_SECONDS);

            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

    private void sendData(String dString) throws IOException {
        System.out.println("Sending: " + dString);

        dString = dString + SERVER_SIG;
        byte[] buf = dString.getBytes();

        // send it
        InetAddress group = InetAddress.getByName("230.0.0.1");
        DatagramPacket packet = new DatagramPacket(buf, buf.length,
                group, ServerConstants.PORT);
        socket.send(packet);
    }

    private String getNextQuote() {
        String returnValue = null;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false;
                returnValue = "No more quotes. Goodbye.";
            }
        } catch (IOException e) {
            returnValue = "IOException occurred in server.";
        }
        return returnValue;
    }

}