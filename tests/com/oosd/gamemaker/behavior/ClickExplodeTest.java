package com.oosd.gamemaker.behavior;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import com.oosd.gamemaker.models.Rectangle;
import com.oosd.gamemaker.models.Sprite;

public class ClickExplodeTest {
	
	private Sprite sprite;
	private MouseClickBehaviour clickExplode;
	
	@Before
	public void setup() {
		sprite = new Rectangle(Color.blue, 10, 10, 150, 10, 0, 0);
		clickExplode = new ClickExplode(sprite);
	}
	
	@Test
	public void testRespondToClick() {
		assertTrue(sprite.getStatus().equals("created"));
		clickExplode.respondToClick();
		assertTrue(sprite.getStatus().equals("Dead"));
	}

}
