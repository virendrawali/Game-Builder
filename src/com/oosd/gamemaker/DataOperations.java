package com.oosd.gamemaker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

public class DataOperations {
	
	private static final Logger logger = Logger.getLogger(DataOperations.class);
	private String path;
	
	public DataOperations() {
		this.path = System.getProperty("user.dir");
	}

	public void writeObjectToFile(SaveObject serObj) {
        try(FileOutputStream fileOut = new FileOutputStream(path+"/test"); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(serObj);
        } catch (Exception ex) {
            logger.debug("Exception while saving to file: " + ex.getMessage());
        }
    }
		
	public SaveObject readObjectFromFile() {
		try(FileInputStream fi = new FileInputStream(new File(path+"/test")); ObjectInputStream oi = new ObjectInputStream(fi); ) {
			return (SaveObject)oi.readObject();
			
		} catch (FileNotFoundException e) {
			logger.debug("File Not found: " + e.getMessage());
		}
		 catch (IOException e) {
			 logger.debug("IO Exception: " + e.getMessage());
		}
		catch (ClassNotFoundException e) {
			logger.debug("Class not found: " + e.getMessage());
		}
		return null;
	}
}
