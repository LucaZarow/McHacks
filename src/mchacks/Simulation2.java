package mchacks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mchacks.physics.Body;
import mchacks.physics.Physics;
import mchacks.util.Vector;

import mchacks.graphics.*;

public class Simulation2 implements Runnable {
	private boolean running;
	private long framesPerSecond, updatesPerSecond;
	
	public ArrayList<Body> bodies;
	
	JFrame frame;
	JPanel panel;
	
	public Simulation2() {
		bodies = new ArrayList<Body>();
		
		Body b = new Body(5.972 * Math.pow(10, 24), 1, 1);
		b.setPos(new Vector(0, 0, 0));
		b.setVel(new Vector(0, 0, 0));
		
		Body b2 = new Body(7.342 * Math.pow(10, 22), 1, 1);
		b2.setPos(new Vector(383400000, 0, 0));
		b2.setVel(new Vector(0, 1000, 0));
		
		bodies.add(b);
		bodies.add(b2);
		
		frame = new JFrame();
		frame.setVisible(true);
		Dimension size = new Dimension(400, 400);
		frame.setSize(400, 400);
		
		panel = new JPanel();
		frame.add(panel);
	}
	
	public void start() {
		new GraphicsApp(bodies);
		running = true;
		this.run();
		
	}
	
	private void render() {
		
	}
	
	private void update(double dt) {
		for(Body b1 : bodies) {
			for(Body b2 : bodies) {
				if(b1 == b2) continue;
				b1.applyDeltaAcc(Physics.gravity(b1, b2));
				
				//Check collisions
				if(b1.hasCollided(b2)) {
					System.out.println("Collision!");
					
					Body b3 = new Body();
					
					//New mass
					b3.setMass(b1.getMass() + b2.getMass());
					
					//New radius
					b3.setRadius(Math.pow(Math.pow(b1.getRadius(), 3) + Math.pow(b2.getRadius(), 3), 0.3333));
					
					//New velocity
					Vector resultant = Vector.sum(Vector.product(b1.getMass(), b1.getVel()), Vector.product(b2.getMass(), b2.getVel()));
					resultant = Vector.product(1.0 / (b1.getMass() * b2.getMass()), resultant);
					b3.setVel(resultant);
					
					bodies.remove(b1);
					bodies.remove(b2);
					bodies.add(b3);
					break;
				}
			}
			
			b1.update(dt);
		}
		
		Graphics2D g2d = (Graphics2D)panel.getGraphics();
		g2d.setColor(new Color(0, 0, 0, 0));
		g2d.clearRect(0, 0, panel.getWidth(), panel.getHeight());
		
		g2d.setColor(Color.black);
		
		for(Body b : bodies) {
			int x = (int) (b.getPos().x / 3834000) + 200;
			int y = (int) (b.getPos().y / 3834000) + 200;
			
			g2d.fillOval(x, y, 5, 5);
		}
		
		panel.paintComponents(g2d);
	}
	
	@Override
	public void run() {
		long then = System.nanoTime();
			
		double timer = 0;
		
		while(running) {
			long now = System.nanoTime();
			double dt = (now - then) / 1000000000.0;
			
			timer += dt;
			updatesPerSecond++;
			
			if(timer > 1) {
				timer -= 1;
				System.out.println("Updates: " + updatesPerSecond + "\tFPS: " + framesPerSecond);
				System.out.println("Body count " + bodies.size());
				
				for(Body b : bodies) {
					System.out.println(Vector.product(1.0 / 383400000, b.getPos()));
				}
				
				updatesPerSecond = 0;
				framesPerSecond = 0;
			}
			update(dt * 1000);
			
			//Sleep
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			then = now;
		}
	}
}
