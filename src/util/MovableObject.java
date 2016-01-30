package util;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Color;
import java.util.Date;

public class MovableObject {
	
	private Shape shape;
	private Rectangle2D.Double box;
	private Vector velocity = new Vector(0,0);
	private Vector acceleration = new Vector(0,0);
	private Date lastEvaluation = new Date();
	private BufferedImage image;
	
	public MovableObject(Shape objectShape){
		shape = objectShape;
		box = (Rectangle2D.Double)objectShape.getBounds2D();
		image = getImage();
	}
	
	public void evaluatePosition(){
		Date currentTime = new Date();
		double deltaTInS = (currentTime.getTime()-lastEvaluation.getTime())/1000.00;
		box = new Rectangle2D.Double(box.getX()+ velocity.getX()*deltaTInS + 0.5*acceleration.getX()*deltaTInS*deltaTInS,
							box.getY()+ velocity.getY()*deltaTInS + 0.5*acceleration.getY()*deltaTInS*deltaTInS,
							box.x, box.y);
		velocity = new Vector(velocity.getX()+acceleration.getX(), velocity.getY()+acceleration.getY());
		lastEvaluation = currentTime;
	}
	
	public boolean collidesWith(Point point){
		return shape.contains(point);
	}
	
	public void move(Vector vector){
		velocity = velocity.add(vector);
		evaluatePosition(); 
	}
	
	public void accelerate(Vector vector){
		acceleration = acceleration.add(vector);
		evaluatePosition();
	}
	
	public Shape getShape(){
		return shape;
	}
	
	public void draw(Graphics2D g){
		g.drawImage(image, (int)box.x, (int)box.y, null);
	}
	
	private BufferedImage getImage(){
		BufferedImage image = new BufferedImage((int)(box.x+box.height+10), (int)(box.y+box.width+10), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.black);
		g.draw(shape);
		return image.getSubimage((int)box.x, (int)box.y, (int)box.width+10, (int)box.height+10);
	}

}
