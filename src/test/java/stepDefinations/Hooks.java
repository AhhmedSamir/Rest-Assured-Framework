package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deletePlace")
	public void BeforeDeletePlaceApi() throws IOException {
		
		//execute this code only when place id is null
		
		
		//this code is used to generate place id
		StepDefination S = new StepDefination();
		
		if(StepDefination.place_Id==null) {
		S.add_place_payload_with("Nasr City", "Arabic", "Test");
		S.user_calls_api_with_http_request("AddPlaceApi", "Post");
		S.verify_place_id_created_maps_to_using("Test", "GetPlaceApi");
		}
	}
	

}
