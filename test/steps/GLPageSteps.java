package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.java.Pages.GLPage;
import main.java.SeleniumController.WebLauncher;
import main.java.Utils.CommonUtility;
import org.junit.Assert;

/**
 * Created by Shailesh on 07/12/20.
 */

public class GLPageSteps {
    GLPage glPage;


    public GLPageSteps() {

        glPage = new GLPage(WebLauncher.getInstance().driver);

    }

    @Given("user is on the landing page for WD site")
    public void user_is_on_the_landing_page_for_WD_site() {


    }

    @And("the Country filter is available")
    public void the_Country_filter_is_available() {

    }

    @When("user selects “Belgium” from the Country filter list on left panel")
    public void user_selects_Belgium_from_the_Country_filter_list_on_left_panel() {
        glPage.clickOnCountry();
    }

    @When("clicks on Update button for the country filter list")
    public void clicks_on_Update_button_for_the_country_filter_list() {
        CommonUtility.waitForElementAndClick(glPage.glPageObjects.updateCountry);

    }

    @Then("the grid displays all meetings that are associated with the country Belgium")
    public void the_grid_displays_all_meetings_that_are_associated_with_the_country_Belgium() {

        CommonUtility.waitForElements(glPage.glPageObjects.countryList, 10);

        String result = "";
        for (int i = 0; i < glPage.glPageObjects.countryList.size(); i++) {

            result = glPage.glPageObjects.countryList.get(i).getText();
            Assert.assertEquals("Belgium", result);

        }

    }

    @Then("no meetings associated with any other country appear on the list")
    public void no_meetings_associated_with_any_other_country_appear_on_the_list() {


    }

    @When("user clicks the Company Name “Activision Blizzard Inc” hyperlink")
    public void user_clicks_the_Company_Name_Activision_Blizzard_Inc_hyperlink() {
        glPage.clickOnNextPage();

        glPage.hyperClick();

    }

    @Then("the user lands onto the “Activision Blizzard Inc” vote card page.")
    public void the_user_lands_onto_the_Activision_Blizzard_Inc_vote_card_page() {

    }

    @Then("Activision Blizzard Inc should appear in the top banner")
    public void activision_Blizzard_Inc_should_appear_in_the_top_banner() {
        glPage.getTitle();
        Assert.assertEquals(glPage.glPageObjects.getTitle.getText(), "Activision Blizzard Inc");

    }
}
