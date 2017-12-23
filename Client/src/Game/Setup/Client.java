package Game.Setup;

import Game.GameConstants;
import Game.Messages.MessageHandler;
import Game.Messages.ReceiveMessages;
import Game.GameWindow.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static Game.Setup.CacheRetriever.downloadCache;
import static Game.clientUtils.sleep;

public class Client extends JFrame {

    private static MessageHandler mh;
    private static LoadScreen load;

    private static byte[] receiveData;
    private static MulticastSocket clientSocket;

    public static void main (String args[]) {
        mh = new MessageHandler();
        load = new LoadScreen();

        downloadCache(load, mh);

        connectToServer();

        ReceiveMessages gameSync = new ReceiveMessages(clientSocket);
        gameSync.start();

        ServerThread game = new ServerThread(gameSync, mh);
        game.start();

        load.dispose();

        EventQueue.invokeLater(() -> {
            GameWindow gw = new GameWindow(mh);
            gw.setVisible(true);
        });
    }

    private static void qlog(String log)
    {
        mh.log(log);
        load.setDisplay(log);
    }

    private static boolean connectToServer() {
        try {
            qlog("Attempting to establish connection...");

            receiveData = new byte[1024];
            MulticastSocket socket = new MulticastSocket(GameConstants.PORT);
            InetAddress group = InetAddress.getByName("localhost");

            DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(packet);

/*            packet = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());*/

            System.out.println("CONNECTION: " + packet.getData().toString());
        } catch (IOException e) {
            e.printStackTrace();
            qlog("Could not connect. Trying again in 5 seconds..");
            sleep(5);
            return connectToServer();
        }
        qlog("Connection successful.");

        return true;
    }
}
