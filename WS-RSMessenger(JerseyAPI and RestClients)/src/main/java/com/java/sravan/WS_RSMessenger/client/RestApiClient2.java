package com.java.sravan.WS_RSMessenger.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.java.sravan.WS_RSMessenger.model.Message;

public class RestApiClient2 {

	public static void main(String[] args) {
		Client client=ClientBuilder.newClient();
		WebTarget baseTarget=client.target("http://localhost:8089/WS-RSMessenger/webresouces/");
		WebTarget messageTarget=baseTarget.path("messages");
		WebTarget singleMessageTarget= messageTarget.path("{messageId}");
		Message message2=singleMessageTarget.resolveTemplate("messageId", "2")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		System.out.println(message2.getMessage());
		Message message3=singleMessageTarget.resolveTemplate("messageId", "3")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		System.out.println(message3.getMessage());


		//		Posting a request in a json format
		Message message=new Message(4, "From jax-rs client", "sravan");
		//	pOST accepts only entity objects...hence, we can wrap our object in an entity
		Response postResponse=messageTarget.request().post(Entity.json(message));
//		unwrapping a response which has an entity
		if(postResponse.getStatus()!=201)
			System.out.println("error occured while craeting");
		Message createdMessage=postResponse.readEntity(Message.class);
		System.out.println(createdMessage.getMessage());
	}
}

