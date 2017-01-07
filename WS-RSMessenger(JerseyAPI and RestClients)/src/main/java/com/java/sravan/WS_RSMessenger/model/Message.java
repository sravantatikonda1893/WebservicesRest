package com.java.sravan.WS_RSMessenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
//We tell JAX-B as a clue that this is the xml root element 
public class Message {
	private long id;
	private String message;
	private Date created;
	private String author;
	private List<Link> links=new ArrayList<Link>();
	private Map<Long, Comment> comments;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
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
	public Message(long id, String message, String author) {
		super();
		this.id = id;
		this.message = message;
		this.author = author;
	}
	//This default constructor is needed as this has to be converted to json or xml by frameworks for packing a response
	public Message() {
	}
	public Map<Long,Comment> getComments() {
		return comments;
	}
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
	//Adding links in a message response	
	public void addLink(String uri,String rel){
		Link link=new Link();
		link.setLink(uri);
		link.setRel(rel);
		links.add(link);
	}

}
