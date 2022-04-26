package com;
import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Payments")

public class PaymentService
{
	Payment payObj = new Payment();
	 
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments()
	 {
	 return payObj.readPay();
	 }
	
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("cusID") String cusID,
	 @FormParam("pType") String pType,
	 @FormParam("pAmount") String pAmount)
	 
	{
	 String output = payObj.insertPay(cusID,pType,pAmount);
	 return output;
	}
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	 String pID = paymentObject.get("pID").getAsString();
	 String cusID = paymentObject.get("cusID").getAsString();
	 String pType = paymentObject.get("pType").getAsString();
	 String pAmount = paymentObject.get("pAmount").getAsString();
	 
	 
	 String output = payObj.updatePay(pID, cusID, pType, pAmount);
	 
	 return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String pID = doc.select("pID").text();
	 String output = payObj.deletePay(pID);
	 
	 return output;
	}
}
