package mchacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mchacks.physics.Body;
import mchacks.physics.Physics;
import mchacks.physics.SolarSystem;
import mchacks.util.Vector;

public class Simulation implements Runnable {
	private boolean running;
	private long framesPerSecond, updatesPerSecond;
	
	public static double timeStepModifier = 3600;
	
	public ArrayList<Body> bodies;
	
	JFrame frame;
	JPanel panel;
	
	public Simulation() {
		bodies = SolarSystem.solSystem();
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(400, 400);
		
		panel = new JPanel();
		frame.add(panel);
	}
	
	public void start() {
		running = true;
		this.run();
	}
	
	private void render() {
		//Ugly 2D graphics
		
		Graphics2D g2d = (Graphics2D)panel.getGraphics();
		g2d.setColor(new Color(0, 0, 0, 0));
		g2d.clearRect(0, 0, panel.getWidth(), panel.getHeight());
		
		g2d.setColor(Color.black);
		
		for(Body b : bodies) {
			int x = (int) (b.getPos().x / SolarSystem.scale) + 200;
			int y = (int) (b.getPos().y / SolarSystem.scale) + 200;
			int size = (int) (5 * (b.getRadius() / Physics.EARTH_RADIUS));
			
			x -= size / 2;
			y -= size / 2;
			
			g2d.fillOval(x, y, 5, 5);
		}
		
		panel.paintComponents(g2d);
	}
	
	private void update(double dt) {
		outerloop: for(Body b1 : bodies) {
			for(Body b2 : bodies) {
				if(b1 == b2) continue;
				b1.applyDeltaAcc(Physics.gravity(b1, b2));
				
				//Check collisions
				if(b1.hasCollided(b2)) {					
					Body b3 = new Body();
					
					//New mass
					b3.setMass(b1.getMass() + b2.getMass());
					
					//New radius
					b3.setRadius(Math.pow(Math.pow(b1.getRadius(), 3) + Math.pow(b2.getRadius(), 3), 0.3333));
					
					//New velocity
					Vector resultant = Vector.sum(Vector.product(b1.getMass(), b1.getVel()), Vector.product(b2.getMass(), b2.getVel()));
					resultant = Vector.product(1.0 / (b1.getMass() * b2.getMass()), resultant);
					b3.setVel(resultant);
					
					//New position
					b3.setPos(b1.getPos());
					
					bodies.remove(b1);
					bodies.remove(b2);
					bodies.add(b3);
					break outerloop;
				}
			}
			
			b1.update(dt);
		}
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

				updatesPerSecond = 0;
				framesPerSecond = 0;
			}
			update(dt * timeStepModifier);
			render();
			
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
