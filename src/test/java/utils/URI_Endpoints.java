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
	postUser_Negative_BodyHasAllFiledsButNoValue("/public-api/users"),
	postUser_Negative_BodyHasAllFiledsButIncorrectValue("/public/v2/users"),
	postUser_Negative_BodyHasAllFieldsButIncorrectDataType("/public/v2/users"),
	
	putUpdateExistingUser_Positive("/public-api/users"),
	putUpdateExistingUser_Negative_TryToUpdateUserWhichIsNotPresent("/public-api/users"),
	putUpdateExistingUser_Negative_SendIncorrectValuesToTheField("/public/v2/users"),
	
	patch_update_gender("/public-api/users"),
	
	deleteSingleUser("/public/v2/users");
	
	private String endpoint;
	
	URI_Endpoints(String endpoint) {
		
		this.endpoint = endpoint;
		
	}
	
	public String get_Endpoint() {
		
		return endpoint;
		
	}

}
