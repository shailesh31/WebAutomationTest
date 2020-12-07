package steps;


import io.cucumber.core.api.Scenario;
import io.cucumber.core.event.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import main.java.SeleniumController.WebLauncher;
import main.java.Utils.WebLogger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.IOException;
import java.util.ArrayList;

//import static main.java.Utils.ConfigManager.getProperty;

/**
 * Created by Shailesh Sharma 06-12-2020
 */
public class GlobalHooks {
    private static final String LOG_TAG = GlobalHooks.class.getSimpleName();

    private String sessionId;


    @Before
    public void setup(Scenario result) throws IOException {
        WebLogger.getInstance().log(LOG_TAG + ":setup");
        boolean isSmokeSuiteRunning = false;
        String tags = result.getSourceTagNames().toString();
        WebLogger.getInstance().log("Tag names: " + tags);
        if (tags.contains("smoke") || tags.contains("sanity")) {
            WebLogger.getInstance().log("Smoke suite running");
            isSmokeSuiteRunning = true;
        }

        WebLauncher.getInstance().start(isSmokeSuiteRunning);
        sessionId = WebLauncher.getSessionId();

    }

    @After
    public void stop(Scenario result) throws InterruptedException {
        WebLogger.getInstance().log(LOG_TAG + ":tear down");
        try {
            ArrayList<Status> resultStatusList = null;


            if (result.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) WebLauncher.getInstance().driver).getScreenshotAs(OutputType.BYTES);
                result.embed(screenshot, "image/png");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            WebLauncher.getInstance().stop();

        }

    }
}