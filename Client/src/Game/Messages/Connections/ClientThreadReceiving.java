package Game.Messages.Connections;

import Game.GameConstants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ClientThreadReceiving extends Thread {

    private final int clientID;
    private final ArrayList<String> queueMessages;
    private Consumer<String> logToReceiveBox;

    public ClientThreadReceiving(Consumer<String> logToReceiveBox, ArrayList<String> queueMessages) {
        this.queueMessages = queueMessages;
        this.logToReceiveBox = logToReceiveBox;
        this.clientID = ServerStats.ClientID;
    }

    public void run() {
        MulticastSocket socket;

        try {
            socket = new MulticastSocket(GameConstants.PORT);
            InetAddress group = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(group);
            String received;

            DatagramPacket packet;
            while (true) {
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                received = new String(packet.getData(), packet.getOffset(), packet.getLength());
                if (received.contains("~SERVER"))
                {
                    received = received.split("~")[0];
                    queueMessages.add(received);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
