package mchacks;

import java.util.Vector;

public class Body {	
	private double mass; //kg 
	private double radius; //m
	private double period; 
	private double speedRot;
	
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
		this.period = 0.0;
		
		this.speedRot = 0.0;
	}
	
	public Body(double m, double r, double s){
		this.pos = new Vector();
		this.vel = new Vector();
		this.acc = new Vector();
		
		this.mass = m;
		this.radius = r;
		this.period = s;
		
		this.speedRot = (2*Math.PI*radius)/period;
	}
	
	public double getMass () {
		return this.mass;
	}
	
	public double getRadius () {
		return this.radius;
	}
	
	public double getPeriod () {
		return this.period;
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
	
	public double gsetPeriod () {
		return this.period;
	}
	
	public Vector setPos () {
		return this.pos;
	}
	
}
