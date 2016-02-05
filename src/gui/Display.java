package gui;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.Constants;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import util.MovableObject;

import static main.Constants.*;

public class Display extends JPanel implements MouseListener{
	
	public static final long serialVersionUID = 43365477651236654L;
	Timer timer, timer1;
	private static Display display = new Display();
	
	public static Display getInstance(){
		return display;
	}
	
	public Display(){
		addMouseListener(this);
		setBackground(Color.white);
		timer = new Timer(10, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				revalidate();
				repaint();
			}
		});
		timer1 = new Timer(10, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				for(MovableObject object : Constants.objects){
					object.evaluatePosition();
				}
			}
		});
		timer.start();
		timer1.start();
	}
	
	@Override
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D)graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.black);
		for(MovableObject object : objects){
			object.draw(g);
		}
		//drawWalls(g);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
