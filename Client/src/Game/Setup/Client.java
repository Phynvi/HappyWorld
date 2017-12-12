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

import static Game.Setup.CacheRetriever.downloadCache;
import static Game.clientUtils.sleep;

public class Client extends JFrame {

    private static Socket socket;
    private static DataInputStream in;
    private static MessageHandler mh;
    private static LoadScreen load;

    public static void main (String args[]) {
        mh = new MessageHandler();
        load = new LoadScreen();

        downloadCache(load);

        connectToServer();

        ReceiveMessages gameSync = new ReceiveMessages(in);
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
        qlog("Connecting...");
        try {
            socket = new Socket("localhost", GameConstants.PORT);
        } catch (IOException e) {
            qlog("Server not on.. Reconnecting in 5 seconds.");
            sleep(5);
            return connectToServer();
        }
        qlog("Connection successful.");

        try {
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            qlog("Could not get input stream. Error: " + e.getMessage());
            return false;
        }

        return true;
    }
}
