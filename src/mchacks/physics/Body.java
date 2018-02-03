package mchacks.physics;

import mchacks.util.Vector;

public class Body {	
	private double mass; //kg 
	private double radius; //m
	
	private double rotation;
	private double rotationSpeed;
	
	public Vector pos;
	public Vector vel;
	public Vector acc;

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
