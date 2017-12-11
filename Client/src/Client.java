import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    static Socket socket;
    static DataInputStream in;

    public static final int PORT = 5565;

    public static void main (String args[]) {
        if (!conectToServer())
        {
            return;
        }

        Sync gameSync = new Sync(in);
        gameSync.start();

        while (true)
        {
            String message = null;
            if (gameSync.messagePending())
            {
                message = gameSync.getMessage();
                System.out.println("We got a message!! : " + message);
            }
            if (message.equals("End"))
            {
                System.out.println("End message detected. Terminating client.");
                return;
            }
        }
    }

    private static boolean conectToServer() {
        System.out.println("Connecting...");
        try {
            socket = new Socket("localhost", PORT);
        } catch (IOException e) {
            System.out.println("Server not on.. Reconnecting in 5 seconds.");
            clientUtils.sleep(5);
            return false;
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
