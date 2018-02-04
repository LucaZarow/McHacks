//Luca Zarow 260686934

package mchacks.graphics;

import java.util.ArrayList;

import mchacks.physics.Body;
import mchacks.util.*;

public class SystemCreator {

	static public String name = "Solar System";
	
	static public ArrayList<GraphicBody> gBodies = new ArrayList<GraphicBody>();
	
	static public DAGNode create(ArrayList<Body> bodies) {
		Body body = new Body(1,1,1);
		
		body.setPos(new Vector(0,0,0));
		body.setVel(new Vector(0,0,0));
		
		
		DAGNode centre = new GraphicBody("planet", body,0);
		
		/*
		DAGNode a= new GraphicBody("a", bodies.get(0),1);
		DAGNode b = new GraphicBody("b", bodies.get(1),1);
		
		centre.add(a);
		centre.add(b);
	*/

		int i =0;
		for(Body b : bodies){
			gBodies.add(new GraphicBody(Integer.toString(i),b, 1));
			centre.add(gBodies.get(i++));
		}
		return centre;
	}
/*	
	static public DAGNode add(String name, Body body){
		DAGNode centre = new GraphicBody("planet",body);
		return centre;
	}
	*/
}
