package com.oosd.gamemaker.behavior;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Graphics2D;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.oosd.gamemaker.models.Ball;
import com.oosd.gamemaker.models.Rectangle;
import com.oosd.gamemaker.models.Sprite;

public class ChangeDirectionTest {
	
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
		
		secondary = new Rectangle(Color.RED, 20, 20, 150, 20, 10, 10);
		secondary.setAutomaticMovement(new AutomaticMovement(true, false));
		secondary.draw(graphics2d);
		primary = new Ball(Color.RED, 20, 20, 20, 20, 0, 0);
		primary.draw(graphics2d);
		
		reaction = new ChangeDirection(primary, secondary, sound);
	}
	
	@Test
	public void testReactCollisionX() {
		int dy = secondary.getDy();
		reaction.react();
		assertEquals(-1 * dy,  secondary.getDy());
	}
	
	@Test
	public void testReactCollisionY() {
		secondary.setAutomaticMovement(new AutomaticMovement(false, true));
		int dx = secondary.getDx();
		reaction.react();
		assertEquals(-1 * dx,  secondary.getDx());
	}
	
	@Test
	public void testReactNonCollision() {
		primary.setLocationX(400);
		primary.setLocationY(400);
		primary.draw(graphics2d);
		int dx = secondary.getDx();
		int dy = secondary.getDy();
		reaction.react();
		assertEquals(dx,  secondary.getDx());
		assertEquals(dy,  secondary.getDy());
	}
}
