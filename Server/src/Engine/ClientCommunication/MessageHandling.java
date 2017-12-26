package Engine.ClientCommunication;

import Engine.GlobalVariables.ServerVariables;

import java.util.ArrayList;

public class MessageHandling {

    private ServerVariables servVars;
    private ArrayList<String> receivedQueue;

    public MessageHandling(ArrayList<String> receivedQueue, ServerVariables servVars)
    {
        this.receivedQueue = receivedQueue;
        this.servVars = servVars;
    }

    public void handle(String received)
    {
        if (received.contains("Connection Request"))
        {
            handleConnectionRequest(received);
        }
        else if (received.contains("PlayerCoords"))
        {
            handlePlayerCoordsUpdate(received);
        }
        else
        {
            System.out.println("Garbage: " + received);
        }
    }

    private void handlePlayerCoordsUpdate(String received) {
        //mh.send("PlayerCoords:" + player.getX() + "," + player.getY() + "*" + ServerStats.ClientID);
        int x = Integer.parseInt(received.split(":")[1].split(",")[0]);
        int y = Integer.parseInt(received.split(":")[1].split(",")[1].split("\\*")[0]);
        int clientId = Integer.parseInt(received.split("\\*")[1]);
        servVars.players.updateLoc(clientId, x, y);
    }

    private void handleConnectionRequest(String received) {
        receivedQueue.add("Connection established" + received.split("\\*")[1]); //Connection accepted
        servVars.players.addPlayer(Integer.parseInt(received.split("\\*")[1]));
    }
}
