package flappy.ai;

public class Individual {

	public int fitness = 0;
	public int genes[];			// Decide how to choose genes
	public int geneLength;      // Define a length from the chosen genes
	
	public Individual() {
		//Random random = new Random();
		
		// Create genes for an individual by some randomness
		
		fitness = 0;
	}
	
	// Create a method to calculate the fitness and define what impacts the fitness
	public void calculateFitness() {
		fitness = 0;
	}
}
