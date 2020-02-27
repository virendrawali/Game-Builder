package com.oosd.gamemaker.behavior;

import org.junit.Before;
import org.junit.Test;

public class SoundTest {
	
	private Sound sound;
	
	@Before
	public void setup() {
		String path = System.getProperty("user.dir");
		sound = new Sound(path + "/resources/sampleSound.wav");
	}
	
	/**
	 * **Just observe whether the sound is playing while running test suite.
	 * ** This is the only way to observe it, since we are not using Dependency injection
	 *  or any variable to check it has played the sound or not. 
	 * ** It would take a major surgery to implement this. So ignoring for now and 
	 *  only calling the method. 
	 */
	@Test
	public void testPlaySound() {
		sound.playSound();	
	}
	
	@Test
	public void testPlaySoundException() {
		sound = new Sound("invalidFile.wav");
		sound.playSound();	
	}

}
