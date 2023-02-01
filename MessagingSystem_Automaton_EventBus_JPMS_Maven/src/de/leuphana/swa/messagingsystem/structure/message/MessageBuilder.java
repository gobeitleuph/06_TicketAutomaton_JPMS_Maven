package de.leuphana.swa.messagingsystem.structure.message;

import de.leuphana.swa.messagingsystem.structure.communicationpartner.EmailReceiver;
import de.leuphana.swa.messagingsystem.structure.communicationpartner.EmailSender;
import de.leuphana.swa.messagingsystem.structure.communicationpartner.Receiver;
import de.leuphana.swa.messagingsystem.structure.communicationpartner.Sender;
import de.leuphana.swa.messagingsystem.structure.content.Content;
import de.leuphana.swa.messagingsystem.structure.content.EmailContent;
import de.leuphana.swa.messagingsystem.structure.messagetypefactory.InternalMessageType;

public class MessageBuilder {

	private MessageBuilder() {
	}
	
	public static Message createMessage(String receiver, String sender, String contentAsString, InternalMessageType messageType) {
		Message message;
		
		MessageHeader messageHeader = createMessageHeader(sender, receiver, messageType);
		MessageBody messageBody = createMessageBody(contentAsString, messageType);
		
		
		switch (messageType) {
		case EMAIL: {
			message = new EmailMessage(messageHeader, messageBody);
			break;
		}
		case SMS: {
			message = new SMSMessage(messageHeader, messageBody);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + messageType);
		}
		
		return message;
	}
	
	private static MessageHeader createMessageHeader(String senderAddress, String receiverAddress, InternalMessageType messageType) {
		Receiver receiver = null;
		Sender sender = null;
		
		// TODO Factory Method Pattern !!!
		switch (messageType) {
		case EMAIL: {
			receiver = new EmailReceiver(receiverAddress);
			sender = new EmailSender(senderAddress);
			break;
		}
		case SMS: {
			// TODO
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + messageType);
		}
		
		return new MessageHeader(sender, receiver);
	}
	
	private static MessageBody createMessageBody(String contentAsString, InternalMessageType messageType) {
		Content content = null;
		
		// TODO Factory Method Pattern !!!
		switch (messageType) {
		case EMAIL: {
			content = new EmailContent(contentAsString);
			break;
		}
		case SMS: {
			// TODO
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + messageType);
		}
		return new MessageBody(content);
	}
}