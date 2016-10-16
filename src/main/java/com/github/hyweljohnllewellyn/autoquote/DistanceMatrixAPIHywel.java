package com.github.hyweljohnllewellyn.autoquote;


import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

public class DistanceMatrixAPIHywel {
     String location;
     double meters;
	
	GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCuBfSqomqtdJh3vcVyywllfrY6IQLqEaY");

	public DistanceMatrixAPIHywel(String location) {
		this.location = location;
	}
	
	public DistanceMatrixAPIHywel() {
	
	}
	public void setMeters(double meters) {
		   this.meters = meters;
		}
	
	 public double getMeters() {
		 return meters;
		}
	 
	public void testGetDistanceMatrixWithBasicStringParams(String origins, String destinations) throws Exception {

	    DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
	        .origins(origins)
	        .destinations(destinations)
	        .mode(TravelMode.DRIVING)
	        .units(Unit.IMPERIAL)
	        .await();

	     meters = matrix.rows[0].elements[0].distance.inMeters;
	  }
	
	
	
	}