package Game.Messages.Connections;

import Game.GameConstants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.function.Consumer;

import static Game.GameConstants.CLIENT_SIG;

/**
 * CluentThreadSending decides what to send based on the serverstate in run().
 */
public class ClientThreadSending extends Thread {

    private final long FIVE_SECONDS = 5000;
    private DatagramSocket socket = null;
    private final int clientID;
    private InetAddress group;
    private Consumer<String> logToSendBox;

    private ArrayList<String> toSendQueue;

    public ClientThreadSending(Consumer<String> logToSendBox) throws IOException {
        this.clientID = ServerStats.ClientID;
        this.logToSendBox = logToSendBox;
        socket = new DatagramSocket();
        toSendQueue = new ArrayList<>();
    }


    public void run() {
        while (ServerStats.connectionStatus != GameConstants.connectionStatusEnum.CONNECTED)
        {
            ServerStats.connectionStatus = GameConstants.connectionStatusEnum.REQUESTED;
            connect();
        }
        while (ServerStats.connectionStatus == GameConstants.connectionStatusEnum.CONNECTED) {
            while (!toSendQueue.isEmpty())
            {
                sendData(toSendQueue.remove(0));
            }
        }
    }

    private void connect() {
        try {
            group = InetAddress.getByName("230.0.0.1");

            sendData("Connection Request*" + clientID);

            System.out.println("Sending connection request");
            sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendData(String dString) {
        try {
            dString += CLIENT_SIG;
            byte[] buf = dString.getBytes();

            // send it
            DatagramPacket packet = new DatagramPacket(buf, buf.length,
                    group, GameConstants.PORT);
            logToSendBox.accept("Sending: " + dString);
            socket.send(packet);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send (String toSend)
    {
        toSendQueue.add(toSend);
    }
}