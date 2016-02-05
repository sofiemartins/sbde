package main;

import java.util.ArrayList;
import java.awt.geom.Ellipse2D;

import util.MovableObject;
import util.Vector;

public class Constants {
	
	public static final double g = 9.81;//Px/s^2
	public static final double viskositaetDesUmgebungsgases = 17.1; //muPa*s (Luft bei 273K)
	
	public static ArrayList<MovableObject> objects = new ArrayList<MovableObject>();
	
	public static void init(){
		objects.add(new MovableObject(new Ellipse2D.Double(20, 20, 50, 50)));
		objects.get(0).addVelocity(new Vector(100, 0));
	} 
	
}
