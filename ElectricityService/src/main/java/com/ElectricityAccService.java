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
public class ElectricityAccService {
	ElectricityAcc account = new ElectricityAcc();

	
	//View electricity account
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String viewAccount() {
		return account.viewAccount();
	}

	//Create electricity account
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createAccount(@FormParam("accName") String accName, 
			@FormParam("accNumber") String accNumber,
			@FormParam("premisesID") String premisesID)

	{
		String output = account.createAccount(accName, accNumber, premisesID);
		return output;
	}

	//Update electricity account
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAccount(String accountData) {
 
		//Convert the input string to a JSON object 
		JsonObject accountObject = new JsonParser().parse(accountData).getAsJsonObject();

		//Read the values from the JSON object
		String accountID = accountObject.get("accountID").getAsString();
		String accName = accountObject.get("accName").getAsString();
		String accNumber = accountObject.get("accNumber").getAsString();
		String premisesID = accountObject.get("premisesID").getAsString();
		String output = account.updateAccount(accountID, accName, accNumber, premisesID);
		return output;
	}

	//Delete electricity account
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAccount(String accountData) {

		//Convert the input string to an XML document
		Document doc = Jsoup.parse(accountData, "", Parser.xmlParser());


		//Read the value from the element <accountID>
		String accountID = doc.select("accountID").text();
		String output = account.deleteAccount(accountID);
		return output;
	}

	
	
//view specific detail
	@GET
	@Path("/getAccountbyID/{accNumber}")
	@Produces(MediaType.TEXT_HTML)
	public String viewSpecificAccount(@PathParam("accNumber") String accNumber) {
		return account.viewSpecificAccount(accNumber);
	}

}
