package main.java.SeleniumController;

/**
 * Created by Shailesh on 05/12/20.
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.Utils.WebLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

import static main.java.Utils.ConfigManager.getProperty;


public class WebLauncher {
    public BROWSER launchingBrowser;
    public Boolean isSmokeSuiteRunning;

    public enum BROWSER {
        FIREFOX,
        CHROME,
        IE
    }

    //Singleton Instance
    private static WebLauncher instance = null;
    public WebDriver driver = null;
    public String url;


    private String  webBrowser,  driverName;
//    private ChromeOptions chromeOptions;

//    private String appiumVersion;

    /* Private constructor */
//    private WebLauncher() {
//        chromeOptions = new ChromeOptions();
//        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
//        chromeOptions.setAcceptInsecureCerts(true);
//        chromeOptions.addArguments("disable-infobars");
//        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//    }


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
                //switch (launchingBrowser) {
                    //case CHROME:

                    //    System.setProperty("webdriver.chrome.driver", "resources/drivers/chromedriver.exe");
                      //  driver = new ChromeDriver();

//                    case FIREFOX:
//                        System.setProperty("webdriver.gecko.driver", "resources/drivers/geckodriver.exe");
//                        driver = new FirefoxDriver();

//                    case IE:
//                        System.setProperty("webdriver.ie.driver", "resources/drivers/IEDriverServer.exe");
//                        driver = new InternetExplorerDriver();
//                        break;

                }
           // }

        //WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
       // driver.get("https://viewpoint.glasslewis.com/WD/?siteId=DemoClient");
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

        webBrowser = getProperty("WEB_BROWSER");

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

