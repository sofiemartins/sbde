package util;

import java.awt.Point;

public class Vector {
	
	private double x1,x2;
	
	public Vector(int x, int y){
		x1 = x;
		x2 = y;
	}
	
	public Vector(double x, double y){
		x1 = x;
		x2 = y;
	}
	
	public double getX(){
		return x1;
	}
	
	public double getY(){
		return x2;
	}
	
	public double getMagnitude(){
		return Math.sqrt(x1*x1 + x2*x2);
	}
	
	public Vector multiply(int a){
		return multiply((double)a);
	}
	
	public Vector multiply(double a){
		return new Vector(a*x1, a*x2);
	}
	
	public double multiply(Vector vector){
		return vector.getX()*x1 + vector.getY()*x2;
	}
	
	public Point shift(Point point){
		return new Point((int)(point.getX()+x1), (int)(point.getY()+x1));
	}
	
	public Vector add(Vector vector){
		return new Vector(vector.getX()+x1, vector.getY()+x2);
	}
	
	public Vector subtract(Vector vector){
		return new Vector(vector.getX()-x1, vector.getY()-x2);
	}
	
	public String toString(){
		return "Vector={ " + x1 + ";" + x2 + "}";
	}
}
