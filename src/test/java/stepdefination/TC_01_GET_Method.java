package stepdefination;

import static io.restassured.RestAssured.*;
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

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

public class TC_01_GET_Method extends Utils_Class{
	
	RequestSpecification request;
	RequestSpecification request_body;
	RequestSpecification null_values_body;
	static URI_Endpoints Endpoint;
	Scanner user_input =  new Scanner(System.in);
	Test_Data data = new Test_Data();
	Response response;
	Response query_response;
	Response gender_query_response;
	Response single_user_response;
	Response incorrect_user_response;
	Response get_all_users_response;
	Response get_users_with_page_no_response;
	Response get_single_user;
	Response get_incorrect_user;
	Response get_users_with_gender;
	Response muliple_query_param_response;
	Response get_users_muliple_query_param;
	Response post_response;
	Response create_new_user_positive_response;
	Response negative_response;
	Response negative_empty_body_response;
	Response null_values_response;
	Response null_value_body_response;
	JsonPath js;
	String code;
	String gender;
	String multiple_query_gender;
	String multiple_query_status;
	String status;
	String gender_validation;
	String status_validation;
	int actual_code;
	int page_no;
	int id;
	String expected_id;
	String actual_page;
	String actual_message;
	
	@Given("Users call the base URI")
	public void users_call_the_base_uri() throws Exception {
		
		request = given().
					  spec(requestSpecBuilder()).
					  auth().oauth2(get_property("AccessToken")).
					  contentType(ContentType.JSON);
		
	}

	@When("Use the {string} with {string} http method")
	public void use_the_with_http_method(String endpoint, String http_method) {
		
		Endpoint = URI_Endpoints.valueOf(endpoint);
		
		if(endpoint.equalsIgnoreCase("getAllUsers")) {
			
			response = request.
					when().
						get(Endpoint.get_Endpoint());
			
		}else {
			
			System.out.println("No Endpoint Specified");
			
		}
		
	}

	@Then("Verify the get all users response with status code {int}")
	public void verify_the_get_all_users_response_with_status_code(Integer int1) {
		
		get_all_users_response = response.
				then().
				    statusCode(200).
				    extract().
				    response();
		
	}

	@Then("Also check the response body {string} as {int}")
	public void also_check_the_response_body_as(String string, Integer expected_code) {
		
		code = json_convertor(get_all_users_response, "code");
		
		actual_code = Integer.parseInt(code);
		
		assertEquals(actual_code, 200);
		
	}
	
	@When("Users use the {string} endpoint and Enter a page no. in the query parameter")
	public void users_use_the_endpoint_and_enter_a_page_no_in_the_query_parameter(String endpoint) {
		
		System.out.println("Enter a page no. to search for a specific page: ");
		
		page_no = user_input.nextInt();
		
		if(endpoint.equalsIgnoreCase("getAllUsersPagination")) {
			
			query_response = request.
					    queryParam("page", page_no).
					when().
						get(Endpoint.get_Endpoint());
			
		}else {
			
			System.out.println("No Endpoint Specified");
			
		}
		
	}
	
	@Then("Verify the get all users with page no. response with status code {int}")
	public void verify_the_get_all_users_with_page_no_response_with_status_code(Integer int1) {
		
		get_users_with_page_no_response = query_response.
				then().
				    statusCode(200).
				    extract().
				    response();
		
	}

	@Then("Also check the response for page no.")
	public void also_check_the_response_for_page_no() {
		
		actual_page = json_convertor(get_users_with_page_no_response,"meta.pagination.page");
		
		assertEquals(Integer.parseInt(actual_page), page_no);
		
	}

	@When("Users use the {string} endpoint with enter an id")
	public void users_use_the_endpoint_with_enter_an_id(String endpoint) {
		
		System.out.println("Enter an ID: ");
		
		id = user_input.nextInt();
		
		if(endpoint.equalsIgnoreCase("getSingleUser")) {			
		    single_user_response = request.
		    		when().
		    		    get(Endpoint.get_Endpoint() + "/" + id);
		    
		}else if(endpoint.equalsIgnoreCase("getIncorrectUser")) {
			
			incorrect_user_response = request.
		    		when().
	    		        get(Endpoint.get_Endpoint() + "/" + id);
			
		}else {
			
			System.out.println("No endpoint found!");
			
		}
		
	}
	
