package Engine;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

public class ServerThreadReceiving extends Thread {

    private ServerVariables servVars;
    ArrayList<String> receivedQueue;

    public ServerThreadReceiving(ArrayList<String> received, ServerVariables servVars) {
        receivedQueue = received;
        this.servVars = servVars;
    }

    public void run() {
        MulticastSocket socket;

        try {
            socket = new MulticastSocket(ServerConstants.PORT);
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
                    //Discard
                    continue;
                }
                received = received.split("~")[0];

                handle(received);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handle(String received) {
        if (received.contains("Connection Request"))
        {
            receivedQueue.add("Connection established" + received.split("\\*")[1]); //Connection accepted
            servVars.players.addPlayer(Integer.parseInt(received.split("\\*")[1]));
        }
        else if (received.contains("PlayerCoords"))
        {
            //mh.send("PlayerCoords:" + player.getX() + "," + player.getY() + "*" + ServerStats.ClientID);
            int x = Integer.parseInt(received.split(":")[1].split(",")[0]);
            int y = Integer.parseInt(received.split(":")[1].split(",")[1]);
            int clientId = Integer.parseInt(received.split("\\*")[1]);
            servVars.players.updateLoc(clientId, x, y);
        }
        else
        {
            System.out.println("Garbage: " + received);
        }
    }
}
