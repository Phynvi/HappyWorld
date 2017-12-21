package Game.Setup;

import Game.GameConstants;
import Game.Messages.MessageHandler;
import Game.Messages.ReceiveMessages;
import Game.GameWindow.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import static Game.Setup.CacheRetriever.downloadCache;
import static Game.clientUtils.sleep;

public class Client extends JFrame {

    private static MessageHandler mh;
    private static LoadScreen load;

    private static byte[] receiveData;
    private static DatagramSocket clientSocket;

    public static void main (String args[]) {
        mh = new MessageHandler();
        load = new LoadScreen();

        downloadCache(load, mh);

        connectToServer();

        ReceiveMessages gameSync = new ReceiveMessages(clientSocket);
        gameSync.start();

        Syncer game = new Syncer(gameSync, mh);
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
        qlog("Attempting to establish connection...");
        try {
            receiveData = new byte[1024];
            clientSocket = new DatagramSocket();
            System.out.println("Client socket initialized");

            InetAddress IPAddress = InetAddress.getByName("localhost");
            String sentence = "Test 123";
            byte[] sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, GameConstants.PORT);
            clientSocket.send(sendPacket);
            System.out.println("Sent data");

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.setSoTimeout(5000);
            clientSocket.receive(receivePacket);
            System.out.println("Received data");

            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
        } catch (IOException e) {
            qlog("Could not connect. Trying again in 5 seconds..");
            sleep(5);
            return connectToServer();
        }
        qlog("Connection successful.");

        return true;
    }
}
