package com.java.sravan.WS_RSMessenger.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvocationDemoClient {
//API to get all messages by year
	public static void main(String[] args){
	InvocationDemoClient idc=new InvocationDemoClient();
	Invocation invocation=idc.prepareRequestForMessagesByYear(2016);
	Response response=invocation.invoke();
	System.out.println(response.getStatus());
	}

private Invocation prepareRequestForMessagesByYear(int year) {
	Client client=ClientBuilder.newClient();
	 return client.target("http://localhost:8089/WS-RSMessenger/webresouces/")
								.path("messages")
								.queryParam("year", year)
								.request(MediaType.APPLICATION_JSON)
								.buildGet();
}
	
}
