Feature: Validating POST operations

  Scenario: Verify post create User Positive
    Given Users call the base URI for post request
    And Give the payload for the "create_new_user_payload" post request
    When User use the "postCreateUser_Positive" for the http post method
    Then Check the response "create_new_user_positive_response" for status as 201

  Scenario: Verify post create user Negative by sending empty body
    Given Users call the base URI for post request
    When User use the "postUser_Negative_SendEmptyBody" for the http post method
    Then Check the response "negative_empty_body_response" for status as 200
    And Also check the response for "negative_empty_body_response" code as 422

  Scenario: Verify post user negative body has all fields but no value
    Given Users call the base URI for post request
    And Give the payload for the "body_with_null_values" post request
    When User use the "postUser_Negative_BodyHasAllFiledsButNoValue" for the http post method
    Then Check the response "null_value_body_response" for status as 200
    And Also check the response for "null_value_body_response" code as 422
    And Also validate the error messages as "can't be blank" for the required fields
    And Also validate the gender error messages as "can't be blank, can be male of female"

  Scenario: Verify post user negative body has all fields but incorrect values
    Given Users call the base URI for post request
    And Give the payload for the "body_with_incorrect_values" post request
    When User use the "postUser_Negative_BodyHasAllFiledsButIncorrectValue" for the http post method
    Then Check the response "negative_incorrect_value_response" for status as 422
    And Also validate the email error messages as "is invalid"

  Scenario: Verify post user negative body has all fields but incorrect data type
    Given Users call the base URI for post request
    And Give the payload for the "body_with_incorrect_DataType" post request
    When User use the "postUser_Negative_BodyHasAllFieldsButIncorrectDataType" for the http post method
    Then Check the response "negative_incorrect_datatype_response" for status as 422
    And Also validate the incorrect datatype response email for error messages as "is invalid"
