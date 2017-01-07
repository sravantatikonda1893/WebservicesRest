package com.java.sravan.WS_RSMessenger.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
//We tell JAX-B as a clue that this is the xml root element 
public class Comment {
	private long id;
	private String message;
	private Date created;
	private String author;
	private Map<Long,Comment> comments=new HashMap<Long,Comment>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	//	When we dont want some thing(Comments) to be ignored whenever messages instances have been converted to XML/JSON
	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
	public Comment(long id, String message, String author) {
		super();
		this.id = id;
		this.message = message;
		this.author = author;
	}
	//		This default constructor is needed as this has to be converted to json or xml by frameworks for packing a response
	public Comment() {
	}

}
