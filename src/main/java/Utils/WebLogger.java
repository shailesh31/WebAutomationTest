package main.java.Utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

/**
 * Created by Shailesh on 05/12/20.
 */
public class WebLogger {
    //Singleton Instance
    private static WebLogger instance = null;
    Logger WebLogger = Logger.getLogger("");
    FileHandler fh;
    File logFile = new File("AutomationLogs/logfile.txt");

    private WebLogger() {


    }

    public static WebLogger getInstance() {
        if (instance == null) {
            instance = new WebLogger();
        }
        return instance;
    }

    public void log(String logs) {
        WebLogger.info(logs);
    }

    public void createFile() {
        System.out.println("Creating file");

        try {
            File path = new File("AutomationLogs");
            if (!path.exists()) {
                boolean createdDirectories = new File("AutomationLogs").mkdirs();
                WebLogger.info(createdDirectories ? "Created directory for automation logs" : "Not created directory for automation logs");
            }
            fh = new FileHandler(logFile.getPath(), true);
            LogManager.getLogManager().reset();
            WebLogger.addHandler(fh);
            WebLogger.setUseParentHandlers(false);
            MyFormatter formatter = new MyFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            WebLogger.info("------------ Beginning of Automation Logs ------------");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        System.out.println("Deleting file");
        boolean delete = logFile.delete();
        System.out.println(delete ? "file deleted" : "file not deleted");
    }

    static class MyFormatter extends Formatter {

        /* (non-Javadoc)
         * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
         */
        @Override
        public String format(LogRecord record) {
            StringBuilder sb = new StringBuilder();
//            sb.append(record.getLevel()).append(':');
            sb.append(record.getMessage()).append('\n');
            return sb.toString();
        }
    }
}
