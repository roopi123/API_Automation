package API.jira;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import Payloads.LibraryPayload;
import Payloads.PlacePayload;

public class Library {
@Test(dataProvider="librarydetails", dataProviderClass=LibraryPayload.class) 
public static void AddLibrary(String isbn ,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		given().log().all().header("Content-Type","application/json").body(LibraryPayload.AddLibrary(isbn, aisle))
		.when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200);

	}
@Test
 public static void PlaceFromFile() throws IOException
 {
	RestAssured.baseURI="https://rahulshettyacademy.com";
	//convert content of file to Byte and convert Byte daye into String
	 // First get the file and convert to bites and then convert to String
	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\roope\\Desktop\\API\\new 1.json")))) //
	.when().post("/maps/api/place/add/json")
    .then().log().all().assertThat().statusCode(200).body("status", equalTo("OK")).header("Connection","Keep-Alive");
 }

}
