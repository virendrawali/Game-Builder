package com.oosd.gamemaker.behavior;

import com.oosd.gamemaker.models.Sprite;

public class ChangeDirection extends Reaction {
	
	private static final long serialVersionUID = 1L;

	public ChangeDirection(Sprite primary, Sprite secondary, Sound sound) {
		super(primary, secondary, sound);
	}

	@Override
	public boolean react() {
		if(doesReact(primary, secondary))
		{
			Movement movement = secondary.getAutomaticMovement();
			if(movement.isUniDirectionMovementX() && !movement.isUniDirectionMovementY()) {
				movement.setUniDirectionMovementX(false);
				movement.setUniDirectionMovementY(true);
				secondary.setDy(-secondary.getDy());
			}else if(!movement.isUniDirectionMovementX() && movement.isUniDirectionMovementY()) {
				movement.setUniDirectionMovementX(true);
				movement.setUniDirectionMovementY(false);
				secondary.setDx(-secondary.getDx());
			}
			secondary.setX(secondary.getX() - (3* secondary.getDx()));
			secondary.setY(secondary.getY() - (3* secondary.getDy()));
			
			secondary.setAutomaticMovement(movement);
			return true;
		}
		
		return false;
	}

}
