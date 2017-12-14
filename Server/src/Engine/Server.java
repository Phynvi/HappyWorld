package Engine;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static ServerSocket serverSocket;
    static Socket socket;
    static DataOutputStream out;

    public static void main (String args[]) throws IOException {
        System.out.println("Starting server..");
        serverSocket = new ServerSocket(ServerConstants.PORT);
        System.out.println("Server started...");
        
        socket = serverSocket.accept();
        System.out.println("Connection from: " + socket.getInetAddress());
        out = new DataOutputStream(socket.getOutputStream());

        writeUTF(out, "This is a test of Java sockets");
        writeUTF(out, "This is a second test of Java sockets.. next one comes in 15 seconds.");
        sleep(15);
        writeUTF(out, "This is a final test of Java sockets");

        for (int i = 0; i < 100; i++)
        {
            writeUTF(out, "sending bullshit... " + (100-i) + " left.");
            sleep(10);
        }

        System.out.println("All the data has been sent.");
        //It tells the Client it disconnected when it's freed here
    }

    private static boolean writeUTF(DataOutputStream out, String msg)
    {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            return false;
        }
        return true;
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
