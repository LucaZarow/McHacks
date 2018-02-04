package mchacks.graphics;

import java.util.ArrayList;

import mchacks.physics.Body;
import mchacks.util.*;

import mchacks.physics.Physics;

public class SystemCreator {

	static public String name = "Solar System";
	
	static public ArrayList<GraphicBody> gBodies = new ArrayList<GraphicBody>();
	
	static public GraphicNode create(ArrayList<Body> bodies) {
		Body body = new Body(1,1,1);
		Vector colour = new Vector(1,1,1);
		double mass;
		
		body.setPos(new Vector(0,0,0));
		body.setVel(new Vector(0,0,0));
		
		
		GraphicNode centre = new GraphicBody("centre", body, colour,0);

		int i =0;
		for(Body b : bodies){
			mass = b.getMass();
			
			if(mass<Physics.EARTH_MASS/10000){
				colour = new Vector(1,0,0);
			}else if(mass<50*Physics.EARTH_MASS){
				colour = new Vector(0,1,0);
			}else if(mass<13*Physics.JUPITER_MASS){
				colour = new Vector(0,0,1);
			}else{
				colour = new Vector(1,1,1);
			}
			
			gBodies.add(new GraphicBody(Integer.toString(i),b, colour, 1));
			centre.add(gBodies.get(i++));
		}
		return centre;
	}
}
