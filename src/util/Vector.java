package util;

public class Vector {
	
	int x1,x2;
	
	public Vector(int x, int y){
		x1 = x;
		x2 = y;
	}
	
	public int getX(){
		return x1;
	}
	
	public int getY(){
		return x2;
	}
	
	public double getMagnitude(){
		return Math.sqrt(x1*x1 + x2*x2);
	}
	
	public Vector multiply(int a){
		return new Vector(a*x1, a*x2);
	}
	
	public int multiply(Vector vector){
		return vector.getX()*x1 + vector.getY()*x2;
	}
	
	

}
