package mchacks.physics;

import mchacks.util.Vector;

public class Physics {
	//m^3 * kg^-1 * s^-2
	public static final double G = 6.67384 * Math.pow(10, -11);
	
	public static final double SOLAR_MASS = 1.989 * Math.pow(10, 30);
	public static final double EARTH_MASS = 5.972 * Math.pow(10, 24);
	
	public static final double SOLAR_RADIUS = 695700000;
	public static final double EARTH_RADIUS = 6400000;
	
	public static final double AU = 1.496 * Math.pow(10, 11);
	
	//Effect of b2 on b1
	public static Vector gravity(Body b1, Body b2) {		
		Vector position = Vector.minus(b1.getPos(), b2.getPos());
		double deltaAcc = G * (b2.getMass()) / (position.getMagnitude() * position.getMagnitude());
		
		return Vector.product(-deltaAcc, position.getUnitVector());
	}
	
	public static double circularOrbit (Body b, double radius) {
		double orbitalSpeed = Math.sqrt(Physics.G * b.getMass() / radius);
		return orbitalSpeed;
	}
}
