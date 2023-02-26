package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils_Class {
	
	/******** Request Variables ********/
	public RequestSpecification request;
	public RequestSpecification request_body;
	
	public RequestSpecification null_values_body;
	public RequestSpecification incorrect_values_body;
	public RequestSpecification incorrect_datatype_body;
	/************************************/
	
	/******** GET Method Response Variable ********/
	public Response response;
	public Response query_response;
	public Response gender_query_response;
	public Response single_user_response;
	public Response incorrect_user_response;
	public Response get_all_users_response;
	public Response get_users_with_page_no_response;
	public Response get_single_user;
	public Response get_incorrect_user;
	public Response get_users_with_gender;
	public Response muliple_query_param_response;
	public Response get_users_muliple_query_param;
	/***********************************************/
	
	/******** POST Method Response Variable ********/
	public Response post_response;
	public Response create_new_user_positive_response;
	public Response negative_response;
	public Response negative_empty_body_response;
	public Response null_values_response;
	public Response null_value_body_response;
	public Response incorrect_values_response;
	public Response negative_incorrect_value_response;
	public Response incorrect_datatype_response;
	public Response negative_incorrect_datatype_response;
	/***********************************************/
	
	/******** PUT Method Response Variable ********/
	public Response put_response;
	public Response updated_user_response;
	public Response verify_single_user_response;
	public Response user_response;
	public Response updated_user_with_incorrect_id_response;
	public Response updated_user_with_incorrect_values_response;
	/**********************************************/
	
	/******** PATCH Method Response Variable ********/
	public Response patch_response;
	public Response updated_user_gender_response;
	public Response get_patched_response;
	/************************************************/
	
	/******** DELETE Method Response Variable ********/
	public Response delete_response;
	public Response delete_single_user_response;
	public Response verify_delete_user_response;
	/*************************************************/
	
	/********* Variables *********/
	public String code;
	public String msg;
	public String gender;
	public String multiple_query_gender;
	public String multiple_query_status;
	public String status;
	public String gender_validation;
	public String status_validation;
	public String expected_id;
	public String actual_page;
	public String actual_message;
	public String actual_email;
	public String expected_email;
	public String expected_gender;
	public String actual_gender;
	
	public int actual_code;
	public int page_no;
	public int id;
	/******************************/
	
	RequestSpecification requestSpecBuilder;
	PrintStream log;
	Properties property;
	FileInputStream file;
	JsonPath validate;
	String response_body;
	
	public RequestSpecification requestSpecBuilder() throws Exception {
		
		if(requestSpecBuilder == null) {
		
			log = new PrintStream(new FileOutputStream("Output-Logs.txt"));
			
				requestSpecBuilder = new RequestSpecBuilder().
					setBaseUri(get_property("BaseURI")).
					addFilter(RequestLoggingFilter.logRequestTo(log)).
					addFilter(ResponseLoggingFilter.logResponseTo(log)).
					build();
			
				return requestSpecBuilder;
			
		}
		
		return requestSpecBuilder;
		
	}
	
	public String get_property(String key) throws Exception {
		
		property = new Properties();
		
		file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/baseURI.properties");
		
		property.load(file);
		
		return property.getProperty(key);
		
	}
	
	public String json_convertor(Response response, String key) {
		
		response_body = response.asString();
		
		validate = new JsonPath(response_body);
		
		return validate.get(key).toString();
		
	}

}
