package mchacks.physics;

import mchacks.util.Vector;

public class Physics {
	//m^3 * kg^-1 * s^-2
	public static double G = 6.67384 * Math.pow(10, -11);
	
	//Effect of b2 on b1
	public static Vector gravity(Body b1, Body b2) {		
		Vector position = Vector.minus(b1.getPos(), b2.getPos());
		double deltaAcc = G * (b2.getMass()) / (position.getMagnitude() * position.getMagnitude());
		
		Vector resultant = Vector.product(-deltaAcc, position.getUnitVector());
		
		System.out.println(resultant);
		
		
		return Vector.product(-deltaAcc, position.getUnitVector());
	}
	
	public static double rocheLimit (Body b1, Body b2){
		double b1Density = b1.getMass()/(4/3)*Math.PI*Math.pow(b1.getRadius(), 3);
		double b2Density = b2.getMass()/(4/3)*Math.PI*Math.pow(b2.getRadius(), 3);
		
		double roche = 2*Math.pow(2,  1.333333)*b1.getRadius()*(b1Density/b2Density);	
		
		return roche;
	}
}
