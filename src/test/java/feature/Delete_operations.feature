Feature: Validating DELETE operations

  Scenario: Verify delete single user api
    Given Users call the base URI for delete request
    When Use the "deleteSingleUser" with DELETE http method
    Then Verify the delete single user response with status code 204
    When Use the "deleteSingleUser" with DELETE http method
    Then Also check the delete response body for message as "Resource not found"
