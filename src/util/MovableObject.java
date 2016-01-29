package util;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Date;

public class MovableObject {
	
	Ellipse2D.Double shape;
	Point position;//center of mass??
	Vector velocity = new Vector(0,0);
	Vector acceleration = new Vector(0,0);
	Date lastEvaluation = new Date();
	BufferedImage icon;
	
	public MovableObject(Ellipse2D.Double objectShape){
		shape = objectShape;
		position = new Point(0, 0);
		velocity = new Vector(0, 0);
		acceleration = new Vector(0, 0);
		icon = getImage();
	}
	
	public void evaluatePosition(){
		Date currentTime = new Date();
		long deltaTInS = (currentTime.getTime()-lastEvaluation.getTime())/1000;
		position = new Point((int)position.getX()+ (int)(velocity.getX()*deltaTInS) + (int)(0.5*acceleration.getX()*deltaTInS*deltaTInS),
							(int)position.getY()+ (int)(velocity.getY()*deltaTInS) + (int)(0.5*acceleration.getY()*deltaTInS*deltaTInS));
		lastEvaluation = currentTime;
	}
	
	public MovableObject(Ellipse2D.Double objectShape, Point objectPosition){
		shape = objectShape;
		position = objectPosition;
		icon = getImage();
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
	
	public Point getPosition(){
		return position;
	}
	
	public BufferedImage getPicture(){
		return icon;
	}
	
	private BufferedImage getImage(){
		Rectangle2D rectangleFit = shape.getBounds2D();
		BufferedImage icon = new BufferedImage((int)(rectangleFit.getWidth()+10),
												(int)(rectangleFit.getHeight()+10),
												BufferedImage.TYPE_INT_ARGB); 
		Graphics2D g = icon.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.red);
		g.fill(shape);
		g.setColor(Color.black);
		g.draw(shape);
		return icon;
	}
	
	public Point getDrawingPosition(){
		Rectangle2D rectangleFit = shape.getBounds2D();
		int upperLeftX = (int)(rectangleFit.getX()+position.getX());
		int upperLeftY = (int)(rectangleFit.getY()+position.getY());
		return new Point(upperLeftX, upperLeftY);
	}
	
	public Ellipse2D.Double getShape(){
		return shape;
	}

}
