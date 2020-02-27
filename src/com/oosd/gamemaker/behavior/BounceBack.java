package com.oosd.gamemaker.behavior;

import com.oosd.gamemaker.models.Sprite;

public class BounceBack extends Reaction {

	public BounceBack(Sprite primary, Sprite secondary, Sound sound) {
		super(primary, secondary, sound);
	}

	private static final long serialVersionUID = 3809186569001206218L;
		
	@Override
	
	public boolean react() {
		
		if(doesReact(primary, secondary))
		{

			int secondaryYCenter = secondary.getY() + secondary.getHeight()/2;
			if(primary.getY() <= secondaryYCenter && secondaryYCenter <= (primary.getY()+primary.getHeight()) ) {
				secondary.setDx(-secondary.getDx());
			}
			else {
				secondary.setDy(-(secondary.getDy()));
			}
			if(sound!= null)
				sound.playSound();
			return true;
		}
		return false;
	}

}
