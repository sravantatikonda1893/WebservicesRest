package com.java.sravan.WS_RSMessenger.model;
//We dont need to have @xmlrootelement annotation. as this model is not going to be the root element in the response
public class Link {
private String link;
private String rel;
public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}
public String getRel() {
	return rel;
}
public void setRel(String rel) {
	this.rel = rel;
}

}
