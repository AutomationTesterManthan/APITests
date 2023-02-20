package stepdefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Test_Data;
import utils.URI_Endpoints;
import utils.Utils_Class;

public class TC_03_PUT_Method extends Utils_Class{
	
	Test_Data data = new Test_Data();
	static URI_Endpoints Endpoint;
	Scanner user_input =  new Scanner(System.in);
	Response put_response;
	Response updated_user_response;
	Response verify_single_user_response;
	Response user_response;
	String actual_email;
	String expected_email;
	
	int id;
	
	@Given("Users call the base URI for put request")
	public void users_call_the_base_uri_for_put_request() throws Exception {
		
		request = given().
				  spec(requestSpecBuilder()).
				  auth().oauth2(get_property("AccessToken")).
				  contentType(ContentType.JSON);
		
	}

	@Given("Give the payload for the {string} put request")
	public void give_the_payload_for_the_put_request(String request_Body) {
		
		if(request_Body.equalsIgnoreCase("update_user_data")) {
		
			request_body = request.
					body(data.update_user_data());
			
		}
		
	}

	@When("User use the {string} for the http put method")
	public void user_use_the_for_the_http_put_method(String endpoint) {
		
		Endpoint = URI_Endpoints.valueOf(endpoint);
		
		System.out.println("Enter an ID where you want to update profile: ");
		
		id = user_input.nextInt();
		
		if(endpoint.equalsIgnoreCase("putUpdateExistingUser_Positive")) {
			
			put_response = request_body.
					when().
						put(Endpoint.get_Endpoint() + "/" + id);
			
		}
		
	}
	
	@Then("Check the response for put response {string} for status as {int}")
	public void check_the_response_for_put_response_status_as(String response_body, Integer int1) {
		
		if(response_body.equalsIgnoreCase("updated_user_response")) {
		
			updated_user_response = put_response.
					then().
						statusCode(200).
						extract().
						response();
			
		}
		
		expected_email = json_convertor(updated_user_response,"data.email");
		
	}
	
	@When("User use the {string} to check updated user using http get method")
	public void user_use_the_to_check_updated_user_using_http_get_method(String endpoint) {
		
		if(endpoint.equalsIgnoreCase("getSingleUser")) {
			
			verify_single_user_response = request.
		    		when().
		    		    get(Endpoint.get_Endpoint() + "/" + id);
			
		}else {
			
			System.out.println("No Endpoint found!");
			
		}
		
	}

	@Then("Check the response {string} for status code as {int}")
	public void check_the_response_for_status_code_as(String string, Integer int1) {
		
		user_response = verify_single_user_response.
				then().
					statusCode(200).
					extract().
					response();
		
	}

	@Then("Also check the email in the response for updated changes")
	public void also_check_the_email_in_the_response_for_updated_changes() {
		
		actual_email = json_convertor(user_response,"data.email");
		
		assertEquals(actual_email, expected_email);
		
	}

}
