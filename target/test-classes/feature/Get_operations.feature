Feature: Validating GET operations

  Scenario: Verify get all users api
    Given Users call the base URI
    When Use the "getAllUsers" with "GET" http method
    Then Verify the get all users response with status code 200
    And Also check the response body "code" as 200
    
  Scenario: Verify get all users pagination using the query parameter
  	Given Users call the base URI
  	When Users use the "getAllUsersPagination" endpoint and Enter a page no. in the query parameter
  	Then Verify the get all users with page no. response with status code 200
  	And Also check the response for page no.
  	
  Scenario: Verify get single user api using id
    Given Users call the base URI
    When Users use the "getSingleUser" endpoint with enter an id 
    Then Verify the get single user response with status code 200
    And Also check the response for id and code 200
    
  Scenario: Verify get incorrect user
    Given Users call the base URI
    When Users use the "getIncorrectUser" endpoint with enter an id 
  	Then Verify the get incorrect user response with status code 200
    And Check the response for code 404 and message as "Resource not found"
  
  Scenario: Verify get users with using Query Parameter of Gender
    Given Users call the base URI
    When Users use the "getUsersWithQueryParam_Gender" endpoint and Enter a gender in the query parameter
    Then Verify the get users with specified gender response with status code 200
    And Also check the response for specified gender
    
  Scenario: Verify get users with multiple Query Parameter gender and status
    Given Users call the base URI
    When Users use the "getUsersWithMultipleQueryParam_GenderAndStatus" endpoint and Enter multiple in the query parameter
    Then Verify the get users with multiple Query Parameter response with status code 200
    And Also check the response for gender and their status