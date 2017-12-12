package Game.Setup;

import Game.Messages.MessageHandler;
import Game.Messages.ReceiveMessages;

import static Game.clientUtils.tick;

public class Syncer extends Thread {

    private ReceiveMessages gameSync;
    MessageHandler mh;

    public Syncer(ReceiveMessages sync, MessageHandler mh)
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
