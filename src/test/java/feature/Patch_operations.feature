Feature: Validating PATCH operation

  Scenario: Verify patch update gender of existing user 
    Given Users call the base URI for patch request
    And Give the payload for the "update_gender" patch request
    When User use the "patch_update_gender" for the http patch method
    Then Check the updated patch response for status as 200
    When User use the "getSingleUser" to check updated user gender using http get method
    Then Check the patched response "user_response" for status code as 200
		And Also check the updated gender in the response