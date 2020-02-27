package com.oosd.gamemaker.models;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.oosd.gamemaker.behavior.Reaction;

public class CompositeTest {
	
	private Composite composite;
	private Map<String,List<Reaction>> winMap;
	private Map<String,List<Reaction>> looseMap;
	private Sprite sprite;
	
	
	@Before
	public void setup() {
		composite =  new Composite();
		winMap = new HashMap<String, List<Reaction>>();
		looseMap = new HashMap<String, List<Reaction>>();
		sprite = new Rectangle(Color.BLACK, 100, 100, 40, 40, 1, 1);
	}
	
	@Test
	public void testSetWinMap()
	{
		composite.setWinMap(winMap);
		assertEquals(winMap, composite.getWinMap());
	}
	
	@Test
	public void testSetLooseMap()
	{
		composite.setLooseMap(looseMap);
		assertEquals(looseMap, composite.getLooseMap());
	}
	
	@Test
	public void testSprites()
	{
		composite.add(sprite);
		assertEquals(1, composite.getAllSprites().size());
		composite.remove(sprite);
		assertEquals(0, composite.getAllSprites().size());
	}
	
	@Test
	public void testPause()
	{
		composite.add(sprite);
		composite.play();
		composite.pause();
		assertEquals(true, composite.getAllSprites().get(0).isGamePaused());
		((Sprite)composite).pause();
		composite.pause();
		assertEquals(true, composite.getAllSprites().get(0).isGamePaused());
	}

	@Test
	public void testShoot()
	{
		sprite.setWillShoot(true);
		composite.add(sprite);
		composite.shoot();
		assertEquals(2, composite.getAllSprites().size());
		
	}
}
