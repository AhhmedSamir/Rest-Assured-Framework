package resources;

import java.util.ArrayList;
import java.util.List;
import pojoClasses.LocationClass;
import pojoClasses.LocationPojoClass;

public class TestData {

	
	public LocationPojoClass getAddPlaceApiPayload(String Address, String Language , String Name  ) {
		
		LocationPojoClass locationPojo = new LocationPojoClass();

		locationPojo.setAccuracy(50);
		locationPojo.setAddress(Address);
		locationPojo.setLanguage(Language);
		locationPojo.setName(Name);
		locationPojo.setPhoneNumber("(+91) 983 893 3937");
		locationPojo.setWebsite("http://google.com");
		List<String> myLocation = new ArrayList <String>(); 
		myLocation.add("shoe park");
		myLocation.add("shop");
		locationPojo.setTypes(myLocation);
		
		LocationClass locationClass = new LocationClass();
		locationClass.setLat(-38.383494);
		locationClass.setLng(33.427362);
		locationPojo.setLocation(locationClass);
		
		return locationPojo;
	}
	
	public String getDeletePlacePayload(String placeId) {
		
		return "{\r\n    \"place_id\" :  \""+placeId+"\"\r\n}";
		
	}
	
}
