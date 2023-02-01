module de.leuphana.cosa.documentsystem {
	exports de.leuphana.swa.documentsystem.behaviour.service.event;
	exports de.leuphana.swa.documentsystem.behaviour.service;
	exports de.leuphana.swa.documentsystem.behaviour;

	requires de.leuphana.cosa.component;
	requires guava;
	requires org.apache.logging.log4j;
	requires org.junit.jupiter.api;
}