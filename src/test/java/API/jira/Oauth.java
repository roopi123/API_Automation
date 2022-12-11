package API.jira;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
public class Oauth {
    @Test
	public static void oauth() 
	{
		
		String codeUrl="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AfgeXvsMTGEqkJ9NFcsJDRf4Do1rLxmnlwjr_lndokPzWMqzdfu8UevWqxdk4sNa0zultA&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String code1=codeUrl.split("code=")[1];
		String code2=code1.split("&scope")[0];
		
		String access=given().log().all().queryParams("code",code2).queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php").queryParams("grant_type","authorization_code").urlEncodingEnabled(false)
		.when().post("https://www.googleapis.com/oauth2/v4/token").then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=new JsonPath(access);
		String access_token=js.getString("access_token");
		
		
		String getcourse=given().log().all().queryParam("acces_token", access_token).when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println(getcourse);
	}

}
