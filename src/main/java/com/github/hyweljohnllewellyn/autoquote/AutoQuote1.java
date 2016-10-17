package com.github.hyweljohnllewellyn.autoquote;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
//test
// http://docs.aws.amazon.com/lambda/latest/dg/java-handler-io-type-pojo.html
public class AutoQuote1 implements RequestHandler<RequestClass, String> {
		
	public String handleRequest(RequestClass request, Context context){
			   
/*		       
    	StringBuilder result = new StringBuilder("");
    	result.append("Requested Performance: " + request.getPerformance() );
    	result.append(", Location: " + request.getLocation() );
    	result.append(", People: " + request.getPeople() );
    	result.append(", Base Rate Quote: " + request.getQuote() );
*/
    	// show the result
    	// return result.toString();
		return request.getQuote();
    }
}