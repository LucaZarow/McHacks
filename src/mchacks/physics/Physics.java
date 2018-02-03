package mchacks.physics;

import mchacks.util.Vector;

public class Physics {
	//m^3 * kg^-1 * s^-2
	public static double G = 6.67384 * Math.pow(10, -11);
	
	public static Vector gravity(Body a, Body b) {		
		Vector position = Vector.minus(a.pos, b.pos);
		
		double force = G * (a.getMass() * b.getMass()) / (position.getMagnitude() * position.getMagnitude());
		
		return Vector.product(force, position.getUnitVector());
	}
}
