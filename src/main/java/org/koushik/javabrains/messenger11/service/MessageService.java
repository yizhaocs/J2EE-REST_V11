package org.koushik.javabrains.messenger11.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger11.database.DatabaseClass;
import org.koushik.javabrains.messenger11.model.Message;

public class MessageService {
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService(){
		messages.put(1L, new Message(1, "Hello World", "koushik"));
		messages.put(2L, new Message(2, "Hello Jersey", "koushik"));
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}

	public List<Message> getAllMessagesPaginated(int start, int size){
		List<Message> list = new ArrayList<Message>(messages.values());
		return list.subList(start, start + size);
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	

	public Message getMessage(long id){
		return messages.get(id);
	}

	public Message addMessage(Message message){
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message){
		if(message.getId() <= 0){
			return null;
		}
		messages.put(message.getId(),message);
		return message;
	}

	public Message removeMessage(long id){
		return messages.remove(id);
	}
}
