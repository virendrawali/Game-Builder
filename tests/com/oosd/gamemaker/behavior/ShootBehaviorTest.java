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

public class ShootBehaviorTest {
	
	private Reaction reaction;
	private Sprite primary;
	private Sprite secondary;
	
	@Mock
	private Graphics2D graphics2d;
	
	@Mock
	private Sound sound;
	
	@Before
	public void setup() {
		graphics2d = Mockito.mock(Graphics2D.class);
		sound = Mockito.mock(Sound.class);
		
		secondary = new Rectangle(Color.RED, 20, 20, 150, 20, 0, 0);
		secondary.draw(graphics2d);
		primary = new Ball(Color.RED, 20, 20, 20, 20, 0, 0);
		primary.draw(graphics2d);
		
		reaction = new ShootBehavior(primary, secondary, sound);
	}
	
	@Test
	public void testReactCollision() {
		assertTrue(primary.getStatus().equals("created"));
		assertTrue(secondary.getStatus().equals("created"));
		
		reaction.react();
		
		assertTrue(primary.getStatus().equals("Dead"));
		assertTrue(secondary.getStatus().equals("Dead"));
		
	}
	
	@Test
	public void testReactNonCollision() {
		assertTrue(primary.getStatus().equals("created"));
		assertTrue(secondary.getStatus().equals("created"));
		
		secondary.setLocationX(400);
		secondary.setLocationY(400);
		
		secondary.draw(graphics2d);
		
		reaction.react();
		
		assertTrue(primary.getStatus().equals("created"));
		assertTrue(secondary.getStatus().equals("created"));	
		
	}
	
	

}
