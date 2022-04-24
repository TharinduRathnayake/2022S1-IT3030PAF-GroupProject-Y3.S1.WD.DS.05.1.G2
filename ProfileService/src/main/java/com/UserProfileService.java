package com;

import model.UserProfile;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

	@Path("/Users") 
	public class UserProfileService {
		// TODO Auto-generated constructor stub
		
		UserProfile us = new UserProfile();
		
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertItem(@FormParam("CustomerId") String CustomerId, 
		 @FormParam("CustomerfName") String CustomerfName, 
		 @FormParam("CustomerlName") String CustomerlName, 
		 @FormParam("AccountNumber") String AccountNumber,
		 @FormParam("CustomerNIC") String CustomerNIC,
		 @FormParam("CustomerEmail") String CustomerEmail,
		 @FormParam("CustomerPhone") String CustomerPhone
		 ) 
		{ 
		 String output=us.insertUser(CustomerId, CustomerfName, CustomerlName, AccountNumber, CustomerNIC, CustomerEmail, CustomerPhone);
		 return output;
				 
		}

		
		 @GET
		 @Path("/") 
		 @Produces(MediaType.TEXT_HTML) 
		 public String readUser() 
		  { 
		  return us.readUser();
		 }
		 
		 @PUT
		 @Path("/") 
		 @Consumes(MediaType.APPLICATION_JSON) 
		 @Produces(MediaType.TEXT_PLAIN) 
		 public String UpdateUser(String userData) 
		 { 
		 //Convert the input string to a JSON object 
		  JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
		 //Read the values from the JSON object
		  String CustomerId = userObject.get("CustomerId").getAsString(); 
		  String CustomerfName = userObject.get("CustomerfName").getAsString(); 
		  String CustomerlName = userObject.get("CustomerlName").getAsString(); 
		  String AccountNumber = userObject.get("AccountNumber").getAsString(); 
		  String CustomerNIC = userObject.get("CustomerNIC").getAsString();
		  String CustomerEmail = userObject.get("CustomerEmail").getAsString();
		  String CustomerPhone = userObject.get("CustomerPhone").getAsString();
		  
		 String output=us.UpdateUser(CustomerId, CustomerfName, CustomerlName, AccountNumber, CustomerNIC, CustomerEmail, CustomerPhone);
		 return output;
		 }
		 
		 @DELETE
		 @Path("/") 
		 @Consumes(MediaType.APPLICATION_XML) 
		 @Produces(MediaType.TEXT_PLAIN) 
		 public String deleteUser(String userData) 
		 { 
		 //Convert the input string to an XML document
		  Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
		  
		 //Read the value from the element <itemID>
		  String CustomerId = doc.select("CustomerId").text(); 
		  String output = us.deleteUser(CustomerId); 
		 return output; 
		 }
		//view specific user account
			@GET
			@Path("/getAccountbyID/{CustomerId}")
			@Produces(MediaType.TEXT_HTML)
			public String readSpecificUser(@PathParam("CustomerId") String CustomerId)
			 {
			 return us.readSpecificUser(CustomerId);
			}


	}


