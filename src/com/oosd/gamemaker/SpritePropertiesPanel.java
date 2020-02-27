package com.oosd.gamemaker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import com.oosd.gamemaker.behavior.AutomaticMovement;
import com.oosd.gamemaker.behavior.BoundaryBounce;
import com.oosd.gamemaker.behavior.BoundaryRotate;
import com.oosd.gamemaker.behavior.ChangeColor;
import com.oosd.gamemaker.behavior.ClickExplode;
import com.oosd.gamemaker.behavior.ClockTick;
import com.oosd.gamemaker.behavior.ManualMovement;
import com.oosd.gamemaker.behavior.Movement;
import com.oosd.gamemaker.models.Ball;
import com.oosd.gamemaker.models.DigitalClock;
import com.oosd.gamemaker.models.Picture;
import com.oosd.gamemaker.models.Rectangle;
import com.oosd.gamemaker.models.Sprite;

public class SpritePropertiesPanel extends PanelMaker implements ActionListener{
	private static final long serialVersionUID = -1605212137510886388L;
	Maker maker;
	Sprite newSprite;
	int spritePropertyX;
	int spritePropertyY;
	int dx;
	int dy; 
	int index;
	String boundaryReaction;
	String path;
	public SpritePropertiesPanel(Maker maker)
	{
		this.maker=maker;
		this.setLayout(null);
	}
	
	public SpritePropertiesPanel(Maker maker, int index)
	{
		this.maker=maker;
		this.setLayout(null);
		this.index = index;
	}
	
