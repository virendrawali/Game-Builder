package com.oosd.gamemaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.oosd.gamemaker.behavior.Reaction;
import com.oosd.gamemaker.models.Composite;

public class LevelObject implements Serializable {
	
	private static final long serialVersionUID = 8142107465518012231L;
	
	private static int level=0;
	private String selectedPath;
	private List<Reaction> reactions = new ArrayList<>();
	private Composite allSprites;
	
	
	public LevelObject(List<Reaction> reactions, Composite allSprites, String selectedPath) {
		super();
		this.reactions = reactions;
		this.allSprites = allSprites;
		this.selectedPath = selectedPath;
	}
	
	public List<Reaction> getReactions() {
		return reactions;
	}
	public void addReaction(Reaction reaction) {
		this.reactions.add(reaction);
	}
	
	public Composite getSprites() {
		return allSprites;
	}
	
	public String getSelectedPath() {
		return selectedPath;
	}

	public void setSelectedPath(String selectedPath) {
		this.selectedPath = selectedPath;
	}

	public static int getLevel() {
		return level;
	}

	
}
