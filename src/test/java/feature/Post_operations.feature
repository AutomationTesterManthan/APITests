Feature: Validating POST operations

Scenario: Verify post create User Positive
	Given Users call the base URI
	And Give the payload for the post request
	When Use the "postCreateUser_Positive" for the post http method
	Then Check the response status as 201