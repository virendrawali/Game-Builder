package com.oosd.gamemaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveObject implements Serializable {
	private static final long serialVersionUID = -2701683955089965424L;
	List<LevelObject> levelObjects = new ArrayList<>();
	
	public SaveObject(List<LevelObject> levelObjects) {
		super();
		this.levelObjects = levelObjects;
	}
	
	public List<LevelObject> getLevelObjects() {
		return levelObjects;
	}
}
