package com.oosd.gamemaker.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.oosd.gamemaker.Maker;

public class MakerTest {

	private Maker mk = new Maker();
	@Test
	public void test() {
		mk.makeGame();
		int check = mk.getButtons().size();
		assertEquals(10, check);
		
	}

}
