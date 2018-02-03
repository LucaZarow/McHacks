package mchacks;

import java.util.ArrayList;
import mchacks.physics.Body;
import mchacks.physics.Physics;
import mchacks.util.Vector;

public class Simulation {
	private boolean running;
	private long framesPerSecond, updatesPerSecond;
	
	private Thread renderThread = new RenderThread();
	private Thread updateThread = new UpdateThread();
	
	public ArrayList<Body> bodies;
	
	public Simulation() {
		bodies = new ArrayList<Body>();
		
		Body b = new Body(1, 1, 1);
		b.setPos(new Vector(0, 0, 0));
		b.setVel(new Vector(1, 0, 0));
		
		bodies.add(b);
	}
	
	public void start() {
		running = true;
		
		updateThread.start();
		renderThread.start();
	}
	
	private void render() {
		
	}
	
	private void update(double dt) {
		for(Body b1 : bodies) {
			for(Body b2 : bodies) {
				if(b1 == b2) continue;
				
				//Check collisions
				if(b1.hasCollided(b2)) {
					Body b3 = new Body();
					b3.setMass(b1.getMass() + b2.getMass());
					b3.setRadius(b1.getRadius());
				}
				
				b1.applyDeltaAcc(Physics.gravity(b1, b2));
			}
			
			b1.update(dt);
		}
	}
	
	private class RenderThread extends Thread {
		public synchronized void run() {
			while(running) {
				render();
				framesPerSecond++;
			}
		}
	}
	
	private class UpdateThread extends Thread {
		public synchronized void run() {
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
				update(dt);
				
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
}
