package com.oosd.gamemaker.behavior;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

@SuppressWarnings("restriction")
public class Sound implements Serializable {

	private static final long serialVersionUID = 1L;

	static final Logger logger = Logger.getLogger(Sound.class);
	private String audiopath;

	public Sound(String audiopath)
	{
		this.audiopath = audiopath;
	}

	public void playSound()
	{
		try {
			InputStream music = new FileInputStream(new File(audiopath));
			AudioStream audio = new AudioStream(music);
			AudioPlayer.player.start(audio);
		} catch (IOException e) {
			logger.debug("Exception occured while playing sound: " + e.getMessage());
		}

	}
}
