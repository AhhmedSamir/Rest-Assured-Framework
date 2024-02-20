Feature: Validating Place API

@addPlace
Scenario Outline: Validate that the place is being successfully added using addPlace API
Given AddPlace Payload with "<Address>" "<Language>" "<Name>"
When User calls "AddPlaceApi" API  with "Post" HTTP request
Then The API call got success with status code 200
And "status" in response body "OK"
And "scope" in response body "APP"
And Verify Place_Id created maps to "<Name>" using "GetPlaceApi"
 
 
Examples: 
          |Address| Language |Name|
          |Cairo Center | English | Frontline house|
       |Alex Center  | Spanish | Backline House |
       
       
    @deletePlace      
Scenario: Validate that the place is being successfully deleted using deletePlace API
Given DeletePlace Payload
When User calls "DeletePlaceApi" API  with "Post" HTTP request
Then The API call got success with status code 200
And "status" in response body "OK"
