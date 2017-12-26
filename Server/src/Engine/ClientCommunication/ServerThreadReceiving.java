package Engine.ClientCommunication;

import Engine.GlobalVariables.ServerConstants;
import Engine.GlobalVariables.ServerVariables;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import static Engine.ClientCommunication.ServerThreadSending.SERVER_SIG;

public class ServerThreadReceiving extends Thread {

    private MessageHandling mh;

    public ServerThreadReceiving(MessageHandling mh) {
        this.mh = mh;
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
                if (received.contains(SERVER_SIG))
                {
                    //Discard
                    continue;
                }
                received = received.split("~")[0];

                mh.handle(received);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
