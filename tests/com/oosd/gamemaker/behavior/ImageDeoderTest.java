package com.oosd.gamemaker.behavior;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class ImageDeoderTest {
	
	private String path;
	
	public ImageDeoderTest() {
		this.path = System.getProperty("user.dir");
	}
	
	@Test
	public void testImageDecoder() throws IOException
	{
		File file = new File(this.path + "/resources/EncodedImageString.txt");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		String actualEncodedImageString = new String(data, "UTF-8");
		actualEncodedImageString = actualEncodedImageString.trim();
		
		ImageDecoder imageDecoder =  new ImageDecoder();
		BufferedImage image = imageDecoder.decodeToImage(actualEncodedImageString);
		assertEquals(128, image.getHeight());
		assertEquals(128, image.getWidth());
	}

}
