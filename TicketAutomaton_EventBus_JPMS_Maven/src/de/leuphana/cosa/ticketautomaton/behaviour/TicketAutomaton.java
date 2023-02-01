package de.leuphana.cosa.ticketautomaton.behaviour;

import de.leuphana.cosa.component.structure.Component;
import de.leuphana.cosa.componentservicebus.behaviour.ComponentServiceBus;
import de.leuphana.cosa.componentservicebus.behaviour.ComponentServiceBusService;
import de.leuphana.swa.documentsystem.behaviour.DocumentSystemImpl;
import de.leuphana.swa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.swa.messagingsystem.behaviour.MessagingSystemImpl;
import de.leuphana.swa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.swa.printingsystem.behaviour.PrintingSystemImpl;
import de.leuphana.swa.printingsystem.behaviour.service.PrintingCommandService;

public class TicketAutomaton {
	// Components
	private PrintingCommandService printingSystem;
	private DocumentCommandService documentSystem;
	private MessagingCommandService messagingSystem;
	
	// Connector 
	private ComponentServiceBusService componentServiceBusService;
	
	public TicketAutomaton() {
		// Create topology (star)
		documentSystem = new DocumentSystemImpl();
		printingSystem = new PrintingSystemImpl();
		messagingSystem = new MessagingSystemImpl();
		
		componentServiceBusService = new ComponentServiceBus();
		
		componentServiceBusService.registerComponent((Component) documentSystem);
		componentServiceBusService.registerComponent((Component) printingSystem);
		componentServiceBusService.registerComponent((Component) messagingSystem);
		
		componentServiceBusService.configureComponentConnections();
	}
	
	public boolean sellTicket(String start, String end) {
		return componentServiceBusService.sellTicket(start, end);
	}

}