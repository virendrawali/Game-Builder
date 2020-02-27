package com.oosd.gamemaker.behavior;
import com.oosd.gamemaker.Playground;
import com.oosd.gamemaker.models.Sprite;

public class BoundaryBounce implements BoundaryBehavior{
	/**
	 * 
	 */
	private static final long serialVersionUID = 235719717468226050L;

	public void reactWithBoundary(Sprite sprite, Playground playground)
	{
		int xMin = sprite.getX();
		int yMin = sprite.getY();
		int xMax = sprite.getX() + sprite.getWidth();
		int yMax = sprite.getY() + sprite.getHeight();
		if (xMin < 0 || xMax > playground.getWidth()+20)
		{
			sprite.setDx(-sprite.getDx());
		}
		if (yMin < 0 || yMax > playground.getHeight()-20)
		{
			sprite.setDy(-sprite.getDy());
		}
		
	}
}



