package main.java.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * Created by Shailesh on 05/12/20.
 */

import java.util.List;

public class GlPageObjects {

    //Country dropdown selected Belgium

    @FindBy(css = "#Belgium-cb-label-CountryFilter")
    public  WebElement dropdownSelectionBelgium;


    @FindBy(css = "div.container:nth-child(2) div.content:nth-child(4) aside.aside div.filter-container:nth-child(4) div:nth-child(1) div.editor-modal.multi-select-modal div.btn-container:nth-child(7) > button.btnMain.btnSize")
    public WebElement updateCountry;

    @FindBy(xpath = "//td[text()='Belgium']")
    public List<WebElement> countryList;

    @FindBy(css = "a[title='Go to the next page']")
    public WebElement nextPageLink;

    @FindBy(css = "a[aria-label='Activision Blizzard Inc']")
    public WebElement hyperLink;

    @FindBy(xpath = "//*[@id='detail-issuer-name']")
    public WebElement getTitle;




}
