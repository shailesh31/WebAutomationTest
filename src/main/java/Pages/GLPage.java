package main.java.Pages;

import main.java.PageObjects.GlPageObjects;
import main.java.Utils.CommonUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Shailesh on 05/12/20.
 */

public class GLPage {


    public GlPageObjects glPageObjects = new GlPageObjects();


    public GLPage(WebDriver driver) {
        PageFactory.initElements(driver, glPageObjects);
        driver.manage().window().maximize();


    }

    public void clickOnCountry(){
        CommonUtility.waitForElementAndClick(glPageObjects.dropdownSelectionBelgium);
    }

    public void clickOnNextPage(){
        CommonUtility.scrollToBottom();
        CommonUtility.waitForElementAndClick(glPageObjects.nextPageLink);


    }

    public void hyperClick(){
        CommonUtility.waitForElementAndClick(glPageObjects.hyperLink);
    }

    public void getTitle(){
        CommonUtility.waitForElement(glPageObjects.getTitle);
        System.out.println(glPageObjects.getTitle.getText());

    }
}
