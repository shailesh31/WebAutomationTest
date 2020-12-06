package main.java.Utils;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties prop;
    private static InputStream inputStream;

    static {
        try {
            prop = new Properties();
            inputStream = new FileInputStream("config.properties");
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error loading config file: " + e.getMessage());
        }
    }

    // Get the property defined either at system level or in config file
    public static String getProperty(String key) {
        String property = System.getProperty(key);
        return (property != null && !property.trim().isEmpty()) ? property : prop.getProperty(key);
    }
}
