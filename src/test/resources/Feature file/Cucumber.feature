Feature: Facebook functionality testing

  Scenario: Facebook login credentials testing
    Given User is on the facebook login page
    When User should enter the username and password
    And User should click the login button
    Then User should verify the success message
