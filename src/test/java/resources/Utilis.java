package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilis {

	public static RequestSpecification reqSpec ;

	public RequestSpecification requestSpecification() throws IOException {
		
		if (reqSpec == null) {
		
		PrintStream log = new PrintStream(new FileOutputStream("Logs.txt"));
		 reqSpec =	new RequestSpecBuilder().setBaseUri(getPropertieValue("BaseUrl")).
		addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
	return reqSpec;
		} 
		else 
			return reqSpec ;
		
	}
	
	public String getPropertieValue(String value) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream ("C:\\Users\\Ahmed.Abdelhady\\eclipse-workspace\\RestAssuredFramework\\src\\test\\java\\resources\\Global.properties");
		prop.load(fis);
	    String propertieValue =	prop.getProperty(value);
		return propertieValue;
	}
	
	public String getJsonPath(Response response , String Key) {
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.getString(Key);
		
	}
}
