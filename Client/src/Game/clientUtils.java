package Game;

public class clientUtils {
    public static void sleep(double secs)
    {
        try {
            Thread.sleep((long) (secs * 1000));
        } catch (InterruptedException e) {
            System.out.println("Fuck");
            e.printStackTrace();
        }
    }
}
