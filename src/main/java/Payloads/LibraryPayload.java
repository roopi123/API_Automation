package Payloads;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LibraryPayload
{
	
public static String AddLibrary(String isbn ,String aisle)
{
	String LibBody="\r\n"
			+ "\r\n"
			+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
			+ "\"isbn\":\""+isbn+"\",\r\n"
			+ "\"aisle\":\""+aisle+"\",\r\n"
			+ "\"author\":\"John foe\"\r\n"
			+ "}\r\n"
			+ "";
	return LibBody;
			
}
@DataProvider(name="librarydetails")
public static  Object[][] libraryData()
{
	Object[][] obj=new Object[][] {{"abc","123"},{"xyz","123"},{"qwe","123"},{"ert","123"}};
	return obj;
}
}
