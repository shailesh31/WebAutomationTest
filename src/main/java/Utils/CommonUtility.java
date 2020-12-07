package main.java.Utils;

import main.java.SeleniumController.WebLauncher;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Shailesh on 06/12/20.
 */


public class CommonUtility {
    private static final String LOG_TAG = CommonUtility.class.getSimpleName();


    /* Function to tap on particular web element */
    public static void tapOnElement(WebElement locator) {
        WebLogger.getInstance().log(LOG_TAG + ": tapOnElement");
        try {
            locator.click();
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Problem clicking element. Error: " + e.getMessage());
        }
    }


    /* Function to wait till the Web element passed is not appeared */
    public static boolean waitForElement(WebElement locator) {
        return waitForElement(locator, 15);
    }

    /* Function to Explicit Wait for web element and click it if appears */
    public static void waitForElementAndClick(WebElement locator) {
        waitForElement(locator);
        tapOnElement(locator);
    }

    public static boolean waitForElements(List<WebElement> elements, int timeInSeconds) {
        WebLogger.getInstance().log(LOG_TAG + ": waitForElements");
        try {
            WebDriverWait wait = new WebDriverWait(WebLauncher.getInstance().driver, timeInSeconds);
            wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOfAllElements(elements));
            return true;
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Problem waiting for elements to display");
            return false;
        }
    }


    public static Object executeJavaScript(String script, Object... object) {
        try {
            JavascriptExecutor javaScript = ((JavascriptExecutor) WebLauncher.getDriver());
            return object.length > 0 ? javaScript.executeScript(script, object[0]) : javaScript.executeScript(script);
        } catch (WebDriverException ex) {
            WebLogger.getInstance().log(LOG_TAG + ": Problem executing javascript. Error: " + ex.getMessage());
            return null;
        }
    }


    /*Function to scroll to bottom of page */
    public static void scrollToBottom() {
        executeJavaScript("window.scrollTo(0,document.body.scrollHeight)");
    }


    public static boolean waitForElement(WebElement webElement, int timeoutInSeconds) {
        WebLogger.getInstance().log(LOG_TAG + ": waitForElement");
        try {
            WebDriverWait wait = new WebDriverWait(WebLauncher.getInstance().driver, timeoutInSeconds);
            wait.ignoring(NoSuchElementException.class, WebDriverException.class)
                    .until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Problem waiting for element to appear => " + e.getMessage());
            return false;
        }
    }
}
