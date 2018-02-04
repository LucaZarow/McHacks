package mchacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mchacks.physics.Body;
import mchacks.physics.Physics;
import mchacks.util.Vector;

public class Simulation implements Runnable {
	private boolean running;
	private long framesPerSecond, updatesPerSecond;
	
	public ArrayList<Body> bodies;
	
	JFrame frame;
	JPanel panel;
	
	public Simulation() {
		bodies = new ArrayList<Body>();
		
		Body b3 = new Body(Physics.SOLAR_MASS, Physics.EARTH_RADIUS * 5, 1);
		b3.setPos(new Vector(0, 0, 0));
		b3.setVel(new Vector(0, 0, 0));
		bodies.add(b3);
		
		for(int i = 0; i < 400; i++) {
			double randomMass = Physics.EARTH_MASS / 2 + (Math.random() * Physics.EARTH_MASS / 2);
			
			double randomPosX = (2 * Math.random() * Physics.AU) - Physics.AU;
			double randomPosY = (2 * Math.random() * Physics.AU) - Physics.AU;
			double randomPosZ = (2 * Math.random() * Physics.AU) - Physics.AU;
			
			//double randomVelX = (2 * Math.random() * 30000) - 15000;
			//double randomVelY = (2 * Math.random() * 30000) - 15000;
			//double randomVelZ = (2 * Math.random() * 30000) - 15000;
			
			Body b = new Body(randomMass, Physics.EARTH_RADIUS, 1);
			b.setPos(new Vector(randomPosX, randomPosY, randomPosZ));
			
			Vector vel = new Vector(-b.getPos().y, b.getPos().x, 0);
			vel = Vector.product(Physics.circularOrbit(b3, b.getPos().getMagnitude()), vel.getUnitVector());
			
			System.out.println(vel);
			b.setVel(vel);
			
			/*
			if(b.getPos().x < b3.getPos().x) {
				if(b.getPos().y < b3.getPos().y) 
					b.setVel(new Vector(-300, 300, 0));
				else
					b.setVel(new Vector(300, 300, 0));
			} else {
				if(b.getPos().y < b3.getPos().y)
					b.setVel(new Vector(-300, -300, 0));
				else
					b.setVel(new Vector(300, -300, 0));
			}
			*/
			
			bodies.add(b);
		}
		
		/*
		Body b3 = new Body(1.989 * Math.pow(10, 30), 1, 1);
		b3.setPos(new Vector(0, 0, 0));
		b3.setVel(new Vector(0, 0, 0));
		
		Body b = new Body(5.972 * Math.pow(10, 24), 1, 1);
		b.setPos(new Vector(149000000000.0, 0, 0));
		b.setVel(new Vector(0, 30000, 0));
		
		Body b2 = new Body(7.342 * Math.pow(10, 22), 1, 1);
		b2.setPos(new Vector(108000000000.0, 0, 0));
		b2.setVel(new Vector(0, 35000, 0));
		
		
		bodies.add(b);
		bodies.add(b2);
		bodies.add(b3);
		
		*/
		
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
		
	}
	
	private void update(double dt) {
		double barycenter = 0;
		
		outerloop: for(Body b1 : bodies) {
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
		
		Graphics2D g2d = (Graphics2D)panel.getGraphics();
		g2d.setColor(new Color(0, 0, 0, 0));
		g2d.clearRect(0, 0, panel.getWidth(), panel.getHeight());
		
		g2d.setColor(Color.black);
		
		for(Body b : bodies) {
			int x = (int) (b.getPos().x / 1490000000.0) + 200;
			int y = (int) (b.getPos().y / 1490000000.0) + 200;
			int size = (int) (5 * (b.getRadius() / Physics.EARTH_RADIUS));
			
			x -= size / 2;
			y -= size / 2;
			
			g2d.fillOval(x, y, size, size);
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

				updatesPerSecond = 0;
				framesPerSecond = 0;
			}
			update(dt * 360000);
			
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
