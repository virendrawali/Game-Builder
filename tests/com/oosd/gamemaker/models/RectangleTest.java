package com.oosd.gamemaker.models;



import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.oosd.gamemaker.Playground;
import com.oosd.gamemaker.behavior.AutomaticMovement;
import com.oosd.gamemaker.behavior.BoundaryBehavior;
import com.oosd.gamemaker.behavior.ManualMovement;
import com.oosd.gamemaker.behavior.MouseClickBehaviour;
import com.oosd.gamemaker.behavior.Reaction;
import com.oosd.gamemaker.models.Rectangle;

public class RectangleTest {

	private Rectangle rectangle;
	
	@Mock
	Graphics2D graphics2d;
	
	@Mock
	Shape shape;
	
	@Mock
	MouseClickBehaviour mouseClickBehaviour;
	
	@Mock
	AutomaticMovement automaticMovement;
	
	@Mock
	ManualMovement manualMovement;

	@Mock
	Reaction reaction;
	
	@Mock
	BoundaryBehavior boundaryBehavior;
	
	@Mock
	Playground playground;
	
	@Before
	public void setup() {
		graphics2d = Mockito.mock(Graphics2D.class);
		shape = Mockito.mock(Shape.class);
		mouseClickBehaviour = Mockito.mock(MouseClickBehaviour.class);
		automaticMovement = Mockito.mock(AutomaticMovement.class);
		manualMovement = Mockito.mock(ManualMovement.class);
		reaction = Mockito.mock(Reaction.class);
		boundaryBehavior = Mockito.mock(BoundaryBehavior.class);
		playground = Mockito.mock(Playground.class);
		rectangle = new Rectangle(Color.BLACK, 100, 100, 100, 100, 0, 0);
	}
	
	
	@Test
	public void testPictureTest()
	{
		rectangle.draw(graphics2d);
		assertEquals(true,rectangle.getShape().contains(100, 100, 100, 100));
	}
	
	
	@Test
	public void testDx() {
		rectangle.setDx(20);
		assertEquals(20, rectangle.getDx());
	}
	
	@Test
	public void testDy() {
		rectangle.setDy(20);
		assertEquals(20, rectangle.getDy());
	}
	
	@Test
	public void testLocationX()
	{
		rectangle.setLocationX(100);
		rectangle.setX(100);
		assertEquals(100, rectangle.getLocationX());
		assertEquals(100, rectangle.getX());
	}

	@Test
	public void testLocationY()
	{
		rectangle.setLocationY(100);
		rectangle.setY(100);
		assertEquals(100, rectangle.getLocationY());
		assertEquals(100, rectangle.getY());
	}
	
	@Test
	public void testShootEffect()
	{
		rectangle.setShootEffect(true);
		assertEquals(true, rectangle.isShootEffect());
	}
	
	@Test
	public void testWillShoot()
	{
		rectangle.setWillShoot(true);
		assertEquals(true, rectangle.isWillShoot());
	}
	
	@Test
	public void testUpdate()
	{
		rectangle.play();
		assertEquals(false, rectangle.isGamePaused());
		rectangle.update(100, 100);
		assertEquals(100, rectangle.getLocationX());
		assertEquals(100, rectangle.getLocationY());
		rectangle.pause();
		assertEquals(true, rectangle.isGamePaused());
	}
	
	@Test
	public void testHeight()
	{
		assertEquals(100, rectangle.getHeight());
	}
	
	@Test
	public void testWidth()
	{
		assertEquals(100, rectangle.getWidth());
	}
	
	@Test
	public void testColor()
	{
		rectangle.setColor(Color.BLACK);
		assertEquals(Color.BLACK, rectangle.getColor());
	}
	
	@Test
	public void testStatus()
	{
		rectangle.setStatus("Hello");
		assertEquals("Hello", rectangle.getStatus());
	}
	
	@Test
	public void testShape()
	{
		rectangle.setShape(shape);
		assertEquals(shape, rectangle.getShape());
	}
	
	@Test
	public void testHasMouseBehaviour()
	{
		rectangle.setHasMouseBehaviour(true);
		assertEquals(true, rectangle.hasMouseBehaviour());
	}
	
	@Test
	public void testMouseClickBehaviour()
	{
		rectangle.setMouseClickBehaviour(mouseClickBehaviour);
		assertEquals(mouseClickBehaviour, rectangle.getMouseClickBehaviour());
		
	}
	
	@Test
	public void testAutomaticMovement()
	{
		rectangle.setAutomaticMovement(automaticMovement);
		assertEquals(automaticMovement, rectangle.getAutomaticMovement());
		
	}
	
	@Test
	public void testSetManualMovement()
	{
		rectangle.setManualMovement(manualMovement);
		assertEquals(1, rectangle.getManualMovements().size());
	}
	
	@Test
	public void testBoundaryBehaviours()
	{
		rectangle.setBoundaryMovement(boundaryBehavior);
		assertEquals(boundaryBehavior, rectangle.getBoundaryMovement());
	}
			
}
