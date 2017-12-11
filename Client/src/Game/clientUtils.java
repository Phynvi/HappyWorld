package Game;

public class clientUtils {
    public static void sleep(int secs)
    {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            System.out.println("Fuck");
            e.printStackTrace();
        }
    }
}
