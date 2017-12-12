package Game;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static Game.clientUtils.sleep;

public class Client {

    static Socket socket;
    static DataInputStream in;

    public static final int PORT = 5565;

    public static void main (String args[]) {
        if (!connectToServer())
        {
            return;
        }

        ReceiveMessages gameSync = new ReceiveMessages(in);
        gameSync.start();

        int ticksSinceLast = 0;
        while (true)
        {
            String message = gameSync.getMessage();

            if (message == null)
            {
                tick();
                ticksSinceLast++;
                continue;
            }

            System.out.println("Client received: \"" + message + "\" (" + ticksSinceLast + " ticks since last message)");
            ticksSinceLast = 0;
            if (message.equals("End"))
            {
                System.out.println("End message detected. Terminating client.");
                return;
            }
        }
    }

    private static void tick() {
        sleep(GameConstants.TICK_TIME);
    }

    private static boolean connectToServer() {
        System.out.println("Connecting...");
        try {
            socket = new Socket("localhost", PORT);
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
