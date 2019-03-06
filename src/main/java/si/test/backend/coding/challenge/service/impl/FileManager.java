package si.test.backend.coding.challenge.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {

    private static Logger logger = Logger.getLogger(FileManager.class.getName());
    private static final String filename = "requestCounter.txt";

    public static void writeRequestCounterToFile( Long data){

        File file = new File(filename);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static AtomicLong readLastCounter(){
        File file = new File(filename);
        if(file.exists()){
            String lastCounter = null;
            try {
                lastCounter = new String(Files.readAllBytes(Paths.get(filename)));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Exception read file:" + filename + ", exception=" + e.getMessage());
                e.printStackTrace();
            }

            if(lastCounter != null){
                return new AtomicLong(Long.parseLong(lastCounter));
            }
        }

        return new AtomicLong(0);
    }
}
