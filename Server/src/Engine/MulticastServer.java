package Engine;

import Engine.ClientCommunication.MessageHandling;
import Engine.ClientCommunication.ServerThreadReceiving;
import Engine.ClientCommunication.ServerThreadSending;
import Engine.GlobalVariables.ServerVariables;

import java.io.IOException;
import java.util.ArrayList;

public class MulticastServer {
    static ArrayList<String> received;

    public static void main(String[] args) throws IOException {
        received = new ArrayList<>();
        ServerVariables servVars = new ServerVariables();

        MessageHandling mh = new MessageHandling(received, servVars);
        new ServerThreadSending(received).start();
        new ServerThreadReceiving(mh).start();
    }
}