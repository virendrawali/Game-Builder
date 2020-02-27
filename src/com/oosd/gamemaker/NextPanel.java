package com.oosd.gamemaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import com.oosd.gamemaker.behavior.BounceBack;
import com.oosd.gamemaker.behavior.ChangeDirection;
import com.oosd.gamemaker.behavior.Explode;
import com.oosd.gamemaker.behavior.Reaction;
import com.oosd.gamemaker.behavior.Sound;

import sun.audio.AudioStream;

@SuppressWarnings("restriction")
public class NextPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = -6529035796591203451L;
	ArrayList<JComboBox<ComboItem>> comboBoxes = new ArrayList<>() ;
	private ArrayList<JButton> buttons = new ArrayList<>();
	Maker maker;
	Sound sound;
	String audiopath;
	
	AudioStream audio = null;
	
	private List<Reaction> anyWinReactions;
	private List<Reaction> anyLooseReactions;
	private List<Reaction> allWinReactions;
	private List<Reaction> allLooseReactions;
	
	public NextPanel(Maker maker) {
		int spriteCount = maker.getAllItems().getAllSprites().size();
		ComboItem[] sprites = new ComboItem[spriteCount];
		
		anyLooseReactions = new ArrayList<>();
		anyWinReactions = new ArrayList<>();
		
		allWinReactions = new ArrayList<>();
		allLooseReactions =  new ArrayList<>();
		
		for(int i = 0; i < spriteCount; i++) {
			sprites[i] = new ComboItem(maker.getAllItems().getAllSprites().get(i).getName(),i);
		}
		addCombobox(new ComboItem[] { new ComboItem("Bounce Back", 0) , new ComboItem("Explode", 1), new ComboItem("Change Direction", 2)} , 10, 10, this);
		addCombobox(sprites, 30, 10, this);
		addCombobox(sprites, 50, 10, this);
		
		addCombobox(new ComboItem[] { new ComboItem("Win", 0) , new ComboItem("Loose", 1)} , 70, 10, this);
		addCombobox(new ComboItem[] { new ComboItem("All", 0) , new ComboItem("Any", 1)} , 90, 10, this);
		
		addButtonToPanel("Add Sound", 40, 170, this);
		addButtonToPanel("Add Reaction", 70, 170, this);
		this.maker=maker;
	}
	
	public void addCombobox(ComboItem []items, int x, int y, JPanel panel) {
		JComboBox<ComboItem> combo = new JComboBox<>(items);
		combo.setBounds(x, y, 100, 20);
		combo.setMaximumSize(combo.getPreferredSize());
		comboBoxes.add(combo);
		panel.add(combo);
	}
	public void addButtonToPanel(String name, int x, int y, JPanel panel) {
		JButton button = new JButton(name);
		button.addActionListener(this);
		button.setVisible(true);
		button.setBounds(x, y, 200, 20);
		panel.add(button);
		buttons.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttons.get(1))
		{
			Object reactionItem = comboBoxes.get(0).getSelectedItem();
			int reactionCode = ((ComboItem)reactionItem).getValue();
			Object spriteItem1 = comboBoxes.get(1).getSelectedItem();
			int spriteCode1 = ((ComboItem)spriteItem1).getValue();
			Object spriteItem2 = comboBoxes.get(2).getSelectedItem();
			int spriteCode2 = ((ComboItem)spriteItem2).getValue();
			
			String winLooseFlags = comboBoxes.get(3).getSelectedItem().toString();
			String anyAllFlags = comboBoxes.get(4).getSelectedItem().toString();
			
			Reaction reaction = null; 
			
			if(reactionCode == 0) {
				reaction = (new BounceBack(maker.getAllItems().getAllSprites().get(spriteCode1), maker.getAllItems().getAllSprites().get(spriteCode2), sound));
			}
			else if(reactionCode == 1) {
				reaction = (new Explode(maker.getAllItems().getAllSprites().get(spriteCode1), maker.getAllItems().getAllSprites().get(spriteCode2), sound));
			}else if(reactionCode == 2) {
				reaction =  new ChangeDirection(maker.getAllItems().getAllSprites().get(spriteCode1), maker.getAllItems().getAllSprites().get(spriteCode2), sound);
			}
			
			maker.addReaction(reaction);
			
			addRectionToList(anyAllFlags,winLooseFlags, reaction);
			
			maker.getAllItems().getWinMap().put("Any",anyWinReactions);
			maker.getAllItems().getWinMap().put("All",allWinReactions);
			
			maker.getAllItems().getLooseMap().put("Any",anyLooseReactions);
			maker.getAllItems().getLooseMap().put("All",allLooseReactions);
		}
		
		else if(e.getSource()==buttons.get(0))
		{
			String path = System.getProperty("user.dir");
			JFileChooser jfc = new JFileChooser(new File(path));
			jfc.setDialogTitle("Choose Background Sound");
			jfc.setMultiSelectionEnabled(true);
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				 File selectedFile = jfc.getSelectedFile();
				 this.audiopath = selectedFile.getAbsolutePath();
				 sound = new Sound(audiopath);
			}
		}
	}
	
	public void addRectionToList(String anyAllCondition, String winLooseCondition, Reaction reaction)
	{
		if(anyAllCondition.equals("Any"))
		{
			if(winLooseCondition.equals("Win"))
			{
				anyWinReactions.add(reaction);
			}
			else
			{
				anyLooseReactions.add(reaction);
			}
		}
		else
		{
			if(winLooseCondition.equals("Win"))
			{
				allWinReactions.add(reaction);
			}
			else
			{
				allLooseReactions.add(reaction);
			}
		}
	}

	public String getAudiopath() {
		return audiopath;
	}
		
}