	@Then("Verify the get single user response with status code {int}")
	public void verify_the_get_single_user_response_with_status_code(Integer int1) {
		
		get_single_user = single_user_response.
				then().
				    statusCode(200).
				    extract().
				    response();
		
		
	}
	
	@Then("Also check the response for id and code {int}")
	public void also_check_the_response_for_id_and_code(Integer int1) {
		
        code = json_convertor(get_single_user, "code");
		
		actual_code = Integer.parseInt(code);
		
		assertEquals(actual_code, 200);
		
		expected_id = json_convertor(get_single_user, "data.id");
		
		assertEquals(id, Integer.parseInt(expected_id));
		
	}

	@Then("Verify the get incorrect user response with status code {int}")
	public void verify_the_get_incorrect_user_response_with_status_code(Integer int1) {
		
		get_incorrect_user = incorrect_user_response.
				then().
				    statusCode(200).
				    extract().
				    response();
		
	}

	@Then("Check the response for code {int} and message as {string}")
	public void check_the_response_for_code_and_message_as(Integer int1, String expected_message) {
		
        code = json_convertor(get_incorrect_user, "code");
		
		actual_code = Integer.parseInt(code);
		
		assertEquals(actual_code, 404);
		
		actual_message = json_convertor(get_incorrect_user, "data.message");
		
		assertEquals(actual_message, expected_message);
		
	}
	
	@When("Users use the {string} endpoint and Enter a gender in the query parameter")
	public void users_use_the_endpoint_and_enter_a_gender_in_the_query_parameter(String endpoint) {
		
		System.out.println("Enter a gender to search for a specific page: ");
		
		gender = user_input.nextLine();
		
		if(endpoint.equalsIgnoreCase("getUsersWithQueryParam_Gender")) {
			
			gender_query_response = request.
					    queryParam("gender", gender).
					when().
						get(Endpoint.get_Endpoint());
			
		}else {
			
			System.out.println("No Endpoint Specified");
			
		}
		
	}

	@Then("Verify the get users with specified gender response with status code {int}")
	public void verify_the_get_users_with_specified_gender_response_with_status_code(Integer int1) {
		
		get_users_with_gender = gender_query_response.
				then().
				   statusCode(200).
				   extract().
				   response();
		
	}

	@Then("Also check the response for specified gender")
	public void also_check_the_response_for_specified_gender() {
		
        code = json_convertor(get_users_with_gender, "code");
		
		actual_code = Integer.parseInt(code);
		
		assertEquals(actual_code, 200);
		
		gender_validation = json_convertor(gender_query_response, "data.gender");
		
		if(gender_validation.contains("female")) {
			
			assertEquals(gender_validation.contains("female"), true, "Response body contains female");
			
		}else if(gender_validation.contains("male")) {
			
			assertEquals(gender_validation.contains("male"), true, "Response body contains male");
			
		}else {
			
			System.out.println("No match found!");
			
		}
		
	}
	
	@When("Users use the {string} endpoint and Enter multiple in the query parameter")
	public void users_use_the_endpoint_and_enter_multiple_in_the_query_parameter(String endpoint) {
		
        System.out.println("Enter a gender to search for a specific page: ");
		
		multiple_query_gender = user_input.nextLine();
		
		System.out.println("Enter the status (active or inactive): ");
		
		multiple_query_status = user_input.nextLine();
		
		if(endpoint.equalsIgnoreCase("getUsersWithMultipleQueryParam_GenderAndStatus")) {
			
			muliple_query_param_response = request.
					    queryParam("gender", multiple_query_gender).
					    queryParam("status",multiple_query_status).
					when().
					    get(Endpoint.get_Endpoint());
			
		}else {
			
			System.out.println("No Endpoint Specified");
			
		}
		
	}

	@Then("Verify the get users with multiple Query Parameter response with status code {int}")
	public void verify_the_get_users_with_multiple_query_parameter_response_with_status_code(Integer int1) {
		
		get_users_muliple_query_param = muliple_query_param_response.
				then().
				    statusCode(200).
				    extract().
				    response();
		
	}

