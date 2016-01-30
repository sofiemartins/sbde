package gui;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import util.Vector;
import util.MovableObject;

public class Display extends JPanel implements MouseListener{
	
	public static final long serialVersionUID = 43365477651236654L;
	Timer timer, timer1;
	private MovableObject object = new MovableObject(new Ellipse2D.Double(5, 5, 50, 50));
	
	public Display(){
		addMouseListener(this);
		setBackground(Color.white);
		object.move(new Vector(20, 20));
		timer = new Timer(10, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				repaint();
			}
		});
		timer1 = new Timer(10, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				object.evaluatePosition();
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
		object.draw(g);
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
