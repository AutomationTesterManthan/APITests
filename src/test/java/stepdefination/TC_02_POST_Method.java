package stepdefination;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Test_Data;
import utils.URI_Endpoints;
import utils.Utils_Class;

public class TC_02_POST_Method extends Utils_Class{
	
	RequestSpecification request;
	RequestSpecification request_body;
	RequestSpecification null_values_body;
	RequestSpecification incorrect_values_body;
	RequestSpecification incorrect_datatype_body;
	static URI_Endpoints Endpoint;
	Test_Data data = new Test_Data();
	Response post_response;
	Response create_new_user_positive_response;
	Response negative_response;
	Response negative_empty_body_response;
	Response null_values_response;
	Response null_value_body_response;
	Response incorrect_values_response;
	Response negative_incorrect_value_response;
	Response incorrect_datatype_response;
	Response negative_incorrect_datatype_response;
	int actual_code;
	String code;
	JsonPath js;
	String msg;;
	
	@Given("Users call the base URI for post request")
	public void users_call_the_base_uri_for_post_request() throws Exception {
		
		request = given().
					  spec(requestSpecBuilder()).
					  auth().oauth2(get_property("AccessToken")).
					  contentType(ContentType.JSON);
		
	}
	@Given("Give the payload for the {string} post request")
	public void give_the_payload_for_the_post_request(String request_Body) {
		if(request_Body.equalsIgnoreCase("create_new_user_payload")) {
			
			request_body = request.
						body(data.create_new_user_payload());
		
		}else if(request_Body.equalsIgnoreCase("body_with_null_values")) {
			
			null_values_body = request.
						body(data.body_with_null_values());
			
		}else if(request_Body.equalsIgnoreCase("body_with_incorrect_values")) {
			
			incorrect_values_body = request.
						body(data.body_with_incorrect_values());
			
		}else if(request_Body.equalsIgnoreCase("body_with_incorrect_DataType")) {
			
			incorrect_datatype_body = request.
						body(data.body_with_incorrect_values());
			
		}
		else {
			
			System.out.println("No Match Found!");
			
		}
		
	}
	
	@When("User use the {string} for the http post method")
	public void user_use_the_for_the_http_post_method(String endpoint) {
		
		Endpoint = URI_Endpoints.valueOf(endpoint);
		
		if(endpoint.equalsIgnoreCase("postCreateUser_Positive")) {
			
			post_response = request_body.
					when().
						post(Endpoint.get_Endpoint());
			
		}else if(endpoint.equalsIgnoreCase("postUser_Negative_SendEmptyBody")) {
			
			negative_response = request.
					when().
						post(Endpoint.get_Endpoint());
			
		}else if(endpoint.equalsIgnoreCase("postUser_Negative_BodyHasAllFiledsButNoValue")){
			
			null_values_response = null_values_body.
					when().
						post(Endpoint.get_Endpoint());
			
		}else if(endpoint.equalsIgnoreCase("postUser_Negative_BodyHasAllFiledsButIncorrectValue")){
			
			incorrect_values_response = incorrect_values_body.
					when().
						post(Endpoint.get_Endpoint());
			
		}else if(endpoint.equalsIgnoreCase("postUser_Negative_BodyHasAllFieldsButIncorrectDataType")){
			
			incorrect_datatype_response = incorrect_datatype_body.
					when().
						post(Endpoint.get_Endpoint());
			
		}
		
	}
	
	@Then("Check the response {string} for status as {int}")
	public void check_the_response_status_as(String response_body, Integer int1) {
		
		if(response_body.equalsIgnoreCase("create_new_user_positive_response")) {
			
			create_new_user_positive_response = post_response.
					then().
						statusCode(201).
						extract().
						response();
			
		}else if(response_body.equalsIgnoreCase("negative_empty_body_response")) {
			
			negative_empty_body_response = negative_response.
					then().
						statusCode(200).
						extract().
						response();
			
		}else if(response_body.equalsIgnoreCase("null_value_body_response")) {
			
			null_value_body_response = null_values_response.
					then().
						statusCode(200).
						extract().
						response();
			
		}else if(response_body.equalsIgnoreCase("negative_incorrect_value_response")) {
			
			negative_incorrect_value_response = incorrect_values_response.
					then().
						statusCode(422).
						extract().
						response();
			
		}else if(response_body.equalsIgnoreCase("negative_incorrect_datatype_response")) {
			
			negative_incorrect_datatype_response = incorrect_datatype_response.
					then().
						statusCode(422).
						extract().
						response();
			
		}
	}
	
	@Then("Also check the response for {string} code as {int}")
	public void also_check_the_response_for_code_as(String response, Integer response_code) {
		
		if(response.equalsIgnoreCase("negative_empty_body_response") && response_code.equals(422)){
			
			code = json_convertor(negative_empty_body_response, "code");
			
			actual_code = Integer.parseInt(code);
		
			assertEquals(actual_code, 422);
			
		}else if(response.equalsIgnoreCase("null_value_body_response") && response_code.equals(422)) {
			
			code = json_convertor(null_value_body_response, "code");
			
			actual_code = Integer.parseInt(code);
		
			assertEquals(actual_code, 422);
			
		}
		
	}

	@Then("Also validate the error messages as {string} for the required fields")
	public void also_validate_the_error_messages_as_for_the_required_fields(String expected_msg) {
		
		msg = json_convertor(null_value_body_response, "data[0].message");

		assertEquals(msg, expected_msg);
		
	}
	@Then("Also validate the gender error messages as {string}")
	public void also_validate_the_gender_error_messages_as(String expected_msg) {
		
		msg = json_convertor(null_value_body_response, "data[2].message");

		assertEquals(msg, expected_msg);
		
	}

	@Then("Also validate the email error messages as {string}")
	public void also_validate_the_email_error_messages_as(String expected_email_error_msg) {
		
		msg = json_convertor(negative_incorrect_value_response, "[2].message");

		assertEquals(msg, expected_email_error_msg);
		
	}
	
	@Then("Also validate the incorrect datatype response email for error messages as {string}")
	public void also_validate_the_incorrect_datatype_response_email_for_error_messages_as(String expected_email_error_msg) {
		
		msg = json_convertor(negative_incorrect_datatype_response, "[2].message");

		assertEquals(msg, expected_email_error_msg);
		
	}

}
