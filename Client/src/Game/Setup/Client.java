package Game.Setup;

import Game.GameConstants;
import Game.Messages.MessageHandler;
import Game.Messages.ReceiveMessages;
import Game.GameWindow.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static Game.clientUtils.sleep;

public class Client extends JFrame {

    private static Socket socket;
    private static DataInputStream in;
    private static MessageHandler mh;
//    private static GameWindow gw;

    public Client()
    {
        initUI();
    }

    public static void main (String args[]) {
        if (!connectToServer())
        {
            return;
        }

        ReceiveMessages gameSync = new ReceiveMessages(in);
        gameSync.start();

        mh = new MessageHandler();
        Syncer game = new Syncer(gameSync, mh);
        game.start();

        EventQueue.invokeLater(() -> {
            GameWindow gw = new GameWindow(mh);
            gw.setVisible(true);
        });
    }

    private void initUI() {
  //      gw = new GameWindow(mh);
    }

    private static boolean connectToServer() {
        System.out.println("Connecting...");
        try {
            socket = new Socket("localhost", GameConstants.PORT);
        } catch (IOException e) {
            System.out.println("Server not on.. Reconnecting in 5 seconds.");
            sleep(5);
            return connectToServer();
        }
        System.out.println("Connection successful.");

        try {
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Could not get input stream. Error: " + e.getMessage());
            return false;
        }

        return true;
    }
}
