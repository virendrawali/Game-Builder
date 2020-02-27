package com.oosd.gamemaker.behavior;

import java.io.Serializable;

import javax.swing.JPanel;

import com.oosd.gamemaker.models.Sprite;

public interface Movement extends Serializable {
	void move(Sprite sprite, JPanel playground );
	
	public void setUniDirectionMovementX(boolean uniDirectionX);
	public void setUniDirectionMovementY(boolean uniDirectionY);
	public boolean isUniDirectionMovementX();
	public boolean isUniDirectionMovementY();
}
