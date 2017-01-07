package com.java.sravan.WS_RSMessenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.java.sravan.WS_RSMessenger.database.Database;
import com.java.sravan.WS_RSMessenger.model.Comment;
import com.java.sravan.WS_RSMessenger.model.ErrorMessage;
import com.java.sravan.WS_RSMessenger.model.Message;

public class CommentService {

	private Map<Long, Message> messages=Database.getMessages();

	public CommentService() {
		messages.put(1L, new Message(1, "Hello Sravan", "Sravan"));
		messages.put(2L, new Message(2, "Hello Srujan", "Srujan"));

	}
	public List<Comment> getAllComments(long id){
		Map<Long,Comment> comments=	messages.get(id).getComments();
		return new ArrayList<Comment>(comments.values());
	}

	//There are some Exceptions jersey already mapped for us. We just need to throw it
	public Comment getComment(long messageId,long commentId){
		ErrorMessage errmsg=new ErrorMessage("not found", 404, "www.docdesc.org");
		Response response=Response.status(Status.NOT_FOUND).entity(errmsg).build();
		
		Message message=messages.get(messageId);
		if(message==null){
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments =messages.get(messageId).getComments();
		Comment comment=comments.get(commentId);
		if(comment==null)
			throw new WebApplicationException(response);
		return comment;
	}
	public Comment addComment(long messageId,Comment comment){
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	public Comment updateComment(long messageId,Comment comment){
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		if(comment.getId()<=0)
			return null;
		return comments.put(comment.getId(),comment);
	}

	public Comment deleteComment(long messageId, long commentId){
		Map<Long,Comment> comments=messages.get(messageId).getComments();

		return comments.remove(commentId);
	}




}




