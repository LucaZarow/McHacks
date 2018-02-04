package mchacks.physics;

import java.util.ArrayList;

import mchacks.util.Vector;

public class SolarSystem {
	public static ArrayList<Body> randomPlanetoids() {
		ArrayList<Body >bodies = new ArrayList<Body>();
		
		Body sun = new Body(Physics.SOLAR_MASS, Physics.EARTH_RADIUS * 5, 1);
		sun.setPos(new Vector(0, 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(sun);
		
		for(int i = 0; i < 400; i++) {
			double randomMass = Physics.EARTH_MASS / 2 + (Math.random() * Physics.EARTH_MASS / 2);
			
			double randomPosX = (2 * Math.random() * Physics.AU) - Physics.AU;
			double randomPosY = (2 * Math.random() * Physics.AU) - Physics.AU;
			double randomPosZ = (2 * Math.random() * Physics.AU) - Physics.AU;
			
			Body b = new Body(randomMass, Physics.EARTH_RADIUS, 1);
			b.setPos(new Vector(randomPosX, randomPosY, randomPosZ));
			
			Vector vel = new Vector(-b.getPos().y, b.getPos().x, 0);
			vel = Vector.product(Physics.circularOrbit(sun, b.getPos().getMagnitude()), vel.getUnitVector());
			
			System.out.println(vel);
			b.setVel(vel);
			
			bodies.add(b);
		}
		return bodies;
	}
	
	public static ArrayList<Body> solSystem() {
		ArrayList<Body >bodies = new ArrayList<Body>();
		
		Body sun = new Body(Physics.SOLAR_MASS, Physics.EARTH_RADIUS * 5, 1);
		sun.setPos(new Vector(0, 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(sun);
		
		return bodies;
	}
}
