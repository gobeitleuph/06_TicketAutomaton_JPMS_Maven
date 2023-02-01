package de.leuphana.swa.documentsystem.behaviour;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.leuphana.swa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.swa.documentsystem.structure.Document;

public class DocumentSystemTest {

	private static DocumentCommandService documentSystem;
	private static Document document;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		documentSystem = new DocumentSystemImpl();
		document = new Document("New document");
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		documentSystem = null;
		document = null;
	}

	@Test
	public void canDocumentBeAdded() {
		Assertions.assertTrue(documentSystem.addDocument(document));
	}

}
