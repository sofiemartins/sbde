package util;

public class Matrix {
	
	double a11, a12, a21, a22;
	
	public Matrix(double a, double b, double c, double d){
		a11 = a;
		a12 = b;
		a21 = c;
		a22 = d;
	}
	
	public Vector transform(Vector vector){
		return new Vector(a11*vector.getX()+a12*vector.getY(), a21*vector.getX()+a22*vector.getY());
	}
	
	public static Matrix rotationMatrix(double alpha){
		return new Matrix(Math.cos(alpha), -Math.sin(alpha), Math.sin(alpha), Math.cos(alpha));
	}
	
	public Matrix multiply(double a){
		return new Matrix(a*a11, a*a12, a*a21, a*a22);
	}
	
	public Matrix transpose(){
		return new Matrix(a11, a21, a12, a22);
	}

}
