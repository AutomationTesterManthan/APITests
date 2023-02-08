Feature: Validating POST operations

  Scenario: Verify post create User Positive
    Given Users call the base URI
    And Give the payload for the "create_new_user_payload" post request
    When Use the "postCreateUser_Positive" for the post http method
    Then Check the response status as 200
    And Also check the response for code as 201

  Scenario: Verify post create user Negative by sending empty body
    Given Users call the base URI
    When User use the "postUser_Negative_SendEmptyBody" for the http post method
    Then check the response status as 200 for negative scenario
    And Also check the response for code as 422
    
  Scenario: Verify post user negative body has all fields but no value 
  	Given Users call the base URI
  	And Give the payload for the "postUserBodyWithNullValues" post request
  	When User use the "" for the http post method
    Then check the response status as 200 for negative scenario
		And Also check the response for code as 422
		And Also validate the error messages as "can't be blank" for the required fields
		And Also validate the gender error messages as "can't be blank, can be male of female"