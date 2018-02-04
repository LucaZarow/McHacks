package mchacks.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import com.jogamp.opengl.DebugGL2;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import mintools.swing.ControlFrame;
import mintools.viewer.EasyViewer;
import mintools.viewer.TrackBallCamera;

import com.jogamp.opengl.util.FPSAnimator;

import mchacks.physics.Body;

public class GraphicsApp implements GLEventListener {

    private TrackBallCamera tbc = new TrackBallCamera();
 
    private Scene scene;												

    public GraphicsApp(ArrayList<Body> bodies) {
    	
    	scene = new Scene (bodies);
    	
        String windowName = "Space";
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities glcap = new GLCapabilities(glp);
        GLCanvas glCanvas = new GLCanvas( glcap );
        final FPSAnimator animator; 
        animator = new FPSAnimator(glCanvas, 30);
        animator.start();
 //       ControlFrame controls = new ControlFrame("Controls", new Dimension( 600,600 ), new Point(680,0) );
 //       controls.setVisible(true);    
        JFrame frame = new JFrame(windowName);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(glCanvas, BorderLayout.CENTER);
        glCanvas.setSize(1280,720); // 720p resolution
        

        glCanvas.addGLEventListener( this );
        
        tbc.attach( glCanvas );
        try {
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            frame.pack(); // size
            frame.setVisible(true);
            glCanvas.requestFocus(); // event listener
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void display(GLAutoDrawable drawable) {    	
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        tbc.prepareForDisplay(drawable);
        scene.display( drawable );						
    }
 

    @Override
    public void init(GLAutoDrawable drawable) {
        drawable.setGL(new DebugGL2(drawable.getGL().getGL2()));
        GL2 gl = drawable.getGL().getGL2();
        
        gl.glClearColor(0f, 0f, 0f, 1f); 	// background
        gl.glClearDepth(1.0f); 				// depth
        gl.glEnable(GL.GL_DEPTH_TEST); 		
        gl.glDepthFunc(GL.GL_LEQUAL); 		
        gl.glEnable( GL2.GL_NORMALIZE ); 	// normailze

        // lights
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();        
        gl.glEnable( GL2.GL_LIGHTING );
        gl.glEnable( GL2.GL_LIGHT0 );
        
        // homogenize
        gl.glLightfv( GL2.GL_LIGHT0, GL2.GL_POSITION, new float[] {10,10,10, 1}, 0 );
        gl.glLightfv( GL2.GL_LIGHT0, GL2.GL_AMBIENT, new float[] {0,0,0,1}, 0);
        gl.glLightModelfv( GL2.GL_LIGHT_MODEL_AMBIENT, new float[] {0.1f,0.1f,0.1f,1}, 0);

        //materials

        final float[] white = new float[] {1,1,1,1}; // R G B A
        gl.glMaterialfv( GL.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, white, 0 );
        gl.glMaterialf( GL.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 50 );
        gl.glEnable( GL2.GL_COLOR_MATERIAL);
     }
        
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }
}
