package de.leuphana.swa.messagingsystem.structure.messagetypefactory;

import de.leuphana.swa.messagingsystem.behaviour.service.MessageType;

public class InternalMessagingTypeFactory {
	
	private InternalMessagingTypeFactory() {
	}

	public static InternalMessageType createInternalMessageType(MessageType messageType) {
		InternalMessageType internalMessageType;

		switch (messageType) {
		case EMAIL: {
			internalMessageType = InternalMessageType.EMAIL;
			break;
		}
		case SMS: {
			internalMessageType = InternalMessageType.SMS;
			break;
		}
		default:
			// besser mit MessageTypeNotAllowedException
			throw new IllegalArgumentException("Unexpected value: " + messageType);
		}

		return internalMessageType;
	}

}
