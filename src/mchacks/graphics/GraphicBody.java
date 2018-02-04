package mchacks.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mchacks.physics.Body;
import mchacks.util.*;

import mintools.parameters.DoubleParameter;

public class GraphicBody extends GraphicNode{
/*	
	DoubleParameter txD;
	DoubleParameter tyD;
	DoubleParameter tzD;
	DoubleParameter rxD;
	DoubleParameter ryD;
	DoubleParameter rzD;
	DoubleParameter s;
	*/
	double s;
	double tx;
	double ty;
	double tz;
	double r;
	double g;
	double b;
	double a;
	
	double scalePos = 	Math.pow(10, 6);
	double scaleSize =		5000; 
	
	Body body;
	
	
	public GraphicBody(String name, Body initBody, float alpha){
		super(name);
/*		dofs.add( txD = new DoubleParameter( name+" txD", 0, -10, 10 ) );		
		dofs.add( tyD = new DoubleParameter( name+" tyD", 0, -10, 10 ) );
		dofs.add( tzD = new DoubleParameter( name+" tzD", 0, -10, 10 ) );
		dofs.add( rxD = new DoubleParameter( name+" rxD", 0, -180, 180 ) );		
		dofs.add( ryD = new DoubleParameter( name+" ryD", 0, -180, 180 ) );
		dofs.add( rzD = new DoubleParameter( name+" rzD", 0, -180, 180 ) );
		dofs.add( s = new DoubleParameter( name+" s", 1, 1, 2 ) );
		*/
		this.body = initBody;
		
		Vector vec = initBody.getPos();
		
		tx=vec.x/scalePos;
		ty=-vec.y/scalePos;
		tz=vec.z/scalePos;
		s=initBody.getRadius()/scaleSize;
		
		r=Math.random();
		g=Math.random();
		b=Math.random();
		a = alpha ;
	}
	
	private void updatePosition(){
		Vector vec = body.getPos();
		tx=vec.x/scalePos;
		ty=-vec.y/scalePos;
		tz=vec.z/scalePos;
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		if ( body == null ) 
			return;
		
		updatePosition();
		
		System.out.println("AAA   " + tx + " " + ty + " " + tz);
		
		GL2 gl = drawable.getGL().getGL2();
	
		gl.glPushMatrix();
		
		gl.glTranslated(tx, 0, 0);
		gl.glTranslated(0, ty, 0);
		gl.glTranslated(0, 0, tz);
		
		gl.glScaled(s,s,s);
		
		super.display(drawable);
		
		gl.glColor4d(r, g, b, a);
	
		glut.glutSolidSphere(1,20,20);
		
		gl.glPopMatrix();
		
	}

}
