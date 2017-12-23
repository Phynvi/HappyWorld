package Game.Setup;

import Game.Messages.ConnectionHandler;

import static Game.clientUtils.tick;


/**
 * This is the class that's responsible for making the MessageHandler handle its messages
 * It maintains a thread of the MessageHandler and makes it check for messages every tick
 */
public class ServerThread extends Thread {

    ConnectionHandler mh;

    public ServerThread(ConnectionHandler mh)
    {
        this.mh = mh;
    }

    public void run()
    {
        while (mh.isRunning())
        {
            while (mh.messagePending())
            {
                String message = mh.getMessage();
                mh.handle(message);
            }
            tick();
        }
    }
}
