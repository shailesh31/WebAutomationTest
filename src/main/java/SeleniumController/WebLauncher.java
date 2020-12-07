package main.java.SeleniumController;

/**
 * Created by Shailesh on 05/12/20.
 */

import main.java.Utils.WebLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

import static main.java.Utils.ConfigManager.getProperty;


public class WebLauncher {
    //Singleton Instance
    private static WebLauncher instance = null;
    public BROWSER launchingBrowser;
    public WebDriver driver = null;
    public String url;
    private String driverName;

    public static WebLauncher getInstance() {
        if (instance == null) {
            instance = new WebLauncher();
        }
        return instance;
    }

    public static WebDriver getDriver() {
        return getInstance().driver;
    }

    public static String getSessionId() {
        if (getDriver() != null)
            return ((RemoteWebDriver) getDriver()).getSessionId().toString();
        else
            return null;
    }

    //set session key cookie for feature flags
    //Commenting out this we are not currently using feature flags
    //driver.manage().addCookie(new Cookie("gr_1_sessionKey", ConfigManager.getProperty("SESSION_KEY")));
    //driver.navigate().refresh();

    /*@BeforeTest */
    public void start() throws IOException {
        if (driver != null) {
            return;
        }
        readCapabilities();

        {
            switch (launchingBrowser) {
                case CHROME:

                    System.setProperty("webdriver.chrome.driver", "resources/drivers/chromedriver.exe");
                    driver = new ChromeDriver();
                    break;

                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", "resources/drivers/geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;

                case IE:
                    System.setProperty("webdriver.ie.driver", "resources/drivers/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    break;

            }
        }

        //WebDriverManager.chromedriver().setup();

        driver.get(url);

        WebLogger.getInstance().log("setup done.");
        WebLogger.getInstance().log("Connecting to : " + url);
    }

    public void stop() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            WebLogger.getInstance().log("Problem quiting driver: " + e.getMessage());
        } finally {
            driver = null;
        }

    }

    /* Read capabilities from config.properties */
    public void readCapabilities() {

        driverName = getProperty("DRIVER_NAME").toLowerCase();
        url = getProperty("WEB_URL");

        launchingBrowser = driverName.equalsIgnoreCase("chrome") ? BROWSER.CHROME : BROWSER.FIREFOX;
    }

    public enum BROWSER {
        FIREFOX,
        CHROME,
        IE
    }
}


