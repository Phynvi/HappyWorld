import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static ServerSocket serverSocket;
    static Socket socket;
    static DataOutputStream out;

    public static final int PORT = 5565;

    public static void main (String args[]) throws IOException {
        System.out.println("Starting server..");
        serverSocket = new ServerSocket(PORT);
        System.out.println("Server started...");

        socket = serverSocket.accept();
        System.out.println("Connection from: " + socket.getInetAddress());

        System.out.println("Sending outputStream in 15 seconds...");
        sleep(15);
        out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF("This is a test of Java sockets");
        out.writeUTF("This is a second test of Java sockets.. next one comes in 15 seconds.");
        sleep(15);
        out.writeUTF("This is a final test of Java sockets");

        System.out.println("All the data has been sent.");
    }

    private static void sleep(int secs)
    {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            System.out.println("Fuck");
            e.printStackTrace();
        }
    }
}
