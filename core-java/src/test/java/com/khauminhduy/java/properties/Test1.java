package com.khauminhduy.java.properties;

import org.junit.Test;

import com.google.gson.Gson;

public class Test1 {
	
	@Test
	public void Test123() {
		String s = "{ \"Body\": \"{\"SessionID\":\"8b323aa5-9e34-4fe8-951c-b471bddf4590\",\"OrderID\":\"22090001160031124\",\"PartnerTransactionList\":[{\"PartnerTransactionID\":\"8d067d22-99eb-42ae-b5ff-dce5472a3834\"}]}\", \"ResponseBody\": \"Read timed out\"}";
		Gson gson = new Gson();
		JsonContent jsonContent = gson.fromJson(s, JsonContent.class);
		String body = jsonContent.getBody();
		String responseBody = jsonContent.getResponseBody();
	}

}
