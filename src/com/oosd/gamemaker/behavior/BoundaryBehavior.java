package com.oosd.gamemaker.behavior;
import java.io.Serializable;

import com.oosd.gamemaker.Playground;
import com.oosd.gamemaker.models.Sprite;

public interface BoundaryBehavior extends Serializable {
	public abstract void reactWithBoundary(Sprite sprite, Playground playground);
}

