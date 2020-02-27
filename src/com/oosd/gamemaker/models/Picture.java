package com.oosd.gamemaker.models;

import java.awt.Graphics2D;
import java.awt.Image;

import com.oosd.gamemaker.behavior.ImageDecoder;
import com.oosd.gamemaker.behavior.ImageEncoder;
import java.awt.geom.Rectangle2D;

public class Picture extends Sprite{
	private static final long serialVersionUID = 6449562524584478972L;
	private String imageString;
	private static int counter = 0;
	public Picture( int initialPositionX, int initialPositionY, int imageHeight, int imageWidth,int dx, int dy, String fileName)
	{	
		this.height = imageHeight;
		this.width = imageWidth;
		this.update(initialPositionX, initialPositionY);
		this.setDx(dx);
		this.setDy(dy);
		ImageEncoder imageEncodeObj = new ImageEncoder(fileName);
		this.imageString = imageEncodeObj.encodeToString(imageEncodeObj.getBufferedImage());
		StringBuilder strName = new StringBuilder("Picture ");
		strName.append(++counter);
		this.name = strName.toString();
	}


	@Override
	public void draw(Graphics2D g2d) {
		super.shape = new Rectangle2D.Double(locationX, locationY, width, height);
		ImageDecoder imageDecodeObj = new ImageDecoder();
		Image image = imageDecodeObj.decodeToImage(this.imageString);
        g2d.drawImage(image,locationX,locationY,width,height,null);
	}

}
