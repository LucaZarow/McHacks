package mchacks;

public class Vector
{
	double x, y, z;
	
	public Vector(double a, double b, double c)
	{
		x=a;
		y=b;
		z=c;
	}
	
	public static Vector sum(Vector v1, Vector v2)
	{   
		double x = v1.x + v2.x;
		double y = v1.y+v2.y;
		double z = v1.z+v2.z;
		
		Vector sumVector = new Vector(x, y, z);
	
		return sumVector;		
	}
	
	public static Vector minus(Vector v1, Vector v2) {
		double x = v1.x - v2.x;
		double y = v1.y - v2.y;
		double z = v1.z - v2.z;
		
		return new Vector(x, y, z);
	}
	
	public static Vector product(double k, Vector v1)
	{    
		double x = k*v1.x;
		double y = k*v1.y;
		double z = k*v1.z;
		
		return new Vector(x, y, z);
	}
	
	public static double magnitude(Vector v1)
	{
		double vectorMagnitude = Math.sqrt(Math.pow(v1.x, 2) + Math.pow(v1.y, 2) + Math.pow(v1.z, 2));

		return vectorMagnitude;
	}
	
	public static Vector unitOf(Vector v1)
	{
		double magnitude = magnitude(v1);
		double scalar = 1.0 / magnitude;
		
		return product(scalar, v1);
	}
}