	public void addSprite(int spriteIndex) {
		String x = textboxes.get(0).getText().isEmpty()?"0":textboxes.get(0).getText();
		String y = textboxes.get(1).getText().isEmpty()?"0":textboxes.get(1).getText();
		String height = textboxes.get(2).getText().isEmpty()?"0":textboxes.get(2).getText();
		String width = textboxes.get(3).getText().isEmpty()?"0":textboxes.get(3).getText();
		String spriteDx = textboxes.get(4).getText().isEmpty()?"0":textboxes.get(4).getText();
		String spriteDy = textboxes.get(5).getText().isEmpty()?"0":textboxes.get(5).getText();
		int boundaryBehavior = comboBoxes.get(0).getSelectedIndex();
		int mouseEvent = comboBoxes.get(3).getSelectedIndex();
		if(spriteIndex == 0) {
			newSprite = new Ball(Color.BLUE, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(height), Integer.parseInt(width),Integer.parseInt(spriteDx),Integer.parseInt(spriteDy));
		}
		
		else if(spriteIndex == 1) {
			newSprite = new Rectangle(Color.RED, Integer.parseInt(x), Integer.parseInt(y),  Integer.parseInt(width),Integer.parseInt(height),Integer.parseInt(spriteDx),Integer.parseInt(spriteDy) );
			
		}
		else if(spriteIndex == 3) {
			newSprite = new DigitalClock(Integer.parseInt(x), Integer.parseInt(y));
		}
		
		else if(spriteIndex == 2) {
			
			newSprite = new Picture( Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(height), Integer.parseInt(width),Integer.parseInt(spriteDx),Integer.parseInt(spriteDy),path);
		}
		
		for(Movement manual :manualMovements) {
			newSprite.setManualMovement(manual);
		}
		if(spriteIndex == 3) {
			newSprite.setAutomaticMovement(new ClockTick());
		}
		else {
			boolean directionX = checkBoxes.get(2).isSelected();
			boolean directionY = checkBoxes.get(3).isSelected();
			if(directionX || directionY) {
				newSprite.setAutomaticMovement(new AutomaticMovement(directionX, directionY));
			}else {
				newSprite.setAutomaticMovement(new AutomaticMovement(true, true));
			}
			if(boundaryBehavior == 0) {
				   newSprite.setBoundaryMovement(new BoundaryBounce());
			}
			else if(boundaryBehavior == 1) {
				newSprite.setBoundaryMovement(new BoundaryRotate());
			}
			else if(boundaryBehavior == 2) {
				//vanish
			}
		}
		
		if(mouseEvent == 1) {
			newSprite.setMouseClickBehaviour(new ChangeColor(newSprite, Color.BLUE));
		}else if(mouseEvent == 2) {
			newSprite.setMouseClickBehaviour(new ClickExplode(newSprite));
		}
		newSprite.setWillShoot(checkBoxes.get(0).isSelected());
		newSprite.setShootEffect(checkBoxes.get(1).isSelected());
		maker.allItems.add(newSprite);
		JPanel listPanel = new ListPanel(maker.allItems, maker);
		listPanel.setSize(200, 200);
		listPanel.setLocation(10, 500);
		maker.remove(maker.getListPanel());
		maker.setListPanel(listPanel);
		maker.add(listPanel);
		maker.setVisible(false);
		maker.setVisible(true);
	}
	public void drawSpritePropertiesPanel()
	{
		int currentIndex = maker.getCurrentSpriteIndex();
		maker.addLabel("Location", 10, 50,this);
		maker.addLabel("x", 180, 50,this); 
		addTextBox(200,50,this);  
		maker.addLabel("y", 280, 50,this); 
		addTextBox(300,50,this);
		
		maker.addLabel("Dimensions", 10, 70, this);
		maker.addLabel("Height", 140, 70, this); //4
		addTextBox(200,70, this);  //5
		maker.addLabel("Width", 250, 70,this); //6
		addTextBox(300,70,this); //7
		
		maker.addLabel("Automatic Movement", 10, 90,this ); //8
		maker.addLabel("dx", 180, 90, this); //9
		addTextBox(200,90, this); //10
		maker.addLabel("dy", 250, 90, this); //11
		addTextBox(270,90, this); //12
		maker.addLabel("Boundary Reaction", 10, 150, this); //13
		addCombobox(new ComboItem[] { new ComboItem("Bounce", 0) , new ComboItem("Rotate", 1) , new ComboItem("Vanish", 2)}, 200, 150,this); //2
		
		maker.addLabel("Keypress", 10, 170, this); //15
		addCombobox(new ComboItem[] { new ComboItem("Up", KeyEvent.VK_UP) , new ComboItem("Down", KeyEvent.VK_DOWN) , new ComboItem("Left",  KeyEvent.VK_LEFT), new ComboItem("Right", KeyEvent.VK_RIGHT)}, 200, 170,this); //2
		maker.addLabel("Movement", 10, 190, this); //17 
		
		addCombobox(new ComboItem[] { new ComboItem("Up", 0) , new ComboItem("Down", 1) , new ComboItem("Left", 2), new ComboItem("Right", 3)}, 200, 190,this); //2
		addButtonToPanel("Add Manual Movement", 10, 220,this); //19 //button 5
		
		maker.addLabel("On Click", 10, 250, this);
		addCombobox(new ComboItem[] {new ComboItem("None", 0), new ComboItem("Change Color", 1), new ComboItem("Explode", 2)}, 200, 250,this); //2
		
		addCheckBox("Will Shoot?", 10, 280, this);
		addCheckBox("Shoot Affect?", 200, 280, this);
		addCheckBox("X", 10, 120, this);
		addCheckBox("Y", 200, 120, this);
		
		addButtonToPanel("Add Component", 10, 320,this);//24 //button 6
		if(currentIndex == 2) {
			addButtonToPanel("Choose Image", 10, 20,this);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == buttons.get(1)) {
			
			addSprite(maker.getCurrentSpriteIndex());
		}
		else if(arg0.getSource() == buttons.get(0)) {
			Object keyItem = comboBoxes.get(1).getSelectedItem();
			int keyCode = ((ComboItem)keyItem).getValue();
			Movement spriteManual = null;
			Object actionItem = comboBoxes.get(2).getSelectedItem();
			int itemCode = ((ComboItem)actionItem).getValue();

			spriteManual = new ManualMovement(keyCode,itemCode);
	        manualMovements.add(spriteManual);
		}
		else if(arg0.getSource() == buttons.get(2)) {
			path = System.getProperty("user.dir");
			JFileChooser jfc = new JFileChooser(new File(path));
			jfc.setDialogTitle("Choose Background Theme");
			jfc.setMultiSelectionEnabled(true);
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				 File selectedFile = jfc.getSelectedFile();
				 this.path = selectedFile.getAbsolutePath();
				 
			}
		}
	}
}
