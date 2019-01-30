package flappy.artificialIntelligence;

import flappy.game.entity.Block;

/*
 * Reference => https://github.com/ssusnic/Machine-Learning-Flappy-Bird
 */

public class GeneticAlgorithm {

	private int maximum_agents;
	private int top_agents;
	private final int SCALE_FACTOR = 200;
	private Agent[] population;
	
	private int iteration;
	private float mutateRate;
	private int best_popultaion;
	private float best_fitness;
	private float best_score;
	
	public GeneticAlgorithm(int maximum_agents, int top_agents) {
		this.maximum_agents = maximum_agents;
		this.top_agents = top_agents;
		
		if(this.maximum_agents < this.top_agents)
			this.top_agents = this.maximum_agents;
		
		this.population = new Agent[this.maximum_agents];
	}
	
	public void reset() {
		this.iteration = 1;
		this.mutateRate = 1;
		
		this.best_popultaion = 0;
		this.best_fitness = 0;
		this.best_score = 0;
	}
	
	// Find a way to generate a random Synaptic neural network
	// with 2 neurons in the input layer, 6 neurons in the hidden layer and 1 neuron in the output layer
	public void createPopulation() {
		this.population = null;
		
		for(int i = 0; i < this.maximum_agents; i++) {
			this.population[i] = null;
		}
	}
	
	// activates the neural network of an unit from the population 
	// to calculate an output action according to the inputs
	public void activateBrain(Agent agent, Block block) {
		// input 1: the horizontal distance between the bird and the target
				
		// input 2: the height difference between the bird and the target
			
		// create an array of all inputs
				
		// calculate outputs by activating synaptic neural network of this bird
					
		// perform flap if output is greater than 0.5
	}
	
	public void evolvePopulation() {
		
	}
	
	public Agent[] selection() {
		return null;
	}
	
	public Agent crossover(Agent a, Agent b) {
		return null;
	}
	
	public void mutation() {
		// Need to return something here
	}
	
	public float mutate(float gene) {
		return 0f; 
	}
	
	public float random(float min, float max) {
		return (float) Math.floor(Math.random() * (max - min + 1) + min);
	}
	
	public <T> T getRandomUnit(T[] a) {
		return a[(int) this.random(0, a.length - 1)];
	}
	
	public int normalize(int value, int max) {
		if(value < -max) {
			value = -max;
		}else if(value > max) {
			value = max;
		}
		
		return value / max;
	}
}