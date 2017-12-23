/*package Engine;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import static Engine.Server.clientList;

public class ClientThread extends Thread {
    private DatagramSocket clientSocket;

    public ClientThread(DatagramSocket serverSocket) {
        this.clientSocket = serverSocket;
    }

    @Override
    public void run() {
        DatagramPacket receiveFromClientPacket = null;
        while (!clientSocket.isClosed())
        {
            try {
                clientSocket.receive(receiveFromClientPacket);
                if (receiveFromClientPacket != null)
                {
                    for (ClientThread client : clientList)
                    {
                        client.getClientSocket().send(receiveFromClientPacket);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (clientSocket.isClosed())
        {
            System.out.println("Client closed! Terminating socket");
        }
    }

    public DatagramSocket getClientSocket()
    {
        return this.clientSocket;
    }
}
*/