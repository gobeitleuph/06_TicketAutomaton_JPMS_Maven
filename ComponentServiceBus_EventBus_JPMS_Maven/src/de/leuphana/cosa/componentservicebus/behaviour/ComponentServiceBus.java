package de.leuphana.cosa.componentservicebus.behaviour;

import java.util.HashMap;
import java.util.Map;

import com.google.common.eventbus.Subscribe;

import de.leuphana.cosa.component.structure.Component;
import de.leuphana.cosa.componentservicebus.structure.adapter.ManageableToPrintableAdapter;
import de.leuphana.cosa.componentservicebus.structure.adapter.PrintReportToSendableAdapter;
import de.leuphana.swa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.swa.documentsystem.behaviour.service.event.ManageableEventService;
import de.leuphana.swa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.swa.messagingsystem.behaviour.service.event.SendableEvent;
import de.leuphana.swa.messagingsystem.behaviour.service.event.SendableEventListener;
import de.leuphana.swa.messagingsystem.behaviour.service.event.SendableEventService;
import de.leuphana.swa.printingsystem.behaviour.service.PrintingCommandService;
import de.leuphana.swa.printingsystem.behaviour.service.event.PrintableEventService;

public class ComponentServiceBus implements ComponentServiceBusService {

	private static boolean isMessageDelivered = false;

	private Map<String, Component> componentMap;
	private Map<String, String> commandServices;
	private Map<String, String> eventServices;

	public ComponentServiceBus() {
		componentMap = new HashMap<String, Component>();
		commandServices = new HashMap<String, String>();
		eventServices = new HashMap<String, String>();
	}

	@Override
	public void registerComponent(Component component) {
		componentMap.put(component.getComponentName(), component);
		commandServices.put(component.getComponentName(),
				component.getCommandServicePath() + "." + component.getCommandServiceName());
		eventServices.put(component.getComponentName(),
				component.getEventServicePath() + "." + component.getEventServiceName());
	}

	@Override
	public void configureComponentConnections() {
		// Sp�ter externe Konfiguration, die aufzeigt, welcher CommandService bei
		// welchem Event aufgerufen wird!!!

		// Erzeugung des manageableToPrintableAdapter
		Component sourceComponent = componentMap.get("DocumentSystem");
		Component targetComponent = componentMap.get("PrintingSystem");

		// Sp�ter Nutzung von Java Reflection API um generisch sourceComponent und
		// targetComponent zuzuweisen
		new ManageableToPrintableAdapter((ManageableEventService) sourceComponent,
				(PrintingCommandService) targetComponent);

		// Erzeugung des printReportToSendableAdapter
		sourceComponent = componentMap.get("PrintingSystem");
		targetComponent = componentMap.get("MessagingSystem");

		// Sp�ter Nutzung von Java Reflection API um generisch sourceComponent und
		// targetComponent zuzuweisen
		new PrintReportToSendableAdapter((PrintableEventService) sourceComponent,
				(MessagingCommandService) targetComponent);
	}

	@Override
	public boolean sellTicket(String start, String end) {
		
		Component component = componentMap.get("MessagingSystem");
		((SendableEventService)component).addSendableEventListener(new SendableEventListener() {
			
			@Subscribe
			@Override
			public void onMessageDelivered(SendableEvent sendableEvent) {
				if(sendableEvent.getDeliveryReport().isDeliverySuccessful()) {
					isMessageDelivered = true;
				}
			}
		});
		
		// Sp�ter statt sellTicket lose Koppelung durch Event-Delegation
		Component startComponent = componentMap.get("DocumentSystem");
		((DocumentCommandService) startComponent).createDocument(start + " " + end);

		return isMessageDelivered;
	}
}
