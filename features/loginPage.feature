Feature: User Login
#
  #Scenario: Successful Login with valid credentials
    #Given the user is on the TutorialsNinja login page
    #When the user enters valid credentials (username: "abc123@gmail.com", password: "test@123")
    #And the user clicks on the Login button
    #Then the user should be redirected to the My Account page

  Scenario Outline: Successful Login with valid credentials
    Given the user is on the TutorialsNinja login page
    When user enters email as "<email>" and password as "<password>"
    And the user clicks on the Login button
    Then the user should be redirected to the My Account page

    Examples:
    | email					   | password  |
    | abc@gmail.com	   | test@123  |
    | john@gmail.com   | test@123  |
    | abc123@gmail.com | test@123  |