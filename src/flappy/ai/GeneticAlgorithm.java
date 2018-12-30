package flappy.ai;

import java.util.Random;

public class GeneticAlgorithm {
	
	public Population population = new Population();
	public Individual fittest;
	public Individual secondFittest;
	public int generationCount;
	public static int fitnessPoint = 5;
	
	public static void main(String[] args) {
		//Random random = new Random();
		GeneticAlgorithm ga = new GeneticAlgorithm();
		ga.population.initializePopulation(10);
		ga.population.calculateFitness();
		
		while(ga.population.fittest < fitnessPoint) {
			// do selection
			// do crossover
			// do mutation under a random probability
			// add fittest offspring to the population
			// calculate new fitness value
			
			System.out.println("Generation: " + ga.generationCount + " Fittest: " + ga.population.fittest);
		}
		
		System.out.println("\nSolution found in generation " + ga.generationCount);
        System.out.println("Fitness: " + ga.population.getFittest().fitness);
        System.out.print("Genes: ");
        
        Individual fittest = ga.population.getFittest();
        for (int i = 0; i < fittest.geneLength; i++) {
            System.out.print(fittest.genes[i]);
        }
        
        System.out.println();
	}
	
	public void selection() {
		fittest = population.getFittest();
		secondFittest = population.getSecondFittest();
	}
	
	public void crossOver() {
		Random random = new Random();
		
		// select a random crossover point
		int crossOverPoint = random.nextInt(population.individuals[0].geneLength);
		
		// swap values among parents
		for(int i = 0; i < crossOverPoint; i++) {
			int temp = fittest.genes[i];
			fittest.genes[i] = secondFittest.genes[i];
			secondFittest.genes[i] = temp;
		}
	}
	
	public void mutation() {
		Random random = new Random();
		
		int mutationPoint = random.nextInt(population.individuals[0].geneLength);
		
		if(fittest.genes[mutationPoint] == 0) {
			fittest.genes[mutationPoint] = 1;
		}else {
			fittest.genes[mutationPoint] = 0;
		}
		
		mutationPoint = random.nextInt(population.individuals[0].geneLength);
		
		if(secondFittest.genes[mutationPoint] == 0) {
			secondFittest.genes[mutationPoint] = 1;
		}else {
			secondFittest.genes[mutationPoint] = 0;
		}
	}
	
	public Individual getFittestOffspring() {
		if(fittest.fitness > secondFittest.fitness) {
			return fittest;
		}
		return secondFittest;
	}
	
	public void addFittestOffspring() {
		fittest.calculateFitness();
		secondFittest.calculateFitness();
		
		int leastFittestIndex = population.getLeastFittestIndex();
		
		population.individuals[leastFittestIndex] = getFittestOffspring();
	}
}
