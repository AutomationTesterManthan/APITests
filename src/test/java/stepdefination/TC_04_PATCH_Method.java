package stepdefination;

import static io.restassured.RestAssured.given;

import java.util.Scanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import utils.Test_Data;
import utils.URI_Endpoints;
import utils.Utils_Class;

public class TC_04_PATCH_Method extends Utils_Class{
	
	Test_Data data = new Test_Data();
	static URI_Endpoints Endpoint;
	Scanner user_input =  new Scanner(System.in);
	
	@Given("Users call the base URI for patch request")
	public void users_call_the_base_uri_for_patch_request() throws Exception {

		request = given().
				  spec(requestSpecBuilder()).
				  auth().oauth2(get_property("AccessToken")).
				  contentType(ContentType.JSON);
		
	}
	
	@Given("Give the payload for the {string} patch request")
	public void give_the_payload_for_the_patch_request(String string) {

		request_body = request.
				body(data.update_gender());
		
	}
	
	@When("User use the {string} for the http patch method")
	public void user_use_the_for_the_http_patch_method(String endpoint) {
		
		Endpoint = URI_Endpoints.valueOf(endpoint);
		
		System.out.println("Enter an ID where you want to update gender: ");
		
		id = user_input.nextInt();
			
		if(endpoint.equalsIgnoreCase("patch_update_gender")) {
			
			patch_response = request_body.
					when().
						put(Endpoint.get_Endpoint() + "/" + id);
			
		}
		
	}
	
	@Then("Check the updated patch response for status as {int}")
	public void check_the_updated_patch_response_for_status_as(Integer int1) {

		updated_user_gender_response = patch_response.
				then().
					statusCode(200).
					extract().
					response();
		
		expected_gender = json_convertor(updated_user_gender_response,"data.gender");
		
	}
	
	@When("User use the {string} to check updated user gender using http get method")
	public void user_use_the_to_check_updated_user_gender_using_http_get_method(String endpoint) {
		
		System.out.println("Enter an ID to check updated gender: ");
		
		id = user_input.nextInt();

		if(endpoint.equalsIgnoreCase("getSingleUser")) {
			
			get_patched_response = request.
					when().
						get(Endpoint.get_Endpoint() + "/" + id);
			
		}
		
	}

	@Then("Check the patched response {string} for status code as {int}")
	public void check_the_patched_response_for_status_code_as(String string, Integer int1) {

		user_response = get_patched_response.
				then().
				statusCode(200).
				extract().
				response();
		
	}

	@Then("Also check the updated gender in the response")
	public void also_check_the_updated_gender_in_the_response() {

		actual_gender = json_convertor(user_response,"data.gender");
		
	}

}
