package Game.Setup;

import Game.GameConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static Game.GameConstants.CACHE_LOC;
import static Game.GameConstants.CACHE_NAME;
import static Game.clientUtils.unzip;

public class CacheRetriever {
    public static boolean downloadCache(LoadScreen load)
    {
        setupCacheDir();

        load.setDisplay("Downloading cache..");
        try {
            download(load);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        load.setDisplay("Unzipping cache..");
        try {
            unzip(new File(CACHE_LOC + File.separator + CACHE_NAME), CACHE_LOC);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static void download(LoadScreen load) throws IOException {
        URL url = new URL(CACHE_LOC);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.addRequestProperty("User-Agent", "Mozilla/4.76");
        int responseCode = httpConn.getResponseCode();
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = GameConstants.CACHE_LOC + File.separator + CACHE_NAME;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            long startTime = System.currentTimeMillis();
            int downloaded = 0;
            long numWritten = 0;
            int length = httpConn.getContentLength();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                numWritten += bytesRead;
                downloaded += bytesRead;
                int percentage = (int)(((double)numWritten / (double)length) * 100D);
                int downloadSpeed = (int) ((downloaded / 1024) / (1 + ((System.currentTimeMillis() - startTime) / 1000)));

                load.setDisplay("Downloading cache " + percentage + "% @ " + downloadSpeed + "Kb/s");
            }

            outputStream.close();
            inputStream.close();

        } else {
            System.out.println("Cache host replied HTTP code: " + responseCode);
            throw new IOException();
        }
        httpConn.disconnect();
    }

    private static void setupCacheDir() {
        final File cacheDir = new File(GameConstants.CACHE_DIRECTORY);
        if (cacheDir.exists())
        {
            cacheDir.delete();
        }
        cacheDir.mkdir();
    }
}
