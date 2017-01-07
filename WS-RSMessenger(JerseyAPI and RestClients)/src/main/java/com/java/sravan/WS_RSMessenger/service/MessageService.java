package com.java.sravan.WS_RSMessenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.java.sravan.WS_RSMessenger.database.Database;
import com.java.sravan.WS_RSMessenger.exception.DataNotFoundException;
import com.java.sravan.WS_RSMessenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages=Database.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1, "Hello Sravan", "Sravan"));
		messages.put(2L, new Message(2, "Hello Srujan", "Srujan"));

	}
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(	messages.values());
	}
//	Excption implementation if no message assciaed with that id in our db
	public Message getMessage(long id){
		Message message=messages.get(id);
		if(message==null)
			throw new DataNotFoundException("message with id"+id+"is not found");
		return message;
	}


	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear=new ArrayList<Message>();
		//		Usually getInstance() method will be used to get instances where the class is a static or factory class.
		Calendar cal=Calendar.getInstance();
		for(Message message:messagesForYear){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year)
				messagesForYear.add(message);
		}
		return messagesForYear;

	}
	public List<Message> getAllMessagesPaginated(int start, int size){
		List<Message> subList=new ArrayList<Message>();
		if(start+size>subList.size())
			return null;
		return subList.subList(start, start+size);
	}

	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	public Message updateMessage(Message message){
		if(message.getId()<=0)
			return null;
		return messages.put(message.getId(), message);
	}
	public Message deleteeMessage(long id){

		return messages.remove(id);
	}
}
