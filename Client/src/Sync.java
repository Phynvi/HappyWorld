import java.io.DataInputStream;
import java.io.IOException;

public class Sync extends Thread {

    static DataInputStream in;

    public Sync(DataInputStream in) {
        this.in = in;
    }

    public void run()
    {
        getInfo();
    }

    private void getInfo() {
        String test = null;
        System.out.println(this.getName() + ": Looking for messages to be sent..");
        try {
            test = in.readUTF();
        } catch (IOException e) {
            System.out.println("No more messages waiting to be received.");
            return;
        }
        System.out.println(this.getName() + ": Message from server: " + test);
        getInfo();
    }

}
