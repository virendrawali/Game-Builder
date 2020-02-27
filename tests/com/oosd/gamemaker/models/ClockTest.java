package com.oosd.gamemaker.models;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics2D;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

import com.oosd.gamemaker.models.DigitalClock;

public class ClockTest {

	private DigitalClock digitalClockTest;
	
	@Mock
	private Graphics2D graphics2D;
	
	@Before
	public void setup() {
		graphics2D = Mockito.mock(Graphics2D.class);
		digitalClockTest = new DigitalClock(0, 0);
	}
	
	@Test
	public void testGettersAndSetters() {
		digitalClockTest.setDx(20);
		digitalClockTest.setDy(20);

		assertEquals(20, digitalClockTest.getDx());
		assertEquals(20, digitalClockTest.getDy());
		assertEquals(0, digitalClockTest.getX());
		assertEquals(0, digitalClockTest.getY());
	}
	
	@Test
	public void testDraw() {
		digitalClockTest.draw(graphics2D);
		Collection<Invocation> invocations = Mockito.mockingDetails(graphics2D).getInvocations();
		assertEquals(3, invocations.size());
	}

	
}
