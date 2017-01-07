package com.java.sravan.WS_RSMessenger.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.java.sravan.WS_RSMessenger.model.Message;

public class RestApiClient {

	public static void main(String[] args) {
		//Gets a client instaance to work with client side requests to service
		Client client=ClientBuilder.newClient();
		//Building a target for request of get and grabbing it in response
		Message message=client.target("http://localhost:8089/WS-RSMessenger/webresouces/messages/1").request(MediaType.APPLICATION_JSON).get(Message.class);
 		//This lets the response object to know that it is expecting Message object
		//Message message=response.readEntity(Message.class);
		System.out.println(message.getMessage());
	}
}

/*
Client client=ClientBuilder.newClient();
WebTarget target=client.target("http://localhost:8089/WS-RSMessenger/webresouces/messages/1");
Builder builder=target.request();
Response response=builder.get();
 */