package API.jira;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;

import Payloads.PlacePayload;

public class Places {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(PlacePayload.AddPlace()).when().post("/maps/api/place/add/json")
		             .then().log().all().assertThat().statusCode(200).body("status", equalTo("OK")).header("Connection","Keep-Alive").extract().response().asString();
		
	JsonPath jsPOST=new JsonPath(response);
	String placeId = jsPOST.getString("place_id");
	
	                 given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(PlacePayload.UpdatePlace(placeId)).when().put("/maps/api/place/update/json").then().assertThat().statusCode(200);
	
	String address = given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeId)
			         .when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
	         
	JsonPath jsGET=new JsonPath(address);
	String actualAddress=jsGET.getString("address");
	Assert.assertEquals("70 Summer walk, USA", actualAddress);
	
	
	}
	

}
