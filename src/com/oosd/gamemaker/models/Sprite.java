package com.oosd.gamemaker.models;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.oosd.gamemaker.Playground;
import com.oosd.gamemaker.behavior.BoundaryBehavior;
import com.oosd.gamemaker.behavior.MouseClickBehaviour;
import com.oosd.gamemaker.behavior.Movement;

public abstract class Sprite implements Serializable{
	private static final long serialVersionUID = 8440651746666951358L;
	protected Shape shape;
	protected ArrayList<Movement>  manualMovements = new ArrayList<>();
	private Movement automaticMovement;
	private boolean isGamePaused = true;
	private int dx = 1;
	private int dy = 1;
	protected int locationX;
	protected int locationY;
	protected String name;
	protected int height;
	protected int width;
	protected BoundaryBehavior boundaryBehavior;
	private boolean firstUpdate = true;
	private boolean willShoot = false;
	private boolean shootEffect = false;
	protected Color color; 
	protected MouseClickBehaviour mouseClickBehaviour;
	private boolean hasMouseBehaviour = false;
	private String status;
	
	public Sprite()
	{
		this.status = "created";
	}
	
	
	public abstract void draw(Graphics2D g2d);
	public int getLocationX() {
		return locationX;
	}
	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}
	public int getLocationY() {
		return locationY;
	}
	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}
	public boolean isShootEffect() {
		return shootEffect;
	}
	public void setShootEffect(boolean shootEffect) {
		this.shootEffect = shootEffect;
	}
	public boolean isWillShoot() {
		return willShoot;
	}
	public void setWillShoot(boolean willShoot) {
		this.willShoot = willShoot;
	}
	
	public void update(int x, int y ) {
		if(!isGamePaused() || firstUpdate) {
			this.locationX = x;
			this.locationY = y;
		}	
		if(firstUpdate) {
			firstUpdate = false;
		}
	}
	public String getName() {
		return name;
	}
	public int getX() {
		return locationX;
	}
	
	public void setX(int x) {
		this.locationX=x;
	}
	
	public void setY(int y) {
		this.locationY=y;
	}

	public int getY() {
		return locationY;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public int getDx() {
		return dx;
	}
	public int getDy() {
		return dy;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void setManualMovement(Movement movement) {
		this.manualMovements.add(movement);
	}
	
	public void move(Playground playground) {
		for(Movement movement : getManualMovements()) {
			movement.move(this, playground);
		}
		if(boundaryBehavior != null) {
			boundaryBehavior.reactWithBoundary(this, playground);
		}
		if(automaticMovement != null) {
			automaticMovement.move(this, playground);
		}
		
	}
	
	public void respondToClick() {
		this.mouseClickBehaviour.respondToClick();
	}
	public void setBoundaryMovement(BoundaryBehavior boundaryBehavior) {
		this.boundaryBehavior = boundaryBehavior;
	}
	
	public BoundaryBehavior getBoundaryMovement()
	{
		return this.boundaryBehavior;
	}
	
	public List<Movement> getManualMovements() {
		return manualMovements;
	}
	public Movement getAutomaticMovement() {
		return automaticMovement;
	}
	public void setAutomaticMovement(Movement automaticMovement) {
		this.automaticMovement = automaticMovement;
	}
	
	public MouseClickBehaviour getMouseClickBehaviour() {
		return mouseClickBehaviour;
	}
	public void setMouseClickBehaviour(MouseClickBehaviour mouseClickBehaviour) {
		hasMouseBehaviour = true;
		this.mouseClickBehaviour = mouseClickBehaviour;
	}
	public boolean hasMouseBehaviour() {
		return hasMouseBehaviour;
	}
	public void setHasMouseBehaviour(boolean hasMouseBehaviour) {
		this.hasMouseBehaviour = hasMouseBehaviour;
	}
	public void pause() {
		this.isGamePaused = true;
	}
	public void play(){
		this.isGamePaused = false;
	}
	public boolean isGamePaused() {
		return isGamePaused;
	}
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
