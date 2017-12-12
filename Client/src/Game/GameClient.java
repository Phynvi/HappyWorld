package Game;

import Game.Messages.MessageHandler;
import Game.Messages.ReceiveMessages;

import javax.swing.*;

import static Game.clientUtils.tick;

public class GameClient extends Thread {

    private ReceiveMessages gameSync;

    public GameClient(ReceiveMessages sync)
    {
        this.gameSync = sync;
    }

    public void run()
    {
        MessageHandler mh = new MessageHandler();
        while (mh.running)
        {
            String message = gameSync.getMessage();
            if (message == null)
            {
                tick();
                continue;
            }

            mh.handle(message);
        }
    }
}
