package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Power;


@Path("/Power")
public class PowerService {
	Power powerObj = new Power(); 

	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readPower() 
	{ 
		return powerObj.readPower(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertPower(@FormParam("locationName") String locationName, 
			@FormParam("relatedZone") String relatedZone, 
			@FormParam("timePeriod") String timePeriod) 
	{ 
	
		String output = powerObj.insertPower(locationName, relatedZone, timePeriod); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatePower(String powerData) 
	{ 
	//Convert the input string to a JSON object 
		JsonObject powerObject = new JsonParser().parse(powerData).getAsJsonObject(); 
	//Read the values from the JSON object
	 	String scheduleID = powerObject.get("scheduleID").getAsString(); 
	 	String locationName = powerObject.get("locationName").getAsString(); 
	 	String relatedZone = powerObject.get("relatedZone").getAsString(); 
	 	String timePeriod = powerObject.get("timePeriod").getAsString(); 
	 	
	 	String output = powerObj.updatePower(scheduleID, locationName, relatedZone, timePeriod); 
	
	 	return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePower(String powerData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(powerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String scheduleID = doc.select("scheduleID").text(); 
	 String output = powerObj.deletePower(scheduleID); 
	return output; 
	}
}
