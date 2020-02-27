package com.oosd.gamemaker;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.oosd.gamemaker.behavior.Reaction;
import com.oosd.gamemaker.behavior.ShootBehavior;
import com.oosd.gamemaker.models.Composite;
import com.oosd.gamemaker.models.Sprite;

public class Playground extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyEventDispatcher {

	static final Logger logger = Logger.getLogger(Playground.class);
	private static final long serialVersionUID = 2376859069846492382L;
	private Maker maker;
	private Image image;
	private Composite allItems;
	private JButton startButton;
	private List<Reaction> reactions;
	private Composite deadSprites;
	private List<Sprite> reactedSprites;
	private boolean startGame;

	int objpos;
	
	public Playground(Maker maker)  {
		reactedSprites = new ArrayList<>();
		this.maker = maker;
		this.startGame = true;
		this.allItems = new Composite();
		this.reactions=new ArrayList<>();
		deadSprites = new Composite();
		this.setLayout(null);
		this.setFocusable(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher((KeyEventDispatcher) this);
	}

	public void setBackgroundImage()
	{
		String selectedpath = maker.getLevelObjects().get(maker.getCurrentLevel()).getSelectedPath();
		if(selectedpath != null)
		{
			File sourceimage = new File(selectedpath);
			try {
				image = ImageIO.read(sourceimage);
			} catch (IOException e) {
				logger.debug("Path not foound exception");
			}
		}
		else {
			image = null;
		}
	}

	public void startGame() {
		startButton = new JButton("Play / Pause");
		startButton.addActionListener(this);
		startButton.setVisible(true);
		startButton.setBounds(200, 10, 200, 20);
		startButton.setLayout(null);
		startButton.setLocation(330,620);
		for(Sprite sprite: maker.getLevelObjects().get(maker.getCurrentLevel()).getSprites().getAllSprites())
		{
			sprite.play();
		}
		this.add(startButton);
		while(isStartGame()){

			this.setBackgroundImage();
			this.allItems = maker.getLevelObjects().get(maker.getCurrentLevel()).getSprites();
			this.reactions = maker.getLevelObjects().get(maker.getCurrentLevel()).getReactions();

			for(Sprite sprite: allItems.getAllSprites()) {
				sprite.move(this);
			}
			try
			{
				for(Reaction reaction: reactions) {
					boolean flag = reaction.react();
					if(flag)
					{
						reactedSprites.add(reaction.getSecondary());
					}
				}
			}
			catch (Exception e) {
				logger.debug("Exception occured when checking reactions " + e.getMessage());
			}

			for(Sprite sprite: allItems.getAllSprites()) {
				if(sprite.getStatus().equals("Dead"))
				{
					deadSprites.add(sprite);
				}
			}


			for(Sprite sprite: deadSprites.getAllSprites())
			{
				try {
					checkWinLoose(sprite);
				}catch (Exception exception) {
					logger.debug("Exceptione when there is issue with Win/Loose conditions " + exception.getMessage());
				}
				allItems.remove(sprite);
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				Thread.currentThread().interrupt();
				logger.error("Interrupted exception");
			}
			repaint();
		}
	}


	public void checkWinLoose(Sprite sprite)
	{
		List<Reaction> tempAnyWinList = allItems.getWinMap().get("Any");
		List<Reaction> tempAllWinList = allItems.getWinMap().get("All");
		List<Reaction> tempAnyLooseList = allItems.getLooseMap().get("Any");
		List<Reaction> tempAllLooseList = allItems.getLooseMap().get("All");

		int tempAnyWinListSize = allItems.getWinMap().get("Any").size();
		int tempAllWinListSize = allItems.getWinMap().get("All").size();
		int tempAnyLooseListSize = allItems.getLooseMap().get("Any").size();
		int tempAllLooseListSize = allItems.getLooseMap().get("All").size();


		List<Reaction> updatedAllWinReactions = checkSprite(tempAllWinList, sprite);
		List<Reaction> updatedAllLooseReactions = checkSprite(tempAllLooseList, sprite);

		List<Reaction> updatedAnyWinReactions = checkSprite(tempAnyWinList, sprite);
		List<Reaction> updatedAnyLooseReactions = checkSprite(tempAnyLooseList, sprite);

		if(tempAnyWinListSize != updatedAnyWinReactions.size()) {
			setStartGame(false);
		}
		if(tempAnyLooseListSize != updatedAnyLooseReactions.size()) {
			setStartGame(false);
		}

		if(tempAllLooseListSize != updatedAllLooseReactions.size() && updatedAllLooseReactions.isEmpty()) {
			setStartGame(false);
		}

		if(tempAllWinListSize != updatedAllWinReactions.size() && updatedAllWinReactions.isEmpty()) {
			setStartGame(false);
		}

		allItems.getWinMap().put("Any", updatedAnyWinReactions);
		allItems.getLooseMap().put("Any", updatedAnyLooseReactions);

		allItems.getWinMap().put("All", updatedAllWinReactions);

		allItems.getLooseMap().put("All", updatedAllLooseReactions);
	}

	public List<Reaction> checkSprite(List<Reaction> reactions, Sprite sprite)
	{
		int counter = 0;

		for(Reaction reaction:reactions)
		{
			if(reaction.getSecondary() == sprite)
			{
				reactions.remove(counter);
				break;
			}
			counter++;
		}

		return reactions;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D graphobj = null;
		super.paintComponent(g);
		graphobj = (Graphics2D) g;
		if(image!=null)
		{
			graphobj.drawImage(image,0,0,800,800,this);
		}
		for(Sprite sprite: allItems.getAllSprites()) {
			sprite.draw(graphobj);
		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton ) {
			allItems.pause();
		}
		else {
			logger.info("yo");
		}
	}
	public void mouseClicked(MouseEvent e) {
		int startX = e.getX();
		int startY = e.getY();

		for(int i=0; i<allItems.getAllSprites().size(); i++) {
			if((startX >= allItems.getAllSprites().get(i).getX()-allItems.getAllSprites().get(i).getWidth())&&(startX <=allItems.getAllSprites().get(i).getX()+allItems.getAllSprites().get(i).getWidth())&&
					(startY <= allItems.getAllSprites().get(i).getY()+allItems.getAllSprites().get(i).getHeight())&&(startY >= allItems.getAllSprites().get(i).getY()-allItems.getAllSprites().get(i).getHeight()) ) {
				Sprite sprite = allItems.getAllSprites().get(i);
				if(sprite.hasMouseBehaviour()) {
					sprite.getMouseClickBehaviour().respondToClick();
				}
				objpos=i;
				break;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		int startX = e.getX();
		int startY = e.getY();
		for(int i=0; i<allItems.getAllSprites().size(); i++) {
			if((startX >= allItems.getAllSprites().get(i).getX()-allItems.getAllSprites().get(i).getWidth())&&(startX <= allItems.getAllSprites().get(i).getX()+allItems.getAllSprites().get(i).getWidth())&&(startY <= allItems.getAllSprites().get(i).getY()+allItems.getAllSprites().get(i).getHeight())&&(startY >= allItems.getAllSprites().get(i).getY()-allItems.getAllSprites().get(i).getHeight())) {
				objpos=i;
				break;
			}
		}
		(allItems.getAllSprites().get(objpos)).setX(startX);
		(allItems.getAllSprites().get(objpos)).setY(startY);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//Not implemented
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//Not implemented
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//Not implemented
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//Not implemented
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//Not implemented
	}

	public boolean isStartGame() {
		return startGame;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_F) {
			allItems.shoot();
			List<Sprite> bullets = maker.getLevelObjects().get(maker.getCurrentLevel()).getSprites().getBullets();
			for (Sprite bullet: bullets) {
				for (Sprite component: allItems.getAllSprites()) {
					if (component.isShootEffect()) {
						maker.getLevelObjects().get(maker.getCurrentLevel()).addReaction(new ShootBehavior(bullet, component, null));

					}
				}
			}
		}
		return false;
	}


	public void setStartGame(boolean startGame) {
		this.startGame = startGame;
	}



}
