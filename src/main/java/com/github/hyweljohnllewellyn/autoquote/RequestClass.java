package com.github.hyweljohnllewellyn.autoquote;

import java.text.DecimalFormat;

import com.google.gson.JsonObject;


//Barbershop Quartet:
//Base rate: £670 for the quartet in London for up to 2 x 30 minute sets within a 3 hour period
//£0.40 x miles from central London per singer for travel expenses
//£110 for each additional 30 minutes of performance 


// http://docs.aws.amazon.com/lambda/latest/dg/java-handler-io-type-pojo.html
public class RequestClass {
    private String destination;
    private String performance;
    private int people;
   
   public void setDestination(String destination) {
	   this.destination = destination;
	}
  
   public void setPerformance(String performance) {
	   this.performance = performance;
	}
   
   public void setPeople(int people) {
	   this.people = people;
	}
   
   public String getDestination() {
		return destination;
	}
  
  public String getPerformance() {
      return performance;
  }
  
  public int getPeople() {
      return people;
  }
    
    public RequestClass(String destination, String performance, int people) {
    	this.destination = destination;
    	this.performance = performance;
    	this.people = people;
    }
    
    public RequestClass() {
    }
    

	public String getQuote() {
		
	    	String quote;
	    	String expenses;
	    	double miles;
	    	double travelcost;
	    	double cost;
	    	int baserate;

	    	String origin = "Leicester Square, London WC2H, UK";
	    	
	    	
	    	final int VICTORIAN_RATE = 480;
	    	final int CAROL_RATE = 440;
	    	final int HV_BARBERSHOP_RATE = 670;
	    	final int HV_CAROL_RATE = 435;
	    	
	    	 if (performance.equals("victoriancarolsingershire")) {
	    		 baserate = VICTORIAN_RATE;
	    	  } else if (performance.equals("carolsingershire")) {
		        	 baserate = CAROL_RATE;
	         } else if (performance.equals("Barbershop Quartet")) {
	        	 baserate = HV_BARBERSHOP_RATE;
	         } else if (performance.equals("Carol Singers")) {
	        	 baserate = HV_CAROL_RATE;
	         } else {
	        	 return "sorry, unable to find performance";
	         }
	   		
	    	// this is where the google distance calculation would be called based on Location
	    	DistanceMatrixAPIHywel distances = new DistanceMatrixAPIHywel();
	    	
	    	try {
	    		distances.testGetDistanceMatrixWithBasicStringParams(origin, destination);
	    	  	double meters = distances.getMeters();
	    	    miles = (meters * 2) / 1609.34; // this is a round trip  - 1609.34 convert from meters to miles
	    	    
	    	  	//£0.40 x miles from central London per singer for travel expenses
		    	double costpermile = 0.20d;  // this is a round trip
		    	DecimalFormat df = new DecimalFormat("#.00"); 
		    	String currency = "\u00a3"; 
		    	travelcost = ((miles * costpermile) * people);

		    	cost = ((miles * costpermile) * people) + baserate;
				
		    	expenses = currency + df.format(travelcost);
		    	quote = currency + df.format(cost);
		    	
		    	DecimalFormat dm = new DecimalFormat("#.0");
		    	
		    	//create Json Object
				  JsonObject json = new JsonObject();
				  
				  //put some values into the JSON object
				  json.addProperty("origin", origin );
				  json.addProperty("destination", getDestination() );
				  json.addProperty("performance", getPerformance() );
			      json.addProperty("people", getPeople() );
			      json.addProperty("quote", quote );
			      json.addProperty("miles", dm.format(miles) );
			      json.addProperty("expenses", expenses );
	    	  	 
		    	return json.toString();
		    	
	    	} catch (Exception e) {
	    	    return "sorry, unable to provide quote";
	    	}
	    }
		
}