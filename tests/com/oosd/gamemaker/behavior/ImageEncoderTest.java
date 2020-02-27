package com.oosd.gamemaker.behavior;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class ImageEncoderTest {
	
	private String path;
	
	public ImageEncoderTest() {
		this.path = System.getProperty("user.dir");
	}
	
	@Test
	public void testImageEncoder() throws IOException
	{
		ImageEncoder imageEncoder =  new ImageEncoder(this.path + "/resources/Ball.png");
		
		File file = new File(this.path + "/resources/EncodedImageString.txt");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();

		String expectedEncodedImageString = new String(data, "UTF-8");
		expectedEncodedImageString = expectedEncodedImageString.trim();
		
		String actualEncodedImageString = imageEncoder.encodeToString(imageEncoder.getBufferedImage());
		
		assertTrue(expectedEncodedImageString.equals(actualEncodedImageString));	
	}
	
}
