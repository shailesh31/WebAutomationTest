package main.java.Utils;

import avro.shaded.com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import main.java.SeleniumController.WebLauncher;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CommonUtility {
    private static String LOG_TAG = CommonUtility.class.getSimpleName();
    private static double SCROLL_RATIO = 0.8;
    private static Dimension windowSize;

    public static void video() {
        WebLogger.getInstance().log(LOG_TAG + ": video");
        try {
            String target;
            if (WebLauncher.getInstance().isSmokeSuiteRunning)
                target = "./stackvideoSmoke.sh";
            else
                target = "./stackvideoFullRun.sh";
            Runtime rt = Runtime.getRuntime();
            rt.exec(target);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /* Function to tap on particular web element */
    public static void tapOnElement(WebElement locator) {
        WebLogger.getInstance().log(LOG_TAG + ": tapOnElement");
        try {
            locator.click();
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Problem clicking element. Error: " + e.getMessage());
        }
    }

    /* Function to enter data on particular web element */
    public static void sendData(WebElement locator, String data) {
        WebLogger.getInstance().log(LOG_TAG + ": sendData");
        try {
            locator.sendKeys(data);
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Locator is not available");
        }
    }

    /* function to get data from particular web element */
    public static String getData(WebElement locator) {
        WebLogger.getInstance().log(LOG_TAG + ": getData");
        String text = null;
        try {
            if (locator.isEnabled()) {
                text = locator.getText();
            }
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Locator is not available");
        }
        return text;
    }

    /* Function to clear data on particular web element */
    public static void clearData(WebElement locator) {
        WebLogger.getInstance().log(LOG_TAG + ": clearData");
        try {
            locator.clear();
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Locator is not available");
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

    public static String getPriceString(WebElement element) {
        String priceString = element.getText();
        priceString = priceString.replaceAll("\\s", "");
        WebLogger.getInstance().log(LOG_TAG + ":priceString : " + priceString);
        return priceString;
    }


    public static boolean notvisibleelement(By loctor) {
        WebLogger.getInstance().log(LOG_TAG + ": notvisibleelement");
        boolean result = false;
        WebDriverWait wait = new WebDriverWait(WebLauncher.getInstance().driver, 16);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loctor));
            WebLogger.getInstance().log(LOG_TAG + ": notvisibleelement    *****Element is not visible visible after 8 sec.");
            result = true;
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": notvisibleelement    *****Element is visible visible after 8 sec.");
            result = false;
        }
        return result;
    }

    public static boolean checkElementClickable(WebElement locator) {
        WebLogger.getInstance().log(LOG_TAG + ": checkElementClickable");
        boolean result = false;
        WebDriverWait wait = new WebDriverWait(WebLauncher.getInstance().driver, 8);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            WebLogger.getInstance().log(LOG_TAG + ":Element is clicable");
            result = true;
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Element not clickable");
            result = false;
        }
        return result;
    }

    public static void goBack() {
        WebLogger.getInstance().log(LOG_TAG + ": Navigating to back");
        WebLauncher.getInstance().driver.navigate().back();
    }

    public static void waitImplicitlyForPageToLoad() {
        WebLogger.getInstance().log(LOG_TAG + "Waiting For Page to Load ");
        try {
            WebLauncher.getInstance().driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }
    }

    public static boolean isElementExist(WebElement element) {
        WebLogger.getInstance().log(LOG_TAG + ": Checking for Element existence  ");
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Element does not exist");
            return false;
        }
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

    public static String generateRandomNumberAsString(int digitCount) {
        if (digitCount < 1)
            throw new RuntimeException("Number of digits must be greater than 1");
        StringBuilder sb = new StringBuilder(digitCount);
        Random random = new Random();
        sb.append((char) ('1' + random.nextInt(9)));
        for (int i = 1; i < digitCount; i++)
            sb.append((char) ('0' + random.nextInt(10)));
        return sb.toString();
    }

    public static boolean waitForElementDisappear(WebElement element, int timeoutInSeconds) {
        WebLogger.getInstance().log(LOG_TAG + ": waitForElementDisappear");
        try {
            WebDriverWait wait = new WebDriverWait(WebLauncher.getInstance().driver, timeoutInSeconds);
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Problem waiting for element to disappear. Error: " + e.getMessage());
            return false;
        }
    }

    public static void waitForElementDisappear(By element, int timeoutInSeconds) {
        WebLogger.getInstance().log(LOG_TAG + ": waitForElementDisappear");
        waitForExpectedCondition(ExpectedConditions.invisibilityOfElementLocated(element), timeoutInSeconds);
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


    public static void scrollToCoordinates(int x, int y) {
        executeJavaScript("window.scrollTo(" + x + "," + y + ");");
    }

    public static void scrollIntoView(WebElement element) {
        WebLogger.getInstance().log(LOG_TAG + ": scrollIntoView");
        try {
            //int winHeight = ((Long) executeJavaScript("return window.innerHeight || document.body.clientHeight")).intValue();
            //scrollToCoordinates(0, element.getLocation().getY() - winHeight / 2);
            executeJavaScript("arguments[0].scrollIntoViewIfNeeded();", element);
        } catch (NoSuchElementException ex) {
            WebLogger.getInstance().log(LOG_TAG + "Unable to scroll to element due to: " + ex.getMessage());
        }
    }

    /*Function to scroll to bottom of page */
    public static void scrollToBottom(){
        executeJavaScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static boolean isElementEnabled(WebElement element) {
        WebLogger.getInstance().log(LOG_TAG + ": isElementEnabled");
        try {
            return element.isEnabled();
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Element is not enabled");
            return false;
        }
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

    public static void clickUsingJavascript(WebElement webElement) {
        executeJavaScript("arguments[0].click();", webElement);
    }

    public static boolean isElementSelected(WebElement element) {
        WebLogger.getInstance().log(LOG_TAG + ": isElementSelected");
        try {
            return element.isSelected();
        } catch (Exception e) {
            WebLogger.getInstance().log(LOG_TAG + ": Element is not selected");
            return false;
        }
    }

    public static void triggerOnChangeReactEvent(WebElement element) {
        WebLogger.getInstance().log(LOG_TAG + ": triggerOnChangeReactEvent");
        try {
            String script = IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream("js/react-change-event-trigger.js"),
                    StandardCharsets.UTF_8);
            executeJavaScript(script + "reactTriggerChange(arguments[0]);", element);
        } catch (Exception e) {
            WebLogger.getInstance().log("Problem executing triggerOnChangeReactEvent");
        }
    }

    public static void waitForExpectedCondition(ExpectedCondition condition, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(WebLauncher.getDriver(), timeoutInSeconds);
            wait.until(condition);
        } catch (Exception e) {
            WebLogger.getInstance().log("Problem waiting for expected condition. Error: " + e.getMessage());
        }
    }

    public static void waitForPageToLoad() {
        WebLogger.getInstance().log(LOG_TAG + ": waitForPageToLoad");
        waitForExpectedCondition((ExpectedCondition<Boolean>) driver ->
                        (Boolean) executeJavaScript("return document.readyState === 'complete' && window.jQuery != undefined && jQuery.active === 0"),
                Integer.parseInt(ConfigManager.getProperty("PAGE_LOAD_TIMEOUT")));
    }

    public static boolean isAlertPresent() {
        WebLogger.getInstance().log(LOG_TAG + ": isAlertPresent");
        try {
            waitForExpectedCondition(ExpectedConditions.alertIsPresent(), 5);
            Alert alert = WebLauncher.getDriver().switchTo().alert();
            //in W3C its switching to alert even if not present but throws error while using any alert methods - Unexpected behaviour
            alert.getText();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isEnvironmentProduction(){
        if(ConfigManager.getProperty("WEB_URL").contains("grofers.com"))
            return true;
        else
            return false;
    }

    public static void closeKeyboard() {
        if (WebLauncher.getDriver() instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) WebLauncher.getDriver();
            if (androidDriver.isKeyboardShown())
                androidDriver.hideKeyboard();
        } else {
            IOSDriver iosDriver = (IOSDriver) WebLauncher.getDriver();
            if (iosDriver.isKeyboardShown())
                iosDriver.hideKeyboard();
        }
    }

    public static Dimension getWindowSize() {
        if (windowSize == null) {
            int height = Math.toIntExact((long) executeJavaScript("return window.innerHeight;"));
            int width = Math.toIntExact((long) executeJavaScript("return window.innerWidth;"));
            windowSize = new Dimension(width, height);
        }
        return windowSize;
    }

    private static void swipe(Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        AppiumDriver<MobileElement> driver = (AppiumDriver<MobileElement>) WebLauncher.getDriver();
        boolean isAndroid = driver instanceof AndroidDriver<?>;
        int ANDROID_SCROLL_DIVISOR = 3;

        if (isAndroid) {
            duration = duration.dividedBy(ANDROID_SCROLL_DIVISOR);
        } else {
            swipe.addAction(new Pause(input, duration));
            duration = Duration.ZERO;
        }
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));
    }

    public static void scroll(ScrollDirection dir, double distance) {
        WebLogger.getInstance().log(LOG_TAG + ": scroll");
        if (distance < 0 || distance > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
        Dimension size = getWindowSize();
        Point midPoint = new Point((int)(size.width * 0.5), (int)(size.height * 0.5));
        int top = midPoint.y - (int)((size.height * distance) * 0.5);
        int bottom = midPoint.y + (int)((size.height * distance) * 0.5);
        int left = midPoint.x - (int)((size.width * distance) * 0.5);
        int right = midPoint.x + (int)((size.width * distance) * 0.5);
        Duration SCROLL_DUR = Duration.ofMillis(1000);

        if (dir == ScrollDirection.UP) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (dir == ScrollDirection.DOWN) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (dir == ScrollDirection.LEFT) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }
    }

    public static void scroll(ScrollDirection dir) {
        scroll(dir, SCROLL_RATIO);
    }

    public static void scroll() {
        scroll(ScrollDirection.DOWN, SCROLL_RATIO);
    }

    public static void scrollUntilElementFound(WebElement webElement) {
        int max_scrolls = 10;
        while(max_scrolls > 0 && !waitForElement(webElement, 3)) {
            scroll();
            max_scrolls--;
        }
    }

    public static void scrollUntilElementsFound(List<WebElement> webElements) {
        int max_scrolls = 10;
        while(max_scrolls > 0 && !waitForElements(webElements, 3)) {
            scroll();
            max_scrolls--;
        }
    }

    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }
}
