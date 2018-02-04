package mchacks.graphics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import com.jogamp.opengl.GLAutoDrawable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mintools.parameters.BooleanParameter;
import mintools.parameters.DoubleParameter;
import mintools.swing.HorizontalFlowPanel;
import mintools.swing.VerticalFlowPanel;

import mchacks.physics.Body;

public class Scene {

    
    private GraphicNode root = null;
    
    private List<DoubleParameter> dofList = new ArrayList<DoubleParameter>();

//    private JPanel vfpPosePanel; 
    
    private VerticalFlowPanel vfpPose = new VerticalFlowPanel();

	public Scene(ArrayList<Body> bodies) {
    	createBody( bodies);
	}
	
	public void display( GLAutoDrawable drawable ) {
        if ( root != null ) 
        	root.display(drawable);
	}
/*																		//try to resolve
    public JPanel getControls() {
    	VerticalFlowPanel vfp = new VerticalFlowPanel();
    	
    	JButton recreate = new JButton("recreate character");
    	recreate.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createBody();
			}
		});
    	vfp.add(recreate);
	
    	vfpPose.setBorder( new TitledBorder("Pose") );
    	vfpPosePanel = vfpPose.getPanel();
    	vfp.add( vfpPosePanel );
    	return vfp.getPanel();
    }

	*/

    public void createBody(ArrayList<Body> bodies) {
        dofList.clear();
        vfpPose.removeAll();
    	root = SystemCreator.create(bodies);
    	if ( root == null ) 
    		return;
    /*
    	root.getDOFs( dofList );
    	int labelWidth = DoubleParameter.DEFAULT_SLIDER_LABEL_WIDTH;
    	int textWidth = DoubleParameter.DEFAULT_SLIDER_TEXT_WIDTH;
    	DoubleParameter.DEFAULT_SLIDER_LABEL_WIDTH = 50;
    	DoubleParameter.DEFAULT_SLIDER_TEXT_WIDTH = 50;
    	vfpPose.add( root.getControls() );    	
    	if (vfpPosePanel != null ) {
    		vfpPosePanel.updateUI();
    	}
    	DoubleParameter.DEFAULT_SLIDER_LABEL_WIDTH = labelWidth;
    	DoubleParameter.DEFAULT_SLIDER_TEXT_WIDTH = textWidth;
    	*/
    }
    
}
