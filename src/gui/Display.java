package gui;

import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Display extends JPanel implements MouseListener{
	
	public static final long serialVersionUID = 4336547765123432543L;
	
	public Display(){
		addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D)graphics;
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
