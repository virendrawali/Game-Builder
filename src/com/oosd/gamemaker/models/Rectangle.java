package com.oosd.gamemaker.models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Sprite{
	private static final long serialVersionUID = -1245882985717282226L;
	int initialPositionX;
	int initialPositionY;
	List<Integer> historyX = new ArrayList<>();
	int countX = 0;
	int totalCount = 0;
	Boolean flag = false;
	private static int counter = 0;
	
	public Rectangle(Color color, int initialPositionX, int initialPositionY, int width, int height, int dx, int dy) {
		this.color = color;
		this.initialPositionX = initialPositionX;
		this.initialPositionY = initialPositionY;
		this.width = width;
		this.height = height;
		this.setDx(dx);
		this.setDy(dy);
		this.update(initialPositionX, initialPositionY);
		StringBuilder strName = new StringBuilder("Rectangle ");
		strName.append(++counter);
		this.name = strName.toString();
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		shape = new Rectangle2D.Double(locationX, locationY , width, height);
		g2d.setColor(this.color);
		g2d.fill(shape);		
	}
	
}
