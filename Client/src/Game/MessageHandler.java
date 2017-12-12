package Game;

public class MessageHandler {

    public boolean running;

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
}
