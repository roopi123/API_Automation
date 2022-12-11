package API.jira;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import Payloads.JiraPayloads;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraIssue {
	
	@Test
	public static void createIssue()
	{   //Session Creation
		SessionFilter session = new SessionFilter();
		RestAssured.baseURI="http://localhost:8080/";
		 given().header("Content-Type","application/json").log().all().body(JiraPayloads.sessionPayload()).filter(session)
		 .when().post("/rest/auth/1/session").then().log().all().assertThat().statusCode(200);
		
		//creating issue
		String issueResponse=given().log().all().header("Content-Type","application/json").body(JiraPayloads.createIssue()).filter(session)
		                     .when().post("rest/api/2/issue").then().log().all().extract().response().asString();
		JsonPath js=new JsonPath(issueResponse);
		String issue_id =js.getString("id");
		
	    //Adding comment
		String commentDetails=given().pathParam("issueid", issue_id).header("Content-Type","application/json").log().all().body(JiraPayloads.addComment()).filter(session)
		                      .when().post("rest/api/2/issue/{issueid}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js1=new JsonPath(commentDetails);
		System.out.println(js1.getString("body"));
		String comment_id=js1.getString("id");
		System.out.println(comment_id);
		System.out.println(issue_id);
		
		
		//Updating comment
		String commentUpdate=given().pathParam("issueid", issue_id).pathParam("commentid", comment_id).header("Content-Type","application/json").log().all().body(JiraPayloads.updateComment()).filter(session)
				        .when().put("rest/api/2/issue/{issueid}/comment/{commentid}").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		//Attaching file to issue
		given().pathParam("issueid", issue_id).header("X-Atlassian-Token","no-check").log().all().header("Content-Type","multipart/form-data").multiPart("file",new File("C:\\Users\\roope\\eclipse-workspace\\firefox\\API\\src\\test\\java\\API\\jira\\Courses.java")).filter(session)
        .when().post("rest/api/2/issue/{issueid}/attachments").then().log().all().assertThat().statusCode(200);
		
		// Get Issue
		given().pathParam("issueid", issue_id).log().all().queryParam("field", "author").filter(session)
	    .when().get("rest/api/2/issue/{issueid}").then().log().all().assertThat().statusCode(200);
		
		//Deleting issue
		given().pathParam("issueid", issue_id).log().all().filter(session)
        .when().delete("rest/api/2/issue/{issueid}").then().log().all().assertThat().statusCode(204).extract().response().asString();

		
	}

}
