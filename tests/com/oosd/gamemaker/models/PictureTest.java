package com.oosd.gamemaker.models;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics2D;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

public class PictureTest {
	
	private Picture picture;
	private String path;
	
	@Mock
	Graphics2D graphics2d;

	@Before
	public void setup() {
		graphics2d = Mockito.mock(Graphics2D.class);
		this.path = System.getProperty("user.dir");
	}
	
	
	@Test
	public void testPictureTest()
	{
		picture = new Picture(100, 100, 40, 40, 0, 0, this.path + "/resources/Ball.png" );
		picture.draw(graphics2d);
		Collection<Invocation> invocations = Mockito.mockingDetails(graphics2d).getInvocations();
		assertEquals(1, invocations.size());
	}
	

}
