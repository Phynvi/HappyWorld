package Game;

import Game.Messages.ConnectionHandler;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

    public static void tick() {
        sleep(GameConstants.TICK_TIME);
    }

    public static void unzip(File tounzip, String destination, ConnectionHandler mh) throws IOException {
        byte[] buffer = new byte[1024];

        //create output directory is not exists
        File folder = new File(destination);
        if(!folder.exists()){
            folder.mkdir();
        }

        //get the zip file content
        ZipInputStream zis =
                new ZipInputStream(new FileInputStream(tounzip));
        //get the zipped file list entry
        ZipEntry ze = zis.getNextEntry();

        while(ze!=null){

            String fileName = ze.getName();
            File newFile = new File(destination + File.separator + fileName);

            mh.log("Unzipped file: "+ newFile.getAbsoluteFile());

            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();

            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

            fos.close();
            ze = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();
    }
}
