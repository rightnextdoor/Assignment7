import acm.graphics.*;
import acm.program.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Interactors extends GraphicsProgram {
	
	public void init() {
		
		nameField = new JTextField(30);
		add(new JLabel("Name"),SOUTH);
		add(nameField, SOUTH);
		nameField.addActionListener(this);
		
		add = new JButton("Add");
		add(add, SOUTH);
		remove = new JButton("Remove");
		add(remove, SOUTH);
		clear = new JButton("Clear");
		add(clear, SOUTH);
		
		allBoxes = new HashMap<String,GObject>();
		
		addActionListeners();
		addMouseListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == add || source == nameField) {
			makeBox(nameField.getText());
		} else if (source == remove) {
			getRemove(nameField.getText());
		} else if (source == clear) {
			getClear();
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if (object != null) {
			object.move(e.getX() - move.getX() , e.getY() - move.getY());
		move = new GPoint(e.getPoint());
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		if (object != null) {
			object.sendToFront();
		}
	}
	
	public void mousePressed(MouseEvent e) {
		move = new GPoint(e.getPoint());
		object = getElementAt(move);
	}
	
	private void makeBox(String name) {
		GCompound comp = new GCompound();
		GRect grect = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name);
		comp.add(grect, -BOX_WIDTH / 2, - BOX_HEIGHT / 2);
		comp.add(label, -label.getWidth() / 2 , label.getHeight() / 2);
		add(comp, getWidth() / 2 , getHeight() / 2);
		allBoxes.put(name,comp);
	}
	
	private void getRemove(String name) {
		GObject obj = allBoxes.get(name);
		
		if(obj != null) {
			remove(obj);
			
		}
		
	}
	
	private void getClear() {
		Iterator<String> iterator = allBoxes.keySet().iterator();
		while (iterator.hasNext()) {
			getRemove(iterator.next());
		}
		allBoxes.clear();
	}
	
	
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	private JTextField nameField;
	private GObject object;
	private JButton add, remove, clear;
	private GPoint move;
	private HashMap<String,GObject> allBoxes;
}
