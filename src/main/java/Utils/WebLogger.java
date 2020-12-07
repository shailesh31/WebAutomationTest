package main.java.Utils;

import java.util.logging.Logger;

/**
 * Created by Shailesh on 05/12/20.
 */
public class WebLogger {

    //Singleton Instance
    private static WebLogger instance = null;
    Logger WebLogger = Logger.getLogger("");


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

}
