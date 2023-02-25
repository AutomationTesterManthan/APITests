Feature: Validating PUT operations

  Scenario: Verify update existing user and check the updated user
    Given Users call the base URI for put request
    And Give the payload for the "update_user_data" put request
    When User use the "putUpdateExistingUser_Positive" for the http put method
    Then Check the response for put response "updated_user_response" for status as 200
    When User use the "getSingleUser" to check updated user using http get method
    Then Check the response "user_response" for status code as 200
    And Also check the email in the response for updated changes
    
  Scenario: Verify put update existing user negative try to update user which is not present
  	Given Users call the base URI for put request
  	And Give the payload for the "update_user_data" put request
  	When User use the "putUpdateExistingUser_Negative_TryToUpdateUserWhichIsNotPresent" for the http put method
		Then Check the response for put response "updated_user_with_incorrect_id_response" for status as 200
		And Check the status code as 404
		And Also verify the error as "Resource not found" in the response "updated_user_with_incorrect_id_response" 
		
	Scenario: Verify put update existing user negative send incorrect values to the field
		Given Users call the base URI for put request
  	And Give the payload for the "update_incorrect_user_data" put request
  	When User use the "putUpdateExistingUser_Negative_SendIncorrectValuesToTheField" for the http put method
		Then Check the response for put response "updated_user_with_incorrect_values_response" for status as 404
		And Also verify the error as "Resource not found" in the response "updated_user_with_incorrect_values_response" 