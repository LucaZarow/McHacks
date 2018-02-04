package mchacks;

import java.util.ArrayList;

import mchacks.physics.Body;
import mchacks.physics.Physics;
import mchacks.physics.SolarSystem;
import mchacks.util.Vector;

import mchacks.graphics.GraphicsApp;

public class Simulation implements Runnable {
	private boolean running;
	private long updatesPerSecond;
	
	public static double timeStepModifier = 360000;
	
	public ArrayList<Body> bodies;
	
	public Simulation() {
		bodies = SolarSystem.randomPlanetoids();
	}
	
	public void start() {
		new GraphicsApp(bodies);
		running = true;
		this.run();
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
				System.out.println("Updates: " + updatesPerSecond);
				updatesPerSecond = 0;
			}
			update(dt * timeStepModifier);
			
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
