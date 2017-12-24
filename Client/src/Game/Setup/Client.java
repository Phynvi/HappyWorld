package Game.Setup;

import Game.GameConstants;
import Game.Messages.ConnectionHandler;
import Game.GameWindow.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.net.MulticastSocket;

import static Game.Setup.CacheRetriever.downloadCache;
import static Game.clientUtils.sleep;

public class Client extends JFrame {

    private static ConnectionHandler mh;
    private static LoadScreen load;

    private static byte[] receiveData;
    private static MulticastSocket clientSocket;

    public static void main (String args[]) {
        //Create communication with server
        mh = new ConnectionHandler();

        //Create initial load screen
        load = new LoadScreen();

        downloadCache(load, mh);

        //Make server communication start looping
        ServerThread game = new ServerThread(mh);
        game.start();

        connectToServer();

//        ReceiveMessages gameSync = new ReceiveMessages(clientSocket);
//        gameSync.start();


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

        while (mh.getConnectionStatus() == GameConstants.connectionStatusEnum.NOTSTARTED)
        {
            qlog("Requesting connection from server..");
            sleep(1);
        }
        while (mh.getConnectionStatus() == GameConstants.connectionStatusEnum.REQUESTED)
        {
            qlog("Waiting to hear from server..");
            sleep(1);
        }

        qlog("Connection successful.");

        return true;
    }
}
