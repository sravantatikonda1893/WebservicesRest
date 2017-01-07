package com.java.sravan.WS_RSMessenger.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.java.sravan.WS_RSMessenger.model.Message;

public class GenericDemo {

	public static void main(String[] args) {
		Client client=ClientBuilder.newClient();
		//		If we want to get a generic type which is here a list of messages we shoulld use a variant of get() method
		List<Message> messages=client.target("http://localhost:8089/WS-RSMessenger/webresouces/")
				.path("messages")
				.queryParam("year", 2015)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Message>>(){});
		System.out.println(messages);
	}

}
