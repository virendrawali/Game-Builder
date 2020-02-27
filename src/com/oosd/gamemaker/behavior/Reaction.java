package com.oosd.gamemaker.behavior;

import java.io.Serializable;

import com.oosd.gamemaker.models.Sprite;

public abstract class Reaction implements Serializable{
	private static final long serialVersionUID = 2757834906244628652L;
	
	Sprite primary;
	Sprite secondary;
	
	public Sprite getSecondary() {
		return secondary;
	}
	
	Sound sound;
	
	public Reaction(Sprite primary, Sprite secondary, Sound sound) {
		super();
		this.primary = primary;
		this.secondary = secondary;
		this.sound = sound;
	}
	
	public abstract boolean react();
	
	public boolean doesReact(Sprite primary, Sprite secondary) {
		return primary.getShape().getBounds2D().intersects(secondary.getShape().getBounds2D());	
	}
}
