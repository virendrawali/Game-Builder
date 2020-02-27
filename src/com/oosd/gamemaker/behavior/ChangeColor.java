package com.oosd.gamemaker.behavior;

import java.awt.Color;

import com.oosd.gamemaker.models.Sprite;

public class ChangeColor implements MouseClickBehaviour{
	
	private static final long serialVersionUID = 1L;
	private Sprite sprite;
	private Color color;
	
	public ChangeColor(Sprite sprite, Color color) {
		this.sprite = sprite;
		this.color = color;
	}
	
	@Override
	public void respondToClick() {
		sprite.setColor(this.color);
	}
}
