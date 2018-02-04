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


    /** 120 frames at 30 frames per section for 4 seconds of animation */
    //private final int NUM_FRAMES = 120;
    
    /** The root of the scene graph / character */
    private DAGNode root = null;
    
    /** Master list of degress of freedom */
    private List<DoubleParameter> dofList = new ArrayList<DoubleParameter>();

    private JPanel vfpPosePanel; 
    
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
    /** 
     * Creates the character.
     * This can be called multiple times (i.e., when making edits and running in debug mode,
     * or if the CharacteCreator loads from a file, or creates different characters for whatever
     * reason).  Keyframes are deleted, and dofs are cleared and recreated from the new DAG.  
     * Notice that the pose sliders interface is also rebuilt.
     */
    public void createBody(ArrayList<Body> bodies) {
        dofList.clear();
        vfpPose.removeAll();
    	root = SystemCreator.create(bodies);
    	if ( root == null ) 
    		return;
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
    }
    
}
