package com; 
import model.Inquiry; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Inquiry") 

public class InquiryService 
	{ 
		Inquiry inquiryObj = new Inquiry(); 

	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readInquiry() 
	{ 
		return inquiryObj.readInquiry(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertInquiry(@FormParam("userID") String userID, 
			@FormParam("inquirySubject") String inquirySubject, 
			@FormParam("inquiryDate") String inquiryDate, 
			@FormParam("inquiryDesc") String inquiryDesc) 
	{ 
	
		String output = inquiryObj.insertInquiry(userID, inquirySubject, inquiryDate, inquiryDesc); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateInquiry(String inquiryData) 
	{ 
	//Convert the input string to a JSON object 
		JsonObject inquiryObject = new JsonParser().parse(inquiryData).getAsJsonObject(); 
	//Read the values from the JSON object
	 	String inquiryID = inquiryObject.get("inquiryID").getAsString(); 
	 	String userID = inquiryObject.get("userID").getAsString(); 
	 	String inquirySubject = inquiryObject.get("inquirySubject").getAsString(); 
	 	String inquiryDate = inquiryObject.get("inquiryDate").getAsString(); 
	 	String inquiryDesc = inquiryObject.get("inquiryDesc").getAsString(); 
	 	
	 	String output = inquiryObj.updateInquiry(inquiryID, userID, inquirySubject, inquiryDate, inquiryDesc); 
	
	 	return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteInquiry(String inquiryData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String inquiryID = doc.select("inquiryID").text(); 
	 String output = inquiryObj.deleteInquiry(inquiryID); 
	return output; 
	}

}