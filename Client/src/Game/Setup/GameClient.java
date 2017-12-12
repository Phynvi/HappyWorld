package Game.Setup;

import Game.Messages.MessageHandler;
import Game.Messages.ReceiveMessages;

import javax.swing.*;

import static Game.clientUtils.tick;

public class GameClient extends Thread {

    private ReceiveMessages gameSync;
    MessageHandler mh;

    public GameClient(ReceiveMessages sync, MessageHandler mh)
    {
        this.gameSync = sync;
        this.mh = mh;
    }

    public void run()
    {
        while (mh.isRunning())
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
