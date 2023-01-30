package utils;

public enum URI_Endpoints {
	
	getAllUsers("/public-api/users"),
	getAllUsersPagination("/public-api/users"),
	getSingleUser("/public-api/users"),
	getIncorrectUser("/public-api/users"),
	getUsersWithQueryParam_Gender("/public-api/users"),
	getUsersWithMultipleQueryParam_GenderAndStatus("/public-api/users");
	
	private String endpoint;
	
	URI_Endpoints(String endpoint) {
		
		this.endpoint = endpoint;
		
	}
	
	public String get_Endpoint() {
		
		return endpoint;
		
	}

}
