package com.oosd.gamemaker.behavior;

import com.oosd.gamemaker.models.Sprite;

public class ClickExplode implements MouseClickBehaviour {
	private static final long serialVersionUID = 1L;
	private Sprite sprite;
	
	public ClickExplode(Sprite sprite) {
		this.sprite = sprite;
		
	}
	
	@Override
	public void respondToClick() {
		this.sprite.setStatus("Dead");
	}

}
