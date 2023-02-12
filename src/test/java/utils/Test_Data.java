package utils;

public class Test_Data {
	
	public String create_new_user_payload() {
		
		return "{\r\n"
				+ "    \"name\":\"Sanji\",\r\n"
				+ "    \"email\":\"sanji@onepiece.com\",\r\n"
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
	
	public String body_with_incorrect_values() {
		
		return "{\r\n"
				+ "    \"name\":\"temp\",\r\n"
				+ "    \"email\":\"231426\",\r\n"
				+ "    \"gender\":\"temp1\",\r\n"
				+ "    \"status\":\"live\"\r\n"
				+ "}";
		
	}

}
