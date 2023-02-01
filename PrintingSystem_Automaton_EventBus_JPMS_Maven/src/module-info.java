module de.leuphana.cosa.printingsystem {
	exports de.leuphana.swa.printingsystem.behaviour.service;
	exports de.leuphana.swa.printingsystem.behaviour.service.event;
	exports de.leuphana.swa.printingsystem.behaviour.service.exceptions;
	exports de.leuphana.swa.printingsystem.behaviour;

	requires de.leuphana.cosa.component;
	requires org.apache.logging.log4j;
	requires transitive org.junit.jupiter.api;
	requires transitive org.junit.jupiter.engine;
}