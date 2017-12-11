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


    }

    private static boolean conectToServer() {
        System.out.println("Connecting...");
        try {
            socket = new Socket("localhost", PORT);
        } catch (IOException e) {
            System.out.println("Could not connect\n" + e.getMessage() + "\n(Probably means the server isn't on)");
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
