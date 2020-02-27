package com.oosd.gamemaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.oosd.gamemaker.behavior.Movement;
import com.oosd.gamemaker.behavior.Reaction;
import com.oosd.gamemaker.models.Composite;
import com.oosd.gamemaker.models.Sprite;
import javax.swing.JFileChooser;
import java.io.File;

public class Maker extends PanelMaker implements ActionListener {
	private static final long serialVersionUID = 1L;
	Composite allItems;
	Sprite newSprite;
	int locX;
	int locY;
	int dx;
	int dy;
	String boundaryReaction;
	String path ;
	private  String selectedpath;
	JPanel listPanel = new JPanel();
	Image image;
	private List<LevelObject> levelObjects=new ArrayList<>();
	int currentLevel=0;
	int currentSpriteIndex=-1;
	
	private DataOperations dataOperations;
	
	public Maker() {
		allItems = new Composite();
		this.setLayout(null);
		dataOperations = new DataOperations();
	}
	public JPanel getListPanel() {
		return listPanel;
	}
	public void setListPanel(JPanel listPanel) {
		this.listPanel = listPanel;
	}
	public List<LevelObject> getLevelObjects() {
		return levelObjects;
	}
	public void setLevelObjects(List<LevelObject> levelObjects) {
		this.levelObjects = levelObjects;
	}
	public void addReaction(Reaction reaction) {
		this.reactions.add(reaction);
	}
	public List<Reaction> getReactions() {
		return reactions;
	}

	public int getCurrentSpriteIndex() {
		return currentSpriteIndex;
	}
	public void makeGame() {
		levelObjects.add(new LevelObject(reactions,allItems,selectedpath));
		addLabel("You are creating level "+currentLevel,10,10,this);
		addLabel("Add a new Component", 10, 30,this);
		addLabel("Component Type", 10, 50,this);
		addButtonToPanel("Circle",60,70,this);
		addButtonToPanel("Rectangle",60,120,this);
		addButtonToPanel("Image",200,70,this);
		addButtonToPanel("Clock",200,120,this);
		addButtonToPanel("Add Reaction", 120, 200,this);
		addButtonToPanel("Choose Theme",120, 240,this); 
		addButtonToPanel("Add Level", 120, 280, this);
		addButtonToPanel("Save", 120, 320, this);
		addButtonToPanel("Load", 120, 360, this);
		addTextBox(60,410, this);
		addButtonToPanel("Go To Level", 120,400, this);
	}
		
	@Override
	public void addTextBox(int x, int y, JPanel panel) {
		JTextField  textbox = new JTextField();
		textbox.setBounds(x,y,50,20);
		textbox.addActionListener(this);
		textboxes.add(textbox);
		panel.add(textbox);
		
	}
	@Override
	public void addButtonToPanel(String name, int x, int y, JPanel panel) {
		JButton button = new JButton(name);
		button.addActionListener(this);
		button.setVisible(true);
		button.setBounds(x, y, 110, 40);
		button.setFont(new Font("Monaco", Font.PLAIN, 13));
		panel.add(button);
		buttons.add(button);
	}

	public Composite getAllItems() {
		return allItems;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		SaveObject saveObject;
		if(arg0.getSource()==buttons.get(0) || arg0.getSource()==buttons.get(1) ||arg0.getSource()==buttons.get(2) ||arg0.getSource()==buttons.get(3))
		{	
			JFrame frame1 = new JFrame();
			if(arg0.getSource()==buttons.get(0))
			{
				frame1.setTitle("New Circle");
				currentSpriteIndex=0;
			}
			else if(arg0.getSource()==buttons.get(1))
			{	
				frame1.setTitle("New Rectange");
				currentSpriteIndex=1;
			}
			else if(arg0.getSource()==buttons.get(2))
			{	
				frame1.setTitle("New Image");
				currentSpriteIndex=2;
			}
			else if(arg0.getSource()==buttons.get(3))
			{
				frame1.setTitle("New Clock");
				currentSpriteIndex=3;
			}
			frame1.setSize(400,400);
			SpritePropertiesPanel spritePropertiesPanelObject=new SpritePropertiesPanel(this);
			spritePropertiesPanelObject.setSize(200,800);
			spritePropertiesPanelObject.setLocation(10,10);
			spritePropertiesPanelObject.setBackground(Color.decode("#ADD8E6"));
			frame1.add(spritePropertiesPanelObject);
			frame1.setVisible(true);
			spritePropertiesPanelObject.drawSpritePropertiesPanel();	
		}
		else if(arg0.getSource() == getButtons().get(4)) {
			JFrame nextFrame = new JFrame();
			JPanel nextPanel = new NextPanel(this);
			nextFrame.add(nextPanel);
			nextPanel.setSize(400, 400);
			nextFrame.setLocation(0, 0);
			nextFrame.setSize(400, 400);
			nextPanel.setBackground(Color.decode("#ADD8E6"));
			nextFrame.setVisible(true);
		}

		else if(arg0.getSource() == getButtons().get(5)) {
			
			path = System.getProperty("user.dir");
			JFileChooser jfc = new JFileChooser(new File(path));
			jfc.setDialogTitle("Choose Background Theme");
			jfc.setMultiSelectionEnabled(true);
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				 File selectedFile = jfc.getSelectedFile();
				 this.selectedpath = selectedFile.getAbsolutePath();
				 levelObjects.get(currentLevel).setSelectedPath(this.selectedpath);
			}
		}
		else if(arg0.getSource() == getButtons().get(6))
		{
			allItems=new Composite();
			reactions=new ArrayList<Reaction>();
			selectedpath = null;
			levelObjects.add(new LevelObject(reactions, allItems,selectedpath));
			currentLevel++;
			labels.get(0).setText("You are on level "+currentLevel);
		}
		else if(arg0.getSource() == getButtons().get(7)) {
			saveObject = new SaveObject(levelObjects);
			dataOperations.writeObjectToFile(saveObject);
		}
		else if(arg0.getSource() == getButtons().get(8)) {
			saveObject = dataOperations.readObjectFromFile();
			this.levelObjects = saveObject.getLevelObjects();
			this.allItems = this.levelObjects.get(currentLevel).getSprites();
			JPanel listsPanel = new ListPanel(this.allItems, this);
			listsPanel.setSize(200, 200);
			listsPanel.setLocation(10, 500);
			this.setListPanel(listsPanel);
			this.remove(this.getListPanel());
			this.setListPanel(listsPanel);
			this.add(listsPanel);
			this.setVisible(false);
			this.setVisible(true);
		}
		else if(arg0.getSource() == getButtons().get(9)) {
			String goToLevel = textboxes.get(0).getText().isEmpty()?"0":textboxes.get(0).getText();
			currentLevel=Integer.parseInt(goToLevel);
			labels.get(0).setText("You are on level "+currentLevel);
		}
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public String getSelectedpath() {
		return selectedpath;
	}
	
	
	public List<Movement> getManualMovements() {
		return manualMovements;
	}

	public List<JButton> getButtons() {
		return buttons;
	}
	
}

class ComboItem
{
    private String label;
    private int value;

    public ComboItem(String label, int value)
    {
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return label;
    }

    public String getKey()
    {
        return label;
    }


    public int getValue()
    {
        return value;
    }
}
