package com.github.hyweljohnllewellyn.autoquote;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

// http://docs.aws.amazon.com/lambda/latest/dg/java-handler-io-type-pojo.html
public class AutoQuote1 implements RequestHandler<RequestClass, String> {
		
	public String handleRequest(RequestClass request, Context context){

		return request.getQuote();
    }
}