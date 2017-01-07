package com.java.sravan.WS_RSMessenger.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.java.sravan.WS_RSMessenger.model.Message;

public class RestApiClient3 {

	public static void main(String[] args) {
		Client client=ClientBuilder.newClient();
		//		Generates a target for base or all services of this application
		WebTarget baseTarget=client.target("http://localhost:8089/WS-RSMessenger/webresouces/");
		//	This appends to base and acts as a target to all messages
		WebTarget messageTarget=baseTarget.path("messages");
		//		Gets a target for message id=2
		WebTarget singleMessageTarget= messageTarget.path("{messageId}");
		//		resolves the messageId with specified value=2
		Message message2=singleMessageTarget.resolveTemplate("messageId", "2")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		System.out.println(message2.getMessage());

		//		resolves the messageId with specified value=3

		Message message3=singleMessageTarget.resolveTemplate("messageId", "3")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);



		System.out.println(message3.getMessage());
	}
}

