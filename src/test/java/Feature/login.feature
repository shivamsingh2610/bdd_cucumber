Feature: Login on home page

  Scenario: Verify the login flow
    Given user is on login page
    When user fill id and passord and click on login button
    Then user redirect to welcome page