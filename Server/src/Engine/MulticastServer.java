package Engine;

import java.io.IOException;
import java.util.ArrayList;

public class MulticastServer {
    static ArrayList<String> received;

    public static void main(String[] args) throws IOException {
        received = new ArrayList<>();
        ServerVariables servVars = new ServerVariables();
        new ServerThreadSending(received).start();
        new ServerThreadReceiving(received, servVars).start();
    }
}