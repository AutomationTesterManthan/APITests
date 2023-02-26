package stepdefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import utils.URI_Endpoints;
import utils.Utils_Class;

public class TC_05_DELETE_Method extends Utils_Class{
	
	static URI_Endpoints Endpoint;
	Scanner user_input =  new Scanner(System.in);
	
	@Given("Users call the base URI for delete request")
	public void users_call_the_base_uri_for_delete_request() throws Exception {

		request = given().
				  spec(requestSpecBuilder()).
				  auth().oauth2(get_property("AccessToken")).
				  contentType(ContentType.JSON);
		
	}

	@When("Use the {string} with DELETE http method")
	public void use_the_with_delete_http_method(String endpoint) {

		Endpoint = URI_Endpoints.valueOf(endpoint);
		
		System.out.println("Enter an ID to delete user: ");
		
		id = user_input.nextInt();
		
		if(endpoint.equalsIgnoreCase("deleteSingleUser")) {
		
			delete_response = request.
					when().
						delete(Endpoint.get_Endpoint() + "/" + id);
			
		}else {
			
			System.out.println("No Endpoint Specified");
			
		}
		
	}

	@Then("Verify the delete single user response with status code {int}")
	public void verify_the_delete_single_user_response_with_status_code(Integer int1) {

		delete_single_user_response = delete_response.
				then().
					statusCode(204).
					extract().
					response();
		
	}

	@Then("Also check the delete response body for message as {string}")
	public void also_check_the_delete_response_body_for_message_as(String expected_message) {

		verify_delete_user_response = delete_response.
				then().
				statusCode(404).
				extract().
				response();
		
		actual_message = json_convertor(verify_delete_user_response,"message");
		
		assertEquals(actual_message, expected_message);
		
	}

}
