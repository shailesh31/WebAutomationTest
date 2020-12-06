package main.java.SeleniumController;

/**
 * Created by Shailesh on 05/12/20.
 */

import main.java.Utils.CommonUtility;
import main.java.Utils.WebLogger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.lang.reflect.Method;
import java.util.logging.Level;

import static main.java.Utils.CommonUtility.video;
import static main.java.Utils.ConfigManager.getProperty;


public class WebLauncher {
    public BROWSER launchingBrowser;
    public Boolean isSmokeSuiteRunning;

    public enum BROWSER {
        FIREFOX,
        CHROME
    }

    //Singleton Instance
    private static WebLauncher instance = null;
    public WebDriver driver = null;
    public String url;


    private String  webBrowser,  driverName;
    private ChromeOptions chromeOptions;
    private String appiumVersion;

    /* Private constructor */
    private WebLauncher() {
        chromeOptions = new ChromeOptions();
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    }

    public static WebLauncher getInstance() {
        if (instance == null) {
            instance = new WebLauncher();
        }
        return instance;
    }

    /*@BeforeTest */
    public void start(boolean isSmokeSuite) throws IOException {
        if (driver != null) {
            return;
        }
        isSmokeSuiteRunning = isSmokeSuite;
        readCapabilities();

            {
                switch (launchingBrowser) {
                    case CHROME:

                        System.setProperty("webdriver.chrome.driver", "resources/drivers/chromedriver.exe");
                        driver = new ChromeDriver();
                        break;
                    case FIREFOX:
                        System.setProperty("webdriver.gecko.driver", "resources/drivers/geckodriver");
                        driver = new FirefoxDriver();
                        break;
                }
            }
        System.setProperty("webdriver.chrome.driver", "resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://viewpoint.glasslewis.com/WD/?siteId=DemoClient");
        driver.get(url);
        //driver.manage().window().maximize();
        WebLogger.getInstance().log("setup done.");
        WebLogger.getInstance().log("Connecting to : " + url);
    }

    //set session key cookie for feature flags
    //Commenting out this we are not currently using feature flags
    //driver.manage().addCookie(new Cookie("gr_1_sessionKey", ConfigManager.getProperty("SESSION_KEY")));
    //driver.navigate().refresh();


    public void stop() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            WebLogger.getInstance().log("Problem quiting driver: " + e.getMessage());
        }
        finally {
            driver = null;
        }

    }

    /* Read capabilities from config.properties */
    public void readCapabilities() {
        //platform = getProperty("PLATFORM");
        //runningOnBrowserStack = platform.equalsIgnoreCase("remote");
        webBrowser = getProperty("WEB_BROWSER");
        //browserVersion = getProperty("BROWSER_VERSION");
       // os = getProperty("OS");
       // osVersion = getProperty("OS_VERSION");
       // resolution = getProperty("BROWSER_RESOLUTION");
       // build = getProperty("BUILD");
        //projectName = getProperty("PROJECT_NAME");
        driverName = getProperty("DRIVER_NAME").toLowerCase();
        url = getProperty("WEB_URL");

        launchingBrowser = driverName.equalsIgnoreCase("chrome") ? BROWSER.CHROME : BROWSER.FIREFOX;
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
}

