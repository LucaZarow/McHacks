package mchacks;

import java.util.ArrayList;
import mchacks.physics.Body;

public class Simulation {
	private boolean running;
	private long framesPerSecond, updatesPerSecond;
	
	private Thread renderThread = new RenderThread();
	private Thread updateThread = new UpdateThread();
	
	public ArrayList<Body> bodies;
	
	public Simulation() {
		bodies = new ArrayList<Body>();
	}
	
	public void start() {
		running = true;
		
		updateThread.start();
		renderThread.start();
	}
	
	private void render() {
		
	}
	
	private void update(double dt) {
		
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
