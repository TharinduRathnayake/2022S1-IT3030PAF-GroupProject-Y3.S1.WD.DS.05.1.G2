package com;
import model.ManageService;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Items")
public class Service
{
	ManageService itemObj = new ManageService();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readItems()
 {
	return itemObj.readItems(); 
 }

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertItem(@FormParam("itemCode") String itemCode,
 @FormParam("topic") String topic,
 @FormParam("description") String description,
 @FormParam("date") String date)
{
 String output = itemObj.insertItem(topic,description,date);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateItem(String itemData)
{
//Convert the input string to a JSON object
 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
//Read the values from the JSON object
 String notificationID = itemObject.get("notificationID").getAsString();
 String topic = itemObject.get("topic").getAsString();
 String description = itemObject.get("description").getAsString();
 String date = itemObject.get("date").getAsString();
 
 String output = itemObj.updateItem(notificationID, topic, description, date);
return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(String itemData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String notificationID = doc.select("notificationID").text();
 String output = itemObj.deleteItem(notificationID);
return output;
}

}

