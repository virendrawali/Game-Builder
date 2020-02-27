package com.oosd.gamemaker;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.oosd.gamemaker.models.Composite;

public class DataOperationsTest {
	
	private SaveObject saveObject;
	
	private DataOperations dataOperations;
	
	private String path;
	
	@Before
	public void setup() {
		path = System.getProperty("user.dir");
		
		LevelObject levelObject = new LevelObject(null, new Composite(), null);
		ArrayList<LevelObject> levelObjects = new ArrayList<LevelObject>();
		levelObjects.add(levelObject);
		
		saveObject = new SaveObject(levelObjects);
		dataOperations = new DataOperations();
	}
	
	@Test
	public void saveTest() {
		dataOperations.writeObjectToFile(saveObject);
		File file = new File(path + "/test");
		assertTrue(file.exists());
	}
	
	@Test
	public void readTest() {
		SaveObject saveObject = dataOperations.readObjectFromFile();
		assertNotNull(saveObject);
	}
	
}
