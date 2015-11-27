package org.koushik.javabrains.messenger11.resources;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger11.model.Message;
import org.koushik.javabrains.messenger11.resources.beans.MessageFilterBean;
import org.koushik.javabrains.messenger11.service.MessageService;

/**
 * REST Web Services 24 - Using Context and BeanParam annotatio
 * POST http://localhost:8080/messenger11/webapi/messages {"author":"koushik","id":3,"message":"Hello World 2"}
 * http://localhost:8080/messenger11/webapi/messages
 * http://localhost:8080/messenger11/webapi/messages/1
 * http://localhost:8080/messenger11/webapi/messages/2
 * http://localhost:8080/messenger11/webapi/messages/test
 * http://localhost:8080/messenger11/webapi/messages?year=2015
 * http://localhost:8080/messenger11/webapi/messages?start=2&size=1
 */
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {	
	MessageService messageService = new MessageService();

	// http://localhost:8080/messenger11/webapi/messages
	// http://localhost:8080/messenger11/webapi/messages?year=2015
	// http://localhost:8080/messenger11/webapi/messages?year=2014
	// http://localhost:8080/messenger11/webapi/messages?start=0&size=1
	// http://localhost:8080/messenger11/webapi/messages?start=1&size=1
	@GET
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		// http://localhost:8080/messenger11/webapi/messages?year=2015
		if(filterBean.getYear() > 0){
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		// http://localhost:8080/messenger11/webapi/messages?start=0&size=1
		// http://localhost:8080/messenger11/webapi/messages?start=1&size=1
		if(filterBean.getStart() >= 0 && filterBean.getSize() >= 0){
			return messageService.getAllMessagesPaginated(filterBean.getStart(),filterBean.getSize());
		}
		
		// http://localhost:8080/messenger11/webapi/messages
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Message test3(@PathParam("messageId")String messageId){
	public Message getMessage(@PathParam("messageId")long messageId){
		return messageService.getMessage(messageId);
	}
	
	@GET
	@Path("/test")
	// @Produces(MediaType.TEXT_PLAIN)
	public String test1(){
		return "test";
	}
	
	@POST
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message){
		return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId")long id, Message message){
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	// @Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId")long id){
		 messageService.removeMessage(id);
	}

//	@GET
//	@Path("/{messageId}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String test2(){
//		return "messageId";
//	}
	
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getHelloWorld(){
//		return "Hello World!";
//	}
}
