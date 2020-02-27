package com.oosd.gamemaker.behavior;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import com.oosd.gamemaker.models.Rectangle;
import com.oosd.gamemaker.models.Sprite;

public class ChangeColorTest {
	private Sprite sprite;
	private MouseClickBehaviour changeColor;
	
	@Before
	public void setup() {
		sprite = new Rectangle(Color.BLUE, 10, 10, 150, 10, 0, 0);
		changeColor = new ChangeColor(sprite, Color.RED);
	}
	
	@Test
	public void testRespondToClick() {
		assertEquals(Color.BLUE, sprite.getColor());
		changeColor.respondToClick();
		assertEquals(Color.RED, sprite.getColor());
	}

}
