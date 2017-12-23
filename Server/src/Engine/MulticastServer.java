package Engine;

import java.io.IOException;

public class MulticastServer {
    public static void main(String[] args) throws IOException {
        new QuoteServerThread().start();
    }
}
