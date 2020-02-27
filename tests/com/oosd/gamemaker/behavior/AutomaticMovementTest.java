package com.oosd.gamemaker.behavior;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.oosd.gamemaker.Playground;
import com.oosd.gamemaker.models.Rectangle;
import com.oosd.gamemaker.models.Sprite;

public class AutomaticMovementTest {
	
	private AutomaticMovement automaticMovement;
	
	@Mock
	Playground playground;
	
	@Before
	public void setup() {
		playground = Mockito.mock(Playground.class);
	}
	
	public AutomaticMovementTest()
	{
		automaticMovement = new AutomaticMovement(true, true);
	}
	
	@Test
	public void testUnidirectionalXY() {
		automaticMovement.setUniDirectionMovementX(true);
		automaticMovement.setUniDirectionMovementY(false);
		assertEquals(true, automaticMovement.isUniDirectionMovementX());
		assertEquals(false, automaticMovement.isUniDirectionMovementY());
	}
	
	@Test
	public void testMoveX()
	{
		automaticMovement.setUniDirectionMovementX(true);
		automaticMovement.setUniDirectionMovementY(false);
		Sprite sprite = new Rectangle(Color.BLACK, 100, 100, 100, 100, 2, 0);
		sprite.play();
		automaticMovement.move(sprite, playground);
		assertEquals(102, sprite.getLocationX());
	}
	
	@Test
	public void testMoveY()
	{
		automaticMovement.setUniDirectionMovementX(false);
		automaticMovement.setUniDirectionMovementY(true);
		Sprite sprite = new Rectangle(Color.BLACK, 100, 100, 100, 100, 0, 2);
		sprite.play();
		automaticMovement.move(sprite, playground);
		assertEquals(102, sprite.getLocationY());
	}

	@Test
	public void testMoveXY()
	{
		automaticMovement.setUniDirectionMovementX(true);
		automaticMovement.setUniDirectionMovementY(true);
		Sprite sprite = new Rectangle(Color.BLACK, 100, 100, 100, 100, 2, 2);
		sprite.play();
		automaticMovement.move(sprite, playground);
		assertEquals(102, sprite.getLocationX());
		assertEquals(102, sprite.getLocationY());
	}
}
