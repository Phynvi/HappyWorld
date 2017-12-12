package Game;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static Game.clientUtils.sleep;
import static Game.clientUtils.tick;

public class Client {

    static Socket socket;
    static DataInputStream in;

    public static void main (String args[]) {
        if (!connectToServer())
        {
            return;
        }

        ReceiveMessages gameSync = new ReceiveMessages(in);
        gameSync.start();

        GameClient game = new GameClient(gameSync);
        game.start();
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
