package mchacks.physics;

import java.util.ArrayList;

import mchacks.util.Vector;

public class SolarSystem {
	public static double scale = 1490000000.0;
	
	public static ArrayList<Body> randomPlanetoids(int num) {
		ArrayList<Body> bodies = new ArrayList<Body>();
		
		Body sun = new Body(Physics.SOLAR_MASS, Physics.EARTH_RADIUS * 8);
		sun.setPos(new Vector(0, 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(sun);
		
		for(int i = 0; i < num; i++) {
			double massRandomizer = Math.random() * 4;
			double randomMass;
			if(massRandomizer < 2)
				randomMass = Physics.EARTH_MASS / 20000.0;
			else if(massRandomizer < 3)
				randomMass = Physics.EARTH_MASS / 2 + (Math.random() * Physics.EARTH_MASS / 2);
			else
				randomMass = Physics.JUPITER_MASS;

			double randomPosX = (2 * Math.random() * Physics.AU) - Physics.AU;
			double randomPosY = (2 * Math.random() * Physics.AU) - Physics.AU;
			double randomPosZ = (2 * Math.random() * Physics.AU) - Physics.AU;
			//double randomPosZ = 0;
			
			Body b = new Body(randomMass, Physics.EARTH_RADIUS * (1 + massRandomizer));
			b.setPos(new Vector(randomPosX, randomPosY, randomPosZ));
			
			Vector vel = new Vector(-b.getPos().y, b.getPos().x, 0);
			vel = Vector.product((0.3 + Math.random()) * Physics.circularOrbit(sun, b.getPos().getMagnitude()), vel.getUnitVector());
			
			b.setVel(vel);
			
			bodies.add(b);
		}
		return bodies;
	}
	
	public static ArrayList<Body> randomDisk(int num) {
		ArrayList<Body> bodies = new ArrayList<Body>();
		
		Body sun = new Body(Physics.SOLAR_MASS, Physics.EARTH_RADIUS * 8);
		sun.setPos(new Vector(0, 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(sun);
		
		for(int i = 0; i < num; i++) {
			double massRandomizer = Math.random() * 4;
			double randomMass;
			if(massRandomizer < 2)
				randomMass = Physics.EARTH_MASS / 20000.0;
			else if(massRandomizer < 3)
				randomMass = Physics.EARTH_MASS / 2 + (Math.random() * Physics.EARTH_MASS / 2);
			else
				randomMass = Physics.JUPITER_MASS;

			double randomPosX = (2 * Math.random() * Physics.AU) - Physics.AU;
			double randomPosY = (2 * Math.random() * Physics.AU) - Physics.AU;
			//double randomPosZ = (2 * Math.random() * Physics.AU) - Physics.AU;
			double randomPosZ = 0;
			
			Body b = new Body(randomMass, Physics.EARTH_RADIUS * (1 + massRandomizer));
			b.setPos(new Vector(randomPosX, randomPosY, randomPosZ));
			
			Vector vel = new Vector(-b.getPos().y, b.getPos().x, 0);
			vel = Vector.product(Physics.circularOrbit(sun, b.getPos().getMagnitude()), vel.getUnitVector());
			
			b.setVel(vel);
			
			bodies.add(b);
		}
		return bodies;
	}
	
	public static ArrayList<Body> pluto() {
		ArrayList<Body> bodies = new ArrayList<Body>();
		
		Body pluto = new Body(1.3 * Math.pow(10, 22), 1188000);
		Body charon = new Body(1.55 * Math.pow(10, 21), 600000);
		
		charon.setPos(new Vector(17500000, 0, 0));
		
		Vector vel = new Vector(-charon.getPos().y, charon.getPos().x, 0);
		Vector velChar = Vector.product(Physics.circularOrbit(pluto, charon.getPos().getMagnitude()), vel.getUnitVector());
		charon.setVel(velChar);
		
		bodies.add(pluto);
		bodies.add(charon);
		
		scale = charon.getPos().getMagnitude() / 100;
		
		return bodies;
	}
	
	public static ArrayList<Body> solSystem() {
		ArrayList<Body >bodies = new ArrayList<Body>();
		
		Body sun = new Body(Physics.SOLAR_MASS, Physics.SOLAR_RADIUS);
		sun.setPos(new Vector(0, 0, 0));
		sun.setVel(new Vector(0, 0, 0));
		bodies.add(sun);
		
		Body mercury = new Body(3.301*Math.pow(10, 23),4879000);
		mercury.setRadius(Physics.SOLAR_RADIUS);
		mercury.setPos(new Vector(46*Math.pow(10, 9), 0, 0));
		mercury.setVel(new Vector(0, 58.98*Math.pow(10, 9), 0));
		bodies.add(mercury);
		
		Body venus = new Body(4.867*Math.pow(10, 24),12104000);
		venus.setRadius(Physics.SOLAR_RADIUS);
		venus.setPos(new Vector(107.5*Math.pow(10, 9), 0, 0));
		venus.setVel(new Vector(0, 35.26*Math.pow(10, 9), 0));
		bodies.add(venus);

		Body earth = new Body(Physics.EARTH_MASS, Physics.EARTH_RADIUS);
		earth.setRadius(Physics.SOLAR_RADIUS);
		earth.setPos(new Vector(Physics.AU, 0, 0));
		earth.setVel(new Vector(0, 30.29*Math.pow(10, 9), 0));
		bodies.add(earth);
		
		Body mars = new Body(6.417*Math.pow(10, 23),6792000);
		mars.setRadius(Physics.SOLAR_RADIUS);
		mars.setPos(new Vector (206.646*Math.pow(10, 9), 0, 0));
		mars.setVel(new Vector(0, 26.50*Math.pow(10, 9), 0));
		bodies.add(mars);

		Body jupiter = new Body(1.899*Math.pow(10, 27), 142984000);
		jupiter.setRadius(Physics.SOLAR_RADIUS);
		jupiter.setPos(new Vector(740.546*Math.pow(10, 9), 0, 0));
		jupiter.setVel(new Vector(0, 13.72*Math.pow(10, 9), 0));
		bodies.add(jupiter);

		Body uranus= new Body(8.682*Math.pow(10, 25), 51118000);
		uranus.setRadius(Physics.SOLAR_RADIUS);
		uranus.setPos(new Vector(1352.646*Math.pow(10, 9), 0, 0));
		uranus.setVel(new Vector(0, 7.11*Math.pow(10, 9), 0));
		bodies.add(uranus);

		Body neptune = new Body(1.024*Math.pow(10, 26), 1638000);
		neptune.setRadius(Physics.SOLAR_RADIUS);
		neptune.setPos(new Vector(2741.346*Math.pow(10, 9), 0, 0));
		neptune.setVel(new Vector(0, 5.50*Math.pow(10, 9), 0));
		bodies.add(neptune);
		
		return bodies;
	}
}
	
