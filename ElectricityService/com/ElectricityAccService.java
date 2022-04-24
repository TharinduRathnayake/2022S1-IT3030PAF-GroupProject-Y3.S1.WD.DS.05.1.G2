package com; 
import model.ElectricityAcc; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Account") 
public class ElectricityAccService 
{ 
	ElectricityAcc itemObj = new ElectricityAcc(); 
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String viewAccount() 
 { 
	return itemObj.viewAccount(); 
 } 

@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String addAccount(@FormParam("accName") String accName, 
 @FormParam("accNumber") String accNumber, 
 @FormParam("premisesID") String premisesID) 
  
{ 
 String output = itemObj.addAccount(accName, accNumber, premisesID); 
return output; 
}

@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateAccount(String itemData) 
{ 
//Convert the input string to a JSON object 
 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
//Read the values from the JSON object
 String accountID = itemObject.get("accountID").getAsString(); 
 String accName = itemObject.get("accName").getAsString(); 
 String accNumber = itemObject.get("accNumber").getAsString(); 
 String premisesID = itemObject.get("premisesID").getAsString();  
 String output = itemObj.updateAccount(accountID, accName, accNumber, premisesID); 
return output; 
}
@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteAccount(String itemData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
 
//Read the value from the element <accountID>
 String accountID = doc.select("accountID").text(); 
 String output = itemObj.deleteAccount(accountID); 
return output; 
}

//view specific account
		@GET
		@Path("/getAccountbyID/{accNumber}")
		@Produces(MediaType.TEXT_HTML)
		public String viewSpecificAccount(@PathParam("accNumber") String accNumber)
		 {
		 return itemObj.viewSpecificAccount(accNumber);
		}

}
