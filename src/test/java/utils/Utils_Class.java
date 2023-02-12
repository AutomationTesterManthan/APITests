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
