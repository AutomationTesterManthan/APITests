package utils;

public enum URI_Endpoints {
	
	getAllUsers("/public-api/users"),
	getAllUsersPagination("/public-api/users"),
	getSingleUser("/public-api/users"),
	getIncorrectUser("/public-api/users"),
	getUsersWithQueryParam_Gender("/public-api/users"),
	getUsersWithMultipleQueryParam_GenderAndStatus("/public-api/users"),
	
	postCreateUser_Positive("/public/v2/users"),
	postUser_Negative_SendEmptyBody("/public-api/users"),
	postUser_Negative_BodyHasAllFiledsButNoValue("/public-api/users");
	
	private String endpoint;
	
	URI_Endpoints(String endpoint) {
		
		this.endpoint = endpoint;
		
	}
	
	public String get_Endpoint() {
		
		return endpoint;
		
	}

}
