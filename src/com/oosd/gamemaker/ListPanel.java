package com.oosd.gamemaker;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.oosd.gamemaker.models.Composite;
import com.oosd.gamemaker.models.Sprite;

public class ListPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 7675156536260347672L;

	Composite allSprites;
	JButton buttonDelete;
	JList<String> listSprite;
	Maker maker;
	JScrollPane scrollPane;
	public ListPanel(Composite allSprites, Maker maker) {
		buttonDelete = new JButton("Delete");
		buttonDelete.setLocation(10, 600);
		this.add(buttonDelete);
		this.setBackground((Color.decode("#ADD8E6")));
		this.allSprites = allSprites;
		this.maker = maker;
		this.createList();
	}
	public void createList() {
		List<Sprite> sprites = allSprites.getAllSprites();
		int count = sprites.size();
		String[] arraySprites = new String[count];
		for(int i = 0; i < count ; i++ ) {
			arraySprites[i] = sprites.get(i).getName();
		}
		listSprite = new JList<>(arraySprites);
		buttonDelete.addActionListener(this);
		scrollPane = new JScrollPane(listSprite);
		this.add(scrollPane);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedIndex = listSprite.getSelectedIndex();
		if(e.getSource() == buttonDelete) {
			maker.allItems.remove(allSprites.getAllSprites().get(selectedIndex));
			listSprite.remove(selectedIndex);
			this.remove(scrollPane);
			this.createList();
			this.setVisible(false);
			this.setVisible(true);
		}
	}
}

class ListItem
{
    private String label;
    private Object value;

    public ListItem(String label, Object value)
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


    public Object getValue()
    {
        return value;
    }
}