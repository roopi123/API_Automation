package API.jira;

import org.junit.Assert;

import Payloads.CoursePaylod;
import Payloads.PlacePayload;
import io.restassured.path.json.JsonPath;

public class Courses {

	public static void main(String[] args)
	{
		JsonPath js=new JsonPath(CoursePaylod.courses());
		//Print No of courses returned by API
	    int courseCount=js.getInt("courses.size()");
	    System.out.println(courseCount);
	    //Print Purchase Amount
	    int purchaseAmount=js.getInt("dashboard.purchaseAmount");
	    System.out.println(purchaseAmount);
	    //Print Title of the first course
	    String Firsttitle=js.getString("courses[0].title");
	    System.out.println(Firsttitle);
	    System.out.println(" abcd");
	    //Print All course titles and their respective Prices
	    for(int i=0;i<courseCount;i++)
	    {
	    	String title=js.getString("courses["+i+"].title");
	    	int price= js.getInt("courses["+i+"].price");
	    	System.out.println(title);
	    	System.out.println(price);
	    }
	   //Print no of copies sold by RPA Course
	    for(int i=0;i<courseCount;i++)
	    {
	    	String title=js.getString("courses["+i+"].title");
	    	if(title.equalsIgnoreCase("RPA"))
	    	{
	    		int price= js.getInt("courses["+i+"].copies");
	    		break;
	    	}
	    }
	    //Verify if Sum of all Course prices matches with Purchase Amount
	    int sum=0;
	    for(int i=0;i<courseCount;i++)
	    {
	    	int title=js.getInt ("courses["+i+"].copies");
	    	int price= js.getInt("courses["+i+"].price");
	    	int total=title*price;
	    	sum=sum+total;
	    	
	    }
         System.out.println(sum);
         Assert.assertEquals(purchaseAmount, sum);
	}

}
