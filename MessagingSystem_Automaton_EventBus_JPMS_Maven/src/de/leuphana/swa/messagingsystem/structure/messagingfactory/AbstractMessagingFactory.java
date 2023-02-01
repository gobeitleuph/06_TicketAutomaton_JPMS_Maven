package de.leuphana.swa.messagingsystem.structure.messagingfactory;

import de.leuphana.swa.messagingsystem.structure.message.Message;
import de.leuphana.swa.messagingsystem.structure.messagetypefactory.InternalMessageType;
import de.leuphana.swa.messagingsystem.structure.messagingprotocol.MessagingProtocol;

public abstract class AbstractMessagingFactory {
	public abstract MessagingProtocol createMessagingProtocol();
	public abstract Message createMessage(String sender, String receiver, String contentAsString);

	public static AbstractMessagingFactory getFactory(InternalMessageType messageType) {
		AbstractMessagingFactory abstractMessagingFactory;
		
		switch (messageType) {
		case EMAIL: {
			abstractMessagingFactory = new EmailMessagingFactory();
			break;
		}
		case SMS: {
			abstractMessagingFactory = new SMSMessagingFactory();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + messageType);
		}
		
		return abstractMessagingFactory;
	}
}
