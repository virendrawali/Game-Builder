package com.oosd.gamemaker.behavior;

import com.oosd.gamemaker.models.Sprite;

public class Explode extends Reaction {
	
	private static final long serialVersionUID = -6234868905842258823L;
	public Explode(Sprite primary, Sprite secondary, Sound sound) {
		super(primary, secondary, sound);
	}
	
	@Override
	public boolean react() {
		if(doesReact(primary, secondary))
		{
			secondary.setStatus("Dead");
			secondary.setDx(0);
			secondary.setDy(0);
			if(sound != null) {
				sound.playSound();
			}
			return true;
		}
		return false;
	}


}
