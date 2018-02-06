package mchacks.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mchacks.physics.Body;
import mchacks.util.*;

import mintools.parameters.DoubleParameter;

public class GraphicBody extends GraphicNode{
/*	
	DoubleParameter txD;
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
	double scaleSize =		20000; 
	
	Body body;
	
	
	public GraphicBody(String name, Body initBody, Vector colour, float alpha){
		super(name);
/*		dofs.add( txD = new DoubleParameter( name+" txD", 0, -10, 10 ) );		
		*/
		this.body = initBody;
		
		Vector vec = initBody.getPos();
		
		tx=vec.x/scalePos;
		ty=-vec.y/scalePos;
		tz=vec.z/scalePos;
		s=initBody.getRadius()/scaleSize;
		
		r=colour.x;//Math.random();
		g=colour.y;//Math.random();
		b=colour.z;//Math.random();
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
		if ( body == null ) {
			this.a = 0;
		}
		
		updatePosition();
		
		GL2 gl = drawable.getGL().getGL2();
	
		gl.glPushMatrix();
		
		gl.glTranslated(tx, 0, 0);
		gl.glTranslated(0, ty, 0);
		gl.glTranslated(0, 0, tz);
		
		gl.glScaled(s,s,s);
		
		super.display(drawable);
		
		gl.glColor4d(r, g, b, a);
	
//		if(r==1 && b==1){
//			glut.glutSolidCylinder(1,2,20,1);
//		}else{
			glut.glutSolidSphere(1,20,20);
//		}
		
		gl.glPopMatrix();
		
	}

}
