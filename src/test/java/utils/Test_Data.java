package utils;

public class Test_Data {
	
	public String create_new_user_payload() {
		
		return "{\r\n"
				+ "    \"name\":\"Jinga\",\r\n"
				+ "    \"email\":\"jingahagane@cartoonnetwork.com\",\r\n"
				+ "    \"gender\":\"male\",\r\n"
				+ "    \"status\":\"active\"\r\n"
				+ "}";

	}
	
	public String body_with_null_values() {
		
		return "{\r\n"
				+ "		\"name\":\"\",\r\n"
				+ "		\"email\":\"\",\r\n"
				+ "		\"gender\":\"\",\r\n"
				+ "		\"status\":\"\"\r\n"
				+ "}";
		
	}

}
