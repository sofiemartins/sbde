package gui;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import util.Vector;
import util.MovableObject;

import static main.Constants.*;

public class Display extends JPanel implements MouseListener{
	
	public static final long serialVersionUID = 43365477651236654L;
	Timer timer, timer1;
	private MovableObject object = new MovableObject(new Ellipse2D.Double(20, 20, 50, 50));
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
				object.evaluatePosition();
			}
		});
		timer.start();
		timer1.start();
		object.move(new Vector(100, 100));
		object.accelerate(new Vector(0, g));
		
	}
	
	@Override
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D)graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.black);
		object.draw(g);
		//drawWalls(g);
	}
	
	private void drawWalls(Graphics2D g){
		g.setColor(Color.red);
		g.fillRect(-50, -50, display.getWidth()+100, 55);
		g.fillRect(display.getWidth()+45, -50, 55, display.getHeight()+100);
		g.fillRect(-50, display.getHeight()-5, display.getWidth()+100, 50);
		g.fillRect(-50, -50, 55, display.getHeight()+100);
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
