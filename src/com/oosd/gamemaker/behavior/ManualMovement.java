package com.oosd.gamemaker.behavior;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.oosd.gamemaker.models.Sprite;

public class ManualMovement implements Movement, KeyEventDispatcher {

	private static final long serialVersionUID = 8062557088943136222L;
	
	static final Logger logger = Logger.getLogger(ManualMovement.class);
	
	private Boolean isLeft = false;
	private Boolean isRight = false;
	private Boolean isDown = false;
	private Boolean isUp = false;
	
	private Boolean isLeftCode = false;
	private Boolean isRightCode = false;
	private Boolean isDownCode = false;
	private Boolean isUpCode = false;

	private int key;
	
	public ManualMovement(int key, int directionCode) {
		super();
		this.key = key;
		switch(directionCode)
		{
			case(0):
				this.isUpCode = true;
				break;
			case(1):
				this.isDownCode = true;
				break;
			case(2):
				this.isLeftCode = true;
				break;
			case(3):
				this.isRightCode = true;
				break;
			default:
				logger.debug("Invalid direction code: " + directionCode);
		}
		
		
	}
	
	@Override
	public void move(Sprite sprite, JPanel playground) {
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher((KeyEventDispatcher) this);
        
		int positionY = sprite.getY();
		int positionX = sprite.getX();
		
		int maxRight = playground.getWidth();
		int maxBottom = playground.getHeight() - 20;
		
		
		int endpositionX = positionX + sprite.getWidth();
		int endpositionY = positionY + sprite.getHeight();
		
		
		int dx = 1;
		int dy = 1;
		
		
		if (isLeft && positionX > 0) {
			positionX -= dx;
		}
		else if (isRight && endpositionX < maxRight) {
			positionX += dx;
		} 
		else if (isDown && endpositionY < maxBottom) {
			positionY += dy;
		}
		else if(isUp && positionY > 0) {
			positionY -= dy;
		}
		
		sprite.update(positionX, positionY);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getKeyCode() == key) {
			if (e.getID() == KeyEvent.KEY_PRESSED && isLeftCode) {
	            this.isLeft = true;
	        } else if (e.getID() == KeyEvent.KEY_RELEASED && isLeftCode) {
	            this.isLeft = false;
	        } else if (e.getID() == KeyEvent.KEY_PRESSED && isRightCode) {
	            this.isRight = true;
	        } else if (e.getID() == KeyEvent.KEY_RELEASED && isRightCode) {
	            this.isRight = false;
	        } else if (e.getID() == KeyEvent.KEY_PRESSED && isDownCode) {
	            this.isDown = true;
	        } else if (e.getID() == KeyEvent.KEY_RELEASED && isDownCode) {
	            this.isDown = false;
	        }else if (e.getID() == KeyEvent.KEY_PRESSED && isUpCode) {
	            this.isUp = true;
	        } else if (e.getID() == KeyEvent.KEY_RELEASED && isUpCode) {
	            this.isUp = false;
	        }
		}
		return false;
	}

	public Boolean getIsLeft() {
		return isLeft;
	}

	public void setIsLeft(Boolean isLeft) {
		this.isLeft = isLeft;
	}

	public Boolean getIsRight() {
		return isRight;
	}

	public void setIsRight(Boolean isRight) {
		this.isRight = isRight;
	}

	public Boolean getIsDown() {
		return isDown;
	}

	public void setIsDown(Boolean isDown) {
		this.isDown = isDown;
	}

	public Boolean getIsUp() {
		return isUp;
	}

	public void setIsUp(Boolean isUp) {
		this.isUp = isUp;
	}

	public Boolean getIsLeftCode() {
		return isLeftCode;
	}

	public void setIsLeftCode(Boolean isLeftCode) {
		this.isLeftCode = isLeftCode;
	}

	public Boolean getIsRightCode() {
		return isRightCode;
	}

	public void setIsRightCode(Boolean isRightCode) {
		this.isRightCode = isRightCode;
	}

	public Boolean getIsDownCode() {
		return isDownCode;
	}

	public void setIsDownCode(Boolean isDownCode) {
		this.isDownCode = isDownCode;
	}

	public Boolean getIsUpCode() {
		return isUpCode;
	}

	public void setIsUpCode(Boolean isUpCode) {
		this.isUpCode = isUpCode;
	}

	@Override
	public void setUniDirectionMovementX(boolean uniDirectionX) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void setUniDirectionMovementY(boolean uniDirectionY) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public boolean isUniDirectionMovementX() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isUniDirectionMovementY() {
		throw new UnsupportedOperationException();
	}
	
	


}