package bicyclegarage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger {
    private PrintWriter writer;
    private Date date;

    /**
     * Creates a new logger that will write logs to the specified directory.
     * @param dirName The directory to write logs to.
     */
    public Logger(String dirName) {
        date = new Date();
        
        /* Create directory @dirName */
        if (!(new File(dirName).mkdirs())) {
            System.err.println("[!] Could not create directory \"" + dirName + "\".");
        }
        
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(dirName
                    + date.toString() + ".log")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a message (@logMessage) to the log file in the directory.
     * @param logMessage
     */
    public void write(String logMessage) {
        writer.println("================================================================================");
        writer.println();
        writer.println("-- DATE --");
        writer.println((new Date()).toString());
        writer.println();
        writer.println("-- MESSAGE --");
        writer.println(logMessage);
        writer.println();
    }

    public void close() {
        writer.close();
    }
}
