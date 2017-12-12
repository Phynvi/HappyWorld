package Game.Messages;

public class MessageHandler {

    private boolean running;

    public MessageHandler()
    {
        this.running = true;
    }

    public void handle(String message)
    {
        System.out.println("Received message: " + message);
        if (message.equals("End"))
        {
            this.running = false;
        }
    }

    public boolean isRunning() {
        return running;
    }
}
