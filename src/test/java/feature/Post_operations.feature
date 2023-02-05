Feature: Validating POST operations

  Scenario: Verify post create User Positive
    Given Users call the base URI
    And Give the payload for the post request
    When Use the "postCreateUser_Positive" for the post http method
    Then Check the response status as 200
    And Also check the response for code as 201

  Scenario: Verify post create user Negative by sending empty body
    Given Users call the base URI
    When User use the "" for the http post method
    Then check the response status as 200 for negative scenario
    And Also check the response for code as 422
