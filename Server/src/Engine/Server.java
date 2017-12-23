/*package Engine;

import javax.naming.ldap.LdapName;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;

public class Server {

    static ArrayList<ClientThread> clientList = new ArrayList<>();

    public static void main (String args[]) throws IOException {
        DatagramSocket serverSocket;
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while(true)
        {
            System.out.println("Loop");

            serverSocket = new DatagramSocket(5565);
            System.out.println("Made socket");
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData());
            System.out.println("RECEIVED new client: " + sentence);

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();

            System.out.println("Sending confirm to client");
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            System.out.println("Sent. Adding client to clientList");


            ClientThread newClient = new ClientThread(serverSocket);
            clientList.add(newClient);
        }
    }
}
*/