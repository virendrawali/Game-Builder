package com.oosd.gamemaker.models;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class DigitalClock extends Sprite{
	
	private static final long serialVersionUID = -3083793706362721926L;
	private int positionX;
	private int positionY;
	private static int counter = 0;
	int currMinutes;
	int currSeconds;
	
	public DigitalClock(int positionX, int positionY)
	{
		this.positionX = positionX;
		this.positionY = positionY; 
		this.update(0,0); 
		StringBuilder strName = new StringBuilder("Clock ");
		strName.append(++counter);
		this.name = strName.toString();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		Font f = new Font("Dialog", Font.PLAIN, 30);
		g2d.setFont(f);
		g2d.drawString(locationX + ":" + locationY, positionX, positionY);
		
	}
	
	public int getCurrMinutes() {
		return locationX;
	}

	public void setCurrMinutes(int currMinutes) {
		this.currMinutes = currMinutes;
	}

	public int getCurrSeconds() {
		return locationY;
	}

	public void setCurrSeconds(int currSeconds) {
		this.currSeconds = currSeconds;
	}


}
