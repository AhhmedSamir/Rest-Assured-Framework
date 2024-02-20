package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import resources.ApiResources;
import resources.TestData;
import resources.Utilis;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class StepDefination extends Utilis {

	ResponseSpecification resSpec;
	RequestSpecification req;
	Response response;
	TestData data = new TestData();
	 static String place_Id;
	
	
@Given("AddPlace Payload with {string} {string} {string}")
public void add_place_payload_with( String Address , String Language , String Name) throws IOException {
    
	req = given().spec(requestSpecification()).body(data.getAddPlaceApiPayload(Address, Language , Name));	
}


@When("User calls {string} API  with {string} HTTP request")
public void user_calls_api_with_http_request(String resource, String method) {
	ApiResources Resource =  ApiResources.valueOf(resource) ;
	Resource.getResource();
	System.out.println(Resource.getResource());
	
	resSpec =	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	if (method.equalsIgnoreCase("Post")) {
	 response =	req.when().post(Resource.getResource());
	 }
	else if(method.equalsIgnoreCase("get")) {
		 response =	req.when().get(Resource.getResource());
		 }
}
	


@Then("The API call got success with status code {int}")
public void the_api_call_got_success_with_status_code(Integer int1) {
	
	response.then().spec(resSpec).extract().response();

	assertEquals(response.statusCode(),200);
}

@Then("{string} in response body {string}")
public void in_response_body(String key, String expectedValue) {
	
	String actualValue =getJsonPath(response, key);
	assertEquals(actualValue,expectedValue);
}


@Then("Verify Place_Id created maps to {string} using {string}")
public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
  
	   place_Id =  getJsonPath(response, "place_id");
	  req = given().spec(requestSpecification()).queryParam("place_id", place_Id);
	  user_calls_api_with_http_request(resource, "Get");
	  String actualName = getJsonPath(response, "name");
	  assertEquals(expectedName, actualName);
	  
}


@Given("DeletePlace Payload")
public void delete_place_payload() throws IOException {
  req = given().spec(requestSpecification()).body(data.getDeletePlacePayload(place_Id));
}
}


