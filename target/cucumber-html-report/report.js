$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:test/bdd/features/gl.feature");
formatter.feature({
  "name": "gl",
  "description": "  As a user,\n  I want to be able to view all meetings that are associated with the country Belgium\n  so that no meetings associated with any other country appear on the list.\n  I also want to be able to view Activision Blizzard Inc should appear in the top banner.",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "User should be able to view all meetings that are associated with the country Belgium",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Case1"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "user is on the landing page for WD site",
  "keyword": "Given "
});
formatter.match({
  "location": "GLPageSteps.user_is_on_the_landing_page_for_WD_site()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the Country filter is available",
  "keyword": "And "
});
formatter.match({
  "location": "GLPageSteps.the_Country_filter_is_available()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user selects “Belgium” from the Country filter list on left panel",
  "keyword": "When "
});
formatter.match({
  "location": "GLPageSteps.user_selects_Belgium_from_the_Country_filter_list_on_left_panel()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "clicks on Update button for the country filter list",
  "keyword": "And "
});
formatter.match({
  "location": "GLPageSteps.clicks_on_Update_button_for_the_country_filter_list()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the grid displays all meetings that are associated with the country Belgium",
  "keyword": "Then "
});
formatter.match({
  "location": "GLPageSteps.the_grid_displays_all_meetings_that_are_associated_with_the_country_Belgium()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "no meetings associated with any other country appear on the list",
  "keyword": "And "
});
formatter.match({
  "location": "GLPageSteps.no_meetings_associated_with_any_other_country_appear_on_the_list()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "User should be able to view Activision Blizzard Inc should appear in the top banner",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Case1"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "user is on the landing page for WD site",
  "keyword": "Given "
});
formatter.match({
  "location": "GLPageSteps.user_is_on_the_landing_page_for_WD_site()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user clicks the Company Name “Activision Blizzard Inc” hyperlink",
  "keyword": "When "
});
formatter.match({
  "location": "GLPageSteps.user_clicks_the_Company_Name_Activision_Blizzard_Inc_hyperlink()"
});
