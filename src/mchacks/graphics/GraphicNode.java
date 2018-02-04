package mchacks.graphics;

import java.util.Collection;
import java.util.LinkedList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import mintools.parameters.BooleanParameter;
import mintools.parameters.DoubleParameter;
import mintools.swing.CollapsiblePanel;
import mintools.swing.VerticalFlowPanel;
import mintools.viewer.FancyAxis;

import com.jogamp.opengl.util.gl2.GLUT;

public abstract class GraphicNode {
    
	String name = "";
	
    LinkedList<GraphicNode> children = new LinkedList<GraphicNode>();

    //Collection<DoubleParameter> dofs = new LinkedList<DoubleParameter>();			
    Collection<Double> dofs = new LinkedList<Double>();
    
    static final public GLUT glut = new GLUT();
        
    public GraphicNode( String name ) {
    	this.name = name;
    }

    public void add( GraphicNode n ) {
    	children.add( n );
    }
    

    public void display( GLAutoDrawable drawable ) {
		for ( GraphicNode n : children ) {
			n.display(drawable);
		}
    }

 /*   public JPanel getControls() {
    	if ( dofs.isEmpty() && children.isEmpty() ) return null;
    	VerticalFlowPanel vfp = new VerticalFlowPanel();
    	vfp.setBorder( new TitledBorder(name) );
    	for ( DoubleParameter p : dofs ) {									
    		vfp.add( p.getSliderControls(false) );
    	}
    	for ( GraphicNode n : children ) {
    		JPanel p = n.getControls();
    		if ( p != null ) {
    			vfp.add( p );
    		}
    	}
    	CollapsiblePanel cp = new CollapsiblePanel( vfp.getPanel() );
    	return cp;
    }*/
    

  /*  public void getDOFs( Collection<DoubleParameter> dofs ) {
    	dofs.addAll( this.dofs );											
    	for ( GraphicNode n : children ) {
			n.getDOFs(dofs);
		}
    }*/
    
}
