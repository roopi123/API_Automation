package Payloads;

public class JiraPayloads {
	
	public static String sessionPayload()
	{
		String session="{ \"username\": \"roopesh\", \"password\": \"roopesh\" }";
		return session;
	}
   public static String createIssue()
   {
	   return "{\r\n"
	   		+ "    \"fields\": {\r\n"
	   		+ "       \"project\":\r\n"
	   		+ "       {\r\n"
	   		+ "          \"key\": \"AUT\"\r\n"
	   		+ "       },\r\n"
	   		+ "       \"summary\": \"REST ye merry gentlemen.\",\r\n"
	   		+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n"
	   		+ "       \"issuetype\": {\r\n"
	   		+ "          \"name\": \"Task\"\r\n"
	   		+ "       }\r\n"
	   		+ "   }\r\n"
	   		+ "}";
   }
   public static String addComment()
   {
	   return "{\r\n"
	   		+ "    \"body\": \"Adding comment.\",\r\n"
	   		+ "    \"visibility\": {\r\n"
	   		+ "        \"type\": \"role\",\r\n"
	   		+ "        \"value\": \"Administrators\"\r\n"
	   		+ "    }\r\n"
	   		+ "}";
   }
   public static String updateComment()
   {
	   return "{\r\n"
	   		+ "    \"body\": \" Updating comment.\",\r\n"
	   		+ "    \"visibility\": {\r\n"
	   		+ "        \"type\": \"role\",\r\n"
	   		+ "        \"value\": \"Administrators\"\r\n"
	   		+ "    }\r\n"
	   		+ "}";
   }
}
