package Game.Messages.Connections;

import Game.GameConstants;

import java.util.Random;

public class ServerStats
{
    public static GameConstants.connectionStatusEnum connectionStatus = GameConstants.connectionStatusEnum.NOTSTARTED;
    public static final int ClientID = new Random().nextInt(10000);
}
