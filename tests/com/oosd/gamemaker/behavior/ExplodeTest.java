package com.oosd.gamemaker.behavior;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Graphics2D;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.oosd.gamemaker.models.Ball;
import com.oosd.gamemaker.models.Rectangle;
import com.oosd.gamemaker.models.Sprite;

public class ExplodeTest {
	
	private Reaction reaction;
	private Sprite primary;
	private Sprite secondary;
	
	@Mock
	Graphics2D graphics2d;
	
	@Mock
	Sound sound;
	
	@Before
	public void setup() {
		graphics2d = Mockito.mock(Graphics2D.class);
		sound = Mockito.mock(Sound.class);
		
		secondary = new Rectangle(Color.RED, 20, 20, 150, 20, 0, 0);
		secondary.draw(graphics2d);
		primary = new Ball(Color.RED, 20, 20, 20, 20, 0, 0);
		primary.draw(graphics2d);
		
		reaction = new Explode(primary, secondary, sound);
	}
	
	@Test
	public void testReactCollision() {
		assertTrue(secondary.getStatus().contentEquals("created"));
		reaction.react();
		assertTrue(secondary.getStatus().contentEquals("Dead"));
	}
	
	@Test
	public void testReactNonCollision() {
		primary.setLocationX(400);
		primary.setLocationY(400);
		primary.draw(graphics2d);
		assertTrue(secondary.getStatus().contentEquals("created"));
		reaction.react();
		assertTrue(secondary.getStatus().contentEquals("created"));
	}

}
