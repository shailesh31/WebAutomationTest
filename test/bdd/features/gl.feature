Feature: gl
  As a user,
  I want to be able to pick timeslot and address for my delivery
  so that I can get my order. I also want to be able to review my order
  and then proceed to payment.

  @Case1
  Scenario: User should be able to view all meetings that are associated with the country Belgium

    Given user is on the landing page for WD site
    And the Country filter is available
    When user selects “Belgium” from the Country filter list on left panel
    And clicks on Update button for the country filter list
    Then the grid displays all meetings that are associated with the country Belgium
    And no meetings associated with any other country appear on the list


  @Case1
  Scenario: User should be able to view Activision Blizzard Inc should appear in the top banner

    Given user is on the landing page for WD site
    When user clicks the Company Name “Activision Blizzard Inc” hyperlink
    Then the user lands onto the “Activision Blizzard Inc” vote card page.
    And Activision Blizzard Inc should appear in the top banner