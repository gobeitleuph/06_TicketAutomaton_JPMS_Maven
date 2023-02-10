module de.leuphana.cosa.componentservicebus {
	exports de.leuphana.cosa.componentservicebus.behaviour;
	exports de.leuphana.cosa.componentservicebus.structure.adapter;
	opens de.leuphana.cosa.componentservicebus.behaviour;


	requires de.leuphana.cosa.component;
	requires de.leuphana.cosa.documentsystem;
	requires de.leuphana.cosa.messagingsystem;
	requires de.leuphana.cosa.printingsystem;
	requires guava;
}