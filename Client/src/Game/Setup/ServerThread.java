package Game.Setup;

import Game.Messages.MessageHandler;
import Game.Messages.ReceiveMessages;

import static Game.clientUtils.tick;

public class ServerThread extends Thread {

    private ReceiveMessages gameSync;
    MessageHandler mh;

    public ServerThread(ReceiveMessages sync, MessageHandler mh)
    {
        this.gameSync = sync;
        this.mh = mh;
    }

    public void run()
    {
        while (mh.isRunning())
        {
            while (gameSync.messagePending())
            {
                String message = gameSync.getMessage();
                mh.handle(message);
            }
            tick();
        }
    }
}
