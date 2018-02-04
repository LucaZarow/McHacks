package mchacks.util;

public class Vector {
	public double x, y, z;
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public double getMagnitude() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
	}
	
	public Vector getUnitVector() {
		return product(1.0 / getMagnitude(), this);
	}
	
	public static Vector sum(Vector v1, Vector v2) {   
		double x = v1.x + v2.x;
		double y = v1.y + v2.y;
		double z = v1.z + v2.z;
		
		return new Vector(x, y, z);		
	}
	
	public static Vector minus(Vector v1, Vector v2) {
		double x = v1.x - v2.x;
		double y = v1.y - v2.y;
		double z = v1.z - v2.z;
		
		return new Vector(x, y, z);
	}
	
	public static Vector product(double k, Vector v1) {    
		double x = k * v1.x;
		double y = k * v1.y;
		double z = k * v1.z;
		
		return new Vector(x, y, z);
	}
	
	public static Vector orthogonal(Vector v1, Vector v2){
		
		double x = v1.x*v2.z;
		double y = v1.z*v2.x - v1.x*v2.z;
		double z = v1.x*v2.y - v1.y*v2.x;
		
		return new Vector(x,y,z);
	}
	
	@Override
	public String toString() {
		return String.format("X:%f, Y:%f, Z:%f", x, y, z);
	}
}