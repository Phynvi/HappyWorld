package Game.Messages.Connections;

import java.util.Random;

public class ServerStats
{
    public static boolean isConnected = false;
    public static final int ClientID = new Random().nextInt(10000);
}
