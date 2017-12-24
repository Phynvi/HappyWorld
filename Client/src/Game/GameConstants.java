package Game;

public class GameConstants {
    public static final double TICK_TIME = 0.06;
    public static final int MS_TICK_TIME = 60;
    public static final int PORT = 5565;

    public static final String CACHE_LOC = "http://download1080.mediafire.com/aqo3a1hz3pag/67tbspu6g4jvd17/cache.zip";
    public static final String CACHE_NAME = "Cache.zip";
    public static final String CACHE_DIRECTORY = "Cache/";
    public static final String PLAYER_PINK = CACHE_DIRECTORY + "PLAYER_PINK.png";

    public enum connectionStatusEnum {NOTSTARTED, REQUESTED, CONNECTED, DISCONNECTED}
}