	@Then("Also check the response for gender and their status")
	public void also_check_the_response_for_gender_and_their_status() {
		
		gender_validation = json_convertor(get_users_muliple_query_param, "data.gender");
		
        if(gender_validation.contains("female")) {
			
			assertEquals(gender_validation.contains("female"), true, "Response body contains female");
			
		}else if(gender_validation.contains("male")) {
			
			assertEquals(gender_validation.contains("male"), true, "Response body contains male");
			
		}else {
			
			System.out.println("No match found!");
			
		}
        
        status_validation = json_convertor(get_users_muliple_query_param, "data.status");
        
        if(status_validation.contains("active")) {
			
			assertEquals(status_validation.contains("active"), true, "Response body contains active");
			
		}else if(status_validation.contains("inactive")) {
			
			assertEquals(status_validation.contains("inactive"), true, "Response body contains inactive");
			
		}else {
			
			System.out.println("No match found!");
			
		}
		
	}

	@Given("Give the payload for the {string} post request")
	public void give_the_payload_for_the_post_request(String request_Body) {
		if(request_Body.equalsIgnoreCase("create_new_user_payload")) {
			
			request_body = request.
						body(data.create_new_user_payload());
		
		}else if(request_Body.equalsIgnoreCase("body_with_null_values")) {
			
			null_values_body = request.
						body(data.body_with_null_values());
			
		}else {
			
			System.out.println("No Match Found!");
			
		}
		
	}
	
	@When("Use the {string} for the post http method")
	public void use_the_for_the_http_method(String endpoint) {
		
		if(endpoint.equalsIgnoreCase("postCreateUser_Positive")) {
			
			post_response = request_body.
					when().
						post(Endpoint.get_Endpoint());
			
		}else {
			
			System.out.println("No Match found!");
			
		}
		
	}
	
	@Then("Check the response status as {int}")
	public void check_the_response_status_as(Integer int1) {
		
		create_new_user_positive_response = post_response.
				then().
					statusCode(200).
					extract().
					response();
		
	}
	
	@Then("Also check the response for code as {int}")
	public void also_check_the_response_for_code_as(Integer response_code) {
		
		if(response_code.equals(201)) {
			
			code = json_convertor(create_new_user_positive_response, "code");
			
			actual_code = Integer.parseInt(code);
		
			assertEquals(actual_code, 201);
			
		}else if(response_code.equals(422)){
			
			code = json_convertor(negative_empty_body_response, "code");
			
			actual_code = Integer.parseInt(code);
		
			assertEquals(actual_code, 422);
			
		}
		
	}
	
	@When("User use the {string} for the http post method")
	public void user_use_the_for_the_http_post_method(String endpoint) {
		
		if(endpoint.equalsIgnoreCase("postUser_Negative_SendEmptyBody")) {
			
			negative_response = request.
					when().
						post(Endpoint.get_Endpoint());
			
		}else if(endpoint.equalsIgnoreCase("postUser_Negative_BodyHasAllFiledsButNoValue")){
			
			null_values_response = null_values_body.
					when().
						post(Endpoint.get_Endpoint());
			
		}
	}

	@Then("check the response status as {int} for negative scenario")
	public void check_the_response_status_as_for_negative_scenario(Integer int1) {
		
		negative_empty_body_response = negative_response.
				then().
					statusCode(200).
					extract().
					response();
		
	}
	
	@Then("check the response status as {int} for negative scenario of null values in the body")
	public void check_the_response_status_as_for_negative_scenario_of_null_values_in_the_body(Integer int1) {
		
		null_value_body_response = null_values_response.
				then().
					statusCode(200).
					extract().
					response();
	}

	@Then("Also check the null values in the body response for code as {int}")
	public void also_check_the_null_values_in_the_body_response_for_code_as(Integer int1) {
		
		code = json_convertor(null_value_body_response, "code");
		
		actual_code = Integer.parseInt(code);
	
		assertEquals(actual_code, 422);
		
	}

	@Then("Also validate the error messages as {string} for the required fields")
	public void also_validate_the_error_messages_as_for_the_required_fields(String string) {
		
		System.out.println("Pending...");
		
	}
	@Then("Also validate the gender error messages as {string}")
	public void also_validate_the_gender_error_messages_as(String string) {
		
		System.out.println("Pending...");
		
	}




}
