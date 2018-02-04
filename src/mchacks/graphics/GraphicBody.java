package mchacks.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mchacks.physics.Body;
import mchacks.util.*;

import mintools.parameters.DoubleParameter;

public class GraphicBody extends DAGNode{
	
	DoubleParameter txD;
	DoubleParameter tyD;
	DoubleParameter tzD;
	DoubleParameter rxD;
	DoubleParameter ryD;
	DoubleParameter rzD;
//	DoubleParameter s;
	double s;
	double tx;
	double ty;
	double tz;
	double r;
	double g;
	double b;
	double a;
	
	double scalePos = 	1000000;
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
		s=initBody.getRadius()/scaleSize;//initBody.getRadius();
		
		
		
		r=Math.random();
		g=Math.random();
		b=Math.random();
		a = alpha;
	}
	
	private void updatePosition(){
		Vector vec = body.getPos();
		tx=vec.x/scalePos;
		ty=-vec.y/scalePos;
		tz=-20;
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		if ( body == null ) 
			return;
		

		
		updatePosition();
		
		Vector vec = body.getPos();
//		System.out.println(vec.x + " " + vec.y + " " + vec.z);
		
		System.out.println("AAA   " + tx + " " + ty + " " + tz);
		
		GL2 gl = drawable.getGL().getGL2();
	
		gl.glPushMatrix();
/*
		gl.glTranslated(txD.getValue(), 0, 0);
		gl.glTranslated(0, tyD.getValue(), 0);
		gl.glTranslated(0, 0, tzD.getValue());
		
		gl.glRotated( rxD.getValue(), 1, 0, 0);
		gl.glRotated( ryD.getValue(), 0, 1, 0);
		gl.glRotated( rzD.getValue(), 0, 0, 1);
		
		*/
		
		gl.glTranslated(tx, 0, 0);
		gl.glTranslated(0, ty, 0);
		gl.glTranslated(0, 0, tz);
		
		//gl.glScaled(s.getValue(), s.getValue(), s.getValue());
		gl.glScaled(s,s,s);
		
		super.display(drawable);
		
		gl.glColor4d(r, g, b, a);
	
		glut.glutSolidSphere(1,20,20);//glut.glutSolidSphere(1, 20, 20);	
		
		gl.glPopMatrix();
		
	}

}
