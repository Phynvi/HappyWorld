package Game;

import static Game.clientUtils.tick;

public class GameClient {

    private ReceiveMessages gameSync;

    public GameClient(ReceiveMessages sync)
    {
        this.gameSync = sync;
    }

    public void start()
    {
        int ticksSinceLast = 0;
        while (true)
        {
            String message = gameSync.getMessage();

            if (message == null)
            {
                tick();
                ticksSinceLast++;
                continue;
            }

            System.out.println("Client received: \"" + message + "\" (" + ticksSinceLast + " ticks since last message)");
            ticksSinceLast = 0;
            if (message.equals("End"))
            {
                System.out.println("End message detected. Terminating client.");
                return;
            }
        }

    }
}
