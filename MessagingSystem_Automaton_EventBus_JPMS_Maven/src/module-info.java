module de.leuphana.cosa.messagingsystem {
	exports de.leuphana.swa.messagingsystem.behaviour.service;
	exports de.leuphana.swa.messagingsystem.behaviour.service.event;
	exports de.leuphana.swa.messagingsystem.behaviour;
	exports de.leuphana.swa.messagingsystem.structure.communicationpartner;
	exports de.leuphana.swa.messagingsystem.behaviour.exception;

	requires de.leuphana.cosa.component;
	requires org.apache.logging.log4j;
	requires org.junit.jupiter.api;
}