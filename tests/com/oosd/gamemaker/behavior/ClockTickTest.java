package com.oosd.gamemaker.behavior;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.oosd.gamemaker.Playground;
import com.oosd.gamemaker.models.DigitalClock;
import com.oosd.gamemaker.models.Sprite;

public class ClockTickTest {
	
	@Mock
	private Playground playground;
	
	private ClockTick clockTick;
	
	@Before
	public void setup() {
		playground = Mockito.mock(Playground.class);
		clockTick = new ClockTick();
	} 
	
	@Test
	public void testUnidirectionalXY() {
		Sprite sprite = new DigitalClock(100, 100);
		sprite.play();
		for(int i = 0; i<6000;i++)
		{
			clockTick.move(sprite, playground);
		}
		assertEquals(1, sprite.getLocationX());
		assertEquals(0, sprite.getLocationY());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testSetUniDirectionMovementX() {
		clockTick.setUniDirectionMovementX(true);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testSetUniDirectionMovementY() {
		clockTick.setUniDirectionMovementY(true);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testIsUniDirectionMovementX() {
		clockTick.isUniDirectionMovementX();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testIsUniDirectionMovementY() {
		clockTick.isUniDirectionMovementY();
	}
	
}
