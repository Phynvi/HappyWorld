package Engine;

import java.util.ArrayList;

public class Players {
    public ArrayList<PlayerVars> playerList = new ArrayList<>();

    public void addPlayer(int id)
    {
        playerList.add(new PlayerVars(id));
    }

    public void updateLoc(int id, int x, int y)
    {
        for (PlayerVars player: playerList)
        {
            if (player.id == id)
            {
                player.x = x;
                player.y = y;
                return;
            }
        }
    }

    public class PlayerVars
    {
        private final int id;
        public int x, y;

        public PlayerVars(int id)
        {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
