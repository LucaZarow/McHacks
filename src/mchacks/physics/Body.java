package mchacks.physics;

import mchacks.util.Vector;

public class Body {	
	private double mass; //kg 
	private double radius; //m
	
	private double rotation;
	private double rotationSpeed;
	
	private Vector pos;
	private Vector vel;
	private Vector acc;

	public static double G = 6.674*Math.pow(10, -11); // Nm^2/kg^2;
		
	//Sets everything to zero if not specified
	public Body() {
		this.pos = new Vector();
		this.vel = new Vector();
		this.acc = new Vector();
		
		this.mass = 0.0;
		this.radius = 0.0;

		this.rotation = 0.0;
		this.rotationSpeed = 0.0;
	}
	
	public Body(double mass, double radius, double period){
		this.pos = new Vector();
		this.vel = new Vector();
		this.acc = new Vector();
		
		this.mass = mass;
		this.radius = radius;
		
		this.rotationSpeed = (2 * Math.PI * radius) / period;
	}
	
	public void applyFoce(Vector force) {
		//a = F / m
		Vector deltaAcc = Vector.product(1.0 / this.mass, force);
		
		this.acc = Vector.sum(this.acc, deltaAcc);
	}
	
	public void update(double dt) {
		Vector deltaVel = Vector.product(dt, this.acc);
		this.vel = Vector.sum(this.vel, deltaVel);
		
		Vector deltaPos = Vector.product(dt, this.vel);
		this.pos = Vector.sum(this.pos, deltaPos);
	}
	
	public double getMass () {
		return this.mass;
	}
	
	public double getRadius () {
		return this.radius;
	}
	
	public Vector getPos () {
		return this.pos;
	}
	public double setMass () {
		return this.mass;
	}
	
	public double setRadius () {
		return this.radius;
	}
	
	public Vector setPos () {
		return this.pos;
	}
}
