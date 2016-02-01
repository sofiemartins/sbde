package util;

import gui.Display;

import java.awt.Shape;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Date;

public class MovableObject {
	
	private Shape shape;
	private Rectangle2D.Double box;
	private Vector velocity = new Vector(0,0);
	private Vector acceleration = new Vector(0,0);
	private Date lastEvaluation = new Date();
	private BufferedImage image;
	
	private static final Vector topWallNormal = new Vector(-1, 0);
	private static final Vector rightWallNormal = new Vector(0, -1);
	private static final Vector bottomWallNormal = new Vector(1, 0);
	private static final Vector leftWallNormal = new Vector(0, 1);
	
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
							box.width, box.height);
		velocity = new Vector(velocity.getX()+acceleration.getX(), velocity.getY()+acceleration.getY());
		checkForWallCollision();
		lastEvaluation = currentTime;
	}
	
	//public void addAirResistance(){
	//	Vector airResistance = new Vector(6*Math.PI*box.width)
	//	acceleration.add(airResistance);
	//}
	
	public boolean collidesWith(Point point){
		return shape.contains(point);
	}
	
	private void checkForWallCollision(){
		if(Display.getInstance()!=null){
			if(collidesWithTopWall()){
				velocity = getVelocityAfterElasticCollision(velocity, topWallNormal);
			}else if(collidesWithRightWall()){
				velocity = getVelocityAfterElasticCollision(velocity, rightWallNormal);
			}else if(collidesWithBottomWall()){
				velocity = getVelocityAfterElasticCollision(velocity, bottomWallNormal);
			}else if(collidesWithLeftWall()){
				velocity = getVelocityAfterElasticCollision(velocity, leftWallNormal);
			}
		}
	}
	
	public boolean collidesWithTopWall(){
		Display display = Display.getInstance();
		if(box.intersects(new Rectangle(-50, -50, display.getWidth()+100, 50))){
			return true;
		}
		return false;
	}
	
	public boolean collidesWithRightWall(){
		Display display = Display.getInstance();
		if(box.intersects(new Rectangle(display.getWidth()+50, -50, 50, display.getHeight()+100))){
			return true;
		}
		return false;
	}
	
	public boolean collidesWithBottomWall(){
		Display display = Display.getInstance();
		if(box.intersects(new Rectangle(-50, display.getHeight(), display.getWidth()+100, 50))){
			return true;
		}
		return false;
	}
	
	public boolean collidesWithLeftWall(){
		Display display = Display.getInstance();
		if(box.intersects(new Rectangle(-50, -50, 50, display.getHeight()+100))){
			return true;
		}
		return false;
	}
	
	public Vector getVelocityAfterElasticCollision(Vector velocity, Vector planeTangent){
		double angleOfIncidence = Math.acos((velocity.multiply(planeTangent)/
											(velocity.getMagnitude()*planeTangent.getMagnitude())));
		return Matrix.rotationMatrix((-1)*angleOfIncidence).transform(planeTangent).multiply(velocity.getMagnitude());
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
		g.fill(shape);
		return image.getSubimage((int)box.x, (int)box.y, (int)box.width+10, (int)box.height+10);
	}
	
	private Vector centerOfMass(){
		Vector sum = new Vector(0, 0);
		int numberOfIterations = 0;
		double coords[] = new double[6];
		for(PathIterator iterator = shape.getPathIterator(null); iterator.isDone(); iterator.next()){
			iterator.currentSegment(coords);
			sum.add(new Vector((int)coords[0], (int)coords[1]));
			numberOfIterations++;
		}
		return sum.multiply((1/numberOfIterations));
	}

}
