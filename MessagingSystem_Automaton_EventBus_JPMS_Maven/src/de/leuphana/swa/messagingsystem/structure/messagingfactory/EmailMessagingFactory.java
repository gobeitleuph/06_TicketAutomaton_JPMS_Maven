package de.leuphana.swa.messagingsystem.structure.messagingfactory;

import de.leuphana.swa.messagingsystem.structure.message.Message;
import de.leuphana.swa.messagingsystem.structure.message.MessageBuilder;
import de.leuphana.swa.messagingsystem.structure.messagetypefactory.InternalMessageType;
import de.leuphana.swa.messagingsystem.structure.messagingprotocol.MessagingProtocol;
import de.leuphana.swa.messagingsystem.structure.messagingprotocol.MessagingProtocolFactory;

public class EmailMessagingFactory extends AbstractMessagingFactory {

	@Override
	public MessagingProtocol createMessagingProtocol() {
		return MessagingProtocolFactory.createMessagingProtocol(InternalMessageType.EMAIL);
	}

	@Override
	public Message createMessage(String sender, String receiver, String contentAsString) {
		return MessageBuilder.createMessage(receiver, sender, contentAsString, InternalMessageType.EMAIL);
	}

}
