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
		
		Body mercury = new Body(3.301*Math.pow(10, 23),4879000, 0.241);
		sun.setPos(new Vector(46*Math.pow(10, 6), 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(mercury);
		
		Body venus = new Body(4.867*Math.pow(10, 24),12104000, 0.615);
		sun.setPos(new Vector(107.5*Math.pow(10, 6), 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(venus);


		Body earth = new Body(Physics.EARTH_MASS, Physics.EARTH_RADIUS, 1);
		sun.setPos(new Vector(147.146*Math.pow(10, 6), 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(earth);
		

		Body mars = new Body(6.417*Math.pow(10, 23),6792000, 1.881);
		sun.setPos(new Vector (206.646*Math.pow(10, 6), 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(mars);

		Body jupiter = new Body(1.899*Math.pow(10, 27), 142984000, 11.86);
		sun.setPos(new Vector(740.546*Math.pow(10, 6), 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(jupiter);
		

		Body uranus= new Body(8.682*Math.pow(10, 25), 51118000, 29.46);
		sun.setPos(new Vector(1352.646*Math.pow(10, 6), 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(uranus);

		Body neptune = new Body(1.024*Math.pow(10, 26), 1638000, 84.01);
		sun.setPos(new Vector(2741.346*Math.pow(10, 6), 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(neptune);
		
		return bodies;
	}
}
