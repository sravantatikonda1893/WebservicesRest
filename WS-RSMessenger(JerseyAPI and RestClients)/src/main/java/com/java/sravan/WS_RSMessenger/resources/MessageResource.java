package com.java.sravan.WS_RSMessenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
/*Accepts in client=@produces in server(service)
Content-Type=@Consumes in server(Service)
 */
import com.java.sravan.WS_RSMessenger.model.Message;
import com.java.sravan.WS_RSMessenger.service.MessageService;
@Path("/messages")
@Consumes(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
//It can Produce both xml and json responses based on the values passed in the reuqest header
@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public class MessageResource {
	MessageService msgserv=new MessageService();
	//	to execute this particular method when there is a get request 
	@GET
	@Produces(MediaType.APPLICATION_XML)
	//This annotation tells Jersey what the return format(response) for the request is. 
	//	And the java has inbuilt support for JAXB, it should get a clue to start converting
	/*public List<Message> getMessages(@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size)
	{
		if(year==0){
			//			If there is any query string in the request url, then it will be year variable.(els year=0)
			//			We can use the same method
			return msgserv.getAllMessagesForYear(year);
		}
		if(start>0 && size>0)
			return msgserv.getAllMessagesPaginated(start, size);
		return msgserv.getAllMessages();
	}
	 */
	//	rather than using above annotated params as will be clumpsy we can use beanparam which can get all the bean instances
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean)
	{	
		if(filterBean.getYear()==0){
			return msgserv.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart()>0 && filterBean.getSize()>0)
			return msgserv.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		return msgserv.getAllMessages();
	}



	//	Path value specifying the variable should be included in {}, the sub folder of messages which is root one.
	//To access that sub folder name, we should use annotation '@PathParam' with the same variable as an argument
	//That string argument will automatically converted to long or any required type being specified by jersey api for us	
	@GET
	@Path("/{messageId}")
	//	As the data transfer in HTTP is only Strings...Jersey will convert automatically to required type 
	public Message getMessage(@PathParam("messageId") long msgid, @Context UriInfo uriInfo)
	{
		Message message=msgserv.getMessage(msgid);
		message.addLink(getUriForSelf(uriInfo,message), "self");
		message.addLink(getUriForProfile(uriInfo,message), "profile");
		message.addLink(getUriForComments(uriInfo,message), "comments");
		return message;
		//return msgserv.getMessage(msgid);
	}



	private String getUriForComments(UriInfo uriInfo, Message message) {
		URI uri=uriInfo.getBaseUriBuilder()  //For getting the context path(http://localhost:8085/WS-RSMessenger/messages)
				.path(MessageResource.class)	
				.path(MessageResource.class,"getCommentResource") //for getting the flow for CommentResource class(As it is internal resource)
				.path(CommentResource.class) //For adding class path(/) specified over commentresource
				.resolveTemplate("messageId", message.getId()) //for getting comments of particular msgid 
				.build();
		return uri.toString();
	}



	private String getUriForProfile(UriInfo uriInfo,Message message)
	{
		URI uri=	uriInfo.getBaseUriBuilder()  //For getting the context path(http://localhost:8085/WS-RSMessenger/messages)
				.path(ProfileResource.class) //For adding class path(/profiles)
				.path(message.getAuthor())// for adding internal(/authorName)
				.build();
		return uri.toString();

	}
	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri=	uriInfo.getBaseUriBuilder()  //For getting the context path(http://localhost:8085/WS-RSMessenger/messages)
				.path(MessageResource.class) //For adding class path(/messages)
				.path(Long.toString(message.getId()))// for adding internal(/messageId)
				.build()
				.toString();
		return uri;
	}

	//	As this is a post method we should configure that this method is gonna consume the Json using the consumes annotation
	//In the rest client, use post request and type the json object using raw tab

	/*@POST
	public Message addMessage(Message message){
		return msgserv.addMessage(message);
	}
	 */
	//If we want to send the status codes for response more specifically, then we can use ResponceBuilder object and its methods
	//Status.CREATED for 201 status code
	//	
	@POST
	public Response addMessage(@Context UriInfo uriInfo,Message message) throws URISyntaxException{
		Message newMessage =msgserv.addMessage(message);
		/*	return Response.status(Status.CREATED).entity(newMessage).build();
		return Response.created(new URI("/WS-RSMessenger/webresources/messages"+ newMessage.getId())).		.entity(newMessage)
		.build();
--Rather tha hardcoding the context
		 */
		String id=	String.valueOf(newMessage);	
		URI ur=uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(ur).entity(newMessage).build();
	}
	@PUT
	@Path("{messageId}")
	public Message updateMessage(@PathParam("messageId") long msgId,Message message){
		message.setId(msgId);
		return msgserv.updateMessage(message);
	}
	@DELETE
	@Path("{messageId}")
	public Message deleteMessage(@PathParam("messageId") long id){
		return msgserv.deleteeMessage(id);
	}
	//	No matter what the Http method is. Once this following url request is recieved by the service, 
	//	jersey will retrun it to CommentResource class. This method acts as a sub resource
	@Path("/messageId/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}

}
//If we change the MediaType.APPLICATION_JSON of Produce annotation, the ouput willl be JSON format.
//Add Maven dependencies for Json as its not provided by java as JAXB for Xml is provided in JavaWrite of Java.
//As most of the mthods produces and consumes Application_json as mediatype. we can annotate them at class itself.
// /projectcontext/webresources/messages?start=2&size=1
///projectcontext/webresources/messages?year=2015

