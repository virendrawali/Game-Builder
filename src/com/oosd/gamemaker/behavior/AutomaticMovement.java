package com.oosd.gamemaker.behavior;

import javax.swing.JPanel;

import com.oosd.gamemaker.models.Sprite;

public class AutomaticMovement implements Movement {

	private static final long serialVersionUID = 5512078739763787804L;
	
	private boolean movementX;
	private boolean movementY;
	
	public AutomaticMovement(boolean uniDirectionMovementX, boolean uniDirectionMovementY) {
		this.movementX = uniDirectionMovementX;
		this.movementY = uniDirectionMovementY;
	}
	
	public boolean isUniDirectionMovementX() {
		return movementX;
	}

	public void setUniDirectionMovementX(boolean uniDirectionMovementX) {
		this.movementX = uniDirectionMovementX;
	}

	public boolean isUniDirectionMovementY() {
		return movementY;
	}

	public void setUniDirectionMovementY(boolean uniDirectionMovementY) {
		this.movementY = uniDirectionMovementY;
	}

	@Override
	public void move(Sprite sprite, JPanel playground) {
		int positionX = sprite.getX();
		int positionY = sprite.getY();
		if(movementX && !movementY)
		{
			positionX = sprite.getX() + sprite.getDx();
		}
		else if(movementY && !movementX)
		{
			positionY = sprite.getY()+ sprite.getDy();
		}else {
			positionX = sprite.getX() + sprite.getDx();
			positionY = sprite.getY()+ sprite.getDy();
		}

		sprite.update(positionX, positionY);
	}

}
