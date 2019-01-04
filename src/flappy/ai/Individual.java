package flappy.ai;

import java.awt.Graphics;

import flappy.game.entity.Entity;

public class Individual extends Entity{

	public int fitness = 0;
	public int genes[];			// Decide how to choose genes
	public int geneLength;      // Define a length from the chosen genes

	public boolean isAlive = false;
	
	public Individual(float x, float y, int width, int height) {
		super(x, y, width, height);
		
		//Random random = new Random();
		
		// Create genes for an individual by some randomness
		
		fitness = 0;
	}
	
	// Create a method to calculate the fitness and define what impacts the fitness
	public void calculateFitness() {
		fitness = 0;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}
}
