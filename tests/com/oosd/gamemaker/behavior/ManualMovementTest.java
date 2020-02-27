package com.oosd.gamemaker.behavior;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.oosd.gamemaker.Playground;
import com.oosd.gamemaker.models.Ball;
import com.oosd.gamemaker.models.Sprite;


public class ManualMovementTest {
	
	private ManualMovement manualMovement;
	private Sprite sprite;
	
	@Mock
	private Playground playground;
	
	@Mock
	private Graphics2D graphics2D;
	
	@Before
	public void setup() {
		playground = Mockito.mock(Playground.class);
		graphics2D = Mockito.mock(Graphics2D.class);
		
		sprite = new Ball(Color.gray, 10, 10, 10, 10, 0, 0);
		sprite.draw(graphics2D); 		
		when(playground.getHeight()).thenReturn(100);
		when(playground.getWidth()).thenReturn(100);
		
		
	}
	
	@Test
	public void testInvalidDirectionCode() {
		manualMovement = new ManualMovement(KeyEvent.VK_UP, 1000);
		assertFalse(manualMovement.getIsUpCode());
		assertFalse(manualMovement.getIsDownCode());
		assertFalse(manualMovement.getIsLeftCode());
		assertFalse(manualMovement.getIsRightCode());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDispatcherKeyEventUp() {
		manualMovement = new ManualMovement(KeyEvent.VK_UP, 0);
		KeyEvent keyEvent = new KeyEvent(playground, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_UP);
		manualMovement.dispatchKeyEvent(keyEvent);
		assertEquals(true, manualMovement.getIsUp());
		keyEvent = new KeyEvent(playground, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_UP);
		manualMovement.dispatchKeyEvent(keyEvent);
		assertEquals(false, manualMovement.getIsUp());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDispatcherKeyEventDown() {
		manualMovement = new ManualMovement(KeyEvent.VK_DOWN, 1);
		KeyEvent keyEvent = new KeyEvent(playground, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_DOWN);
		manualMovement.dispatchKeyEvent(keyEvent);
		assertEquals(true, manualMovement.getIsDown());
		keyEvent = new KeyEvent(playground, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_DOWN);
		manualMovement.dispatchKeyEvent(keyEvent);
		assertEquals(false, manualMovement.getIsDown());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDispatcherKeyEventLeft() {
		manualMovement = new ManualMovement(KeyEvent.VK_LEFT, 2);
		KeyEvent keyEvent = new KeyEvent(playground, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_LEFT);
		manualMovement.dispatchKeyEvent(keyEvent);
		assertEquals(true, manualMovement.getIsLeft());
		keyEvent = new KeyEvent(playground, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_LEFT);
		manualMovement.dispatchKeyEvent(keyEvent);
		assertEquals(false, manualMovement.getIsLeft());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDispatcherKeyEventRight() {
		manualMovement = new ManualMovement(KeyEvent.VK_RIGHT, 3);
		KeyEvent keyEvent = new KeyEvent(playground, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_RIGHT);
		manualMovement.dispatchKeyEvent(keyEvent);
		assertEquals(true, manualMovement.getIsRight());
		keyEvent = new KeyEvent(playground, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_RIGHT);
		manualMovement.dispatchKeyEvent(keyEvent);
		assertEquals(false, manualMovement.getIsRight());
	}
	
	@Test
	public void testMoveDown() {
		manualMovement = new ManualMovement(KeyEvent.VK_DOWN, 1);
		manualMovement.setIsDown(true);
		manualMovement.setIsDownCode(true);
	
		int positionY = sprite.getLocationY();
		sprite.play();
		
		manualMovement.move(sprite, playground);
		assertEquals(true, manualMovement.getIsDown());
		assertEquals(true, manualMovement.getIsDownCode());
		assertEquals(positionY + 1, sprite.getLocationY());
	}
	
	@Test
	public void testMoveUp() {
		manualMovement = new ManualMovement(KeyEvent.VK_UP, 0);
		manualMovement.setIsUp(true);
		manualMovement.setIsUpCode(true);
	
		int positionY = sprite.getLocationY();
		sprite.play();
		
		manualMovement.move(sprite, playground);
		assertEquals(true, manualMovement.getIsUp());
		assertEquals(true, manualMovement.getIsUpCode());
		assertEquals(positionY - 1, sprite.getLocationY());
	}
	
	@Test
	public void testMoveLeft() {
		manualMovement = new ManualMovement(KeyEvent.VK_LEFT, 2);
		manualMovement.setIsLeft(true);
		manualMovement.setIsLeftCode(true);
	
		int positionX = sprite.getLocationX();
		sprite.play();
		
		manualMovement.move(sprite, playground);
		assertEquals(true, manualMovement.getIsLeft());
		assertEquals(true, manualMovement.getIsLeftCode());
		assertEquals(positionX - 1, sprite.getLocationX());
	}
	
	@Test
	public void testMoveRight() {
		manualMovement = new ManualMovement(KeyEvent.VK_RIGHT, 3);
		manualMovement.setIsRight(true);
		manualMovement.setIsRightCode(true);
	
		int positionX = sprite.getLocationX();
		sprite.play();
		
		manualMovement.move(sprite, playground);
		assertEquals(true, manualMovement.getIsRight());
		assertEquals(true, manualMovement.getIsRightCode());
		assertEquals(positionX + 1, sprite.getLocationX());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testSetUniDirectionMovementX() {
		manualMovement = new ManualMovement(KeyEvent.VK_RIGHT, 3);
		manualMovement.setUniDirectionMovementX(true);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testSetUniDirectionMovementY() {
		manualMovement = new ManualMovement(KeyEvent.VK_RIGHT, 3);
		manualMovement.setUniDirectionMovementY(true);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testIsUniDirectionMovementX() {
		manualMovement = new ManualMovement(KeyEvent.VK_RIGHT, 3);
		manualMovement.isUniDirectionMovementX();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testIsUniDirectionMovementY() {
		manualMovement = new ManualMovement(KeyEvent.VK_RIGHT, 3);
		manualMovement.isUniDirectionMovementY();
	}
	

}
