package com.oosd.gamemaker.models;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.event.KeyEvent;
import org.junit.Test;
import com.oosd.gamemaker.models.Ball;
import com.oosd.gamemaker.Maker;
import com.oosd.gamemaker.Playground;
import com.oosd.gamemaker.behavior.AutomaticMovement;
import com.oosd.gamemaker.behavior.BoundaryBounce;
import com.oosd.gamemaker.behavior.ManualMovement;

public class BallTest {

	private Ball b = new Ball(Color.BLACK, 0, 0, 0, 0, 20, 20);
	private Playground pu = new Playground(new Maker());
	
	@Test
	public void test() {
		//pu.startGame();
	
		b.setAutomaticMovement(new AutomaticMovement(true, true));
		b.setBoundaryMovement(new BoundaryBounce());
		b.setManualMovement(new ManualMovement(KeyEvent.VK_DOWN, 1));
		b.setManualMovement(new ManualMovement(KeyEvent.VK_UP, 0));
		b.setManualMovement(new ManualMovement(KeyEvent.VK_LEFT, 2));
		b.move(pu);
		b.setDx(20);
		b.setDy(20);
		assertEquals(20, b.getDx());
		assertEquals(20, b.getDy());
   }
	
}
