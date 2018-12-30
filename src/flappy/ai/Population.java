package flappy.ai;

public class Population {

	public int populationSize;
	public Individual[] individuals;
	public int fittest = 0;
	
	public void initializePopulation(int size) {
		this.populationSize = size;
		individuals = new Individual[populationSize];
		
		for(int i = 0; i < individuals.length; i++) {
			individuals[i] = new Individual();
		}
	}
	
	public Individual getFittest() {
		int maximumFitness = Integer.MIN_VALUE;
		int maximumFitnessIndex = 0;
		
		for(int i = 0; i < individuals.length; i++) {
			if(maximumFitness <= individuals[i].fitness) {
				maximumFitness = individuals[i].fitness;
				maximumFitnessIndex = i;
			}
		}
		
		fittest = individuals[maximumFitnessIndex].fitness;
		return individuals[maximumFitnessIndex];
	}
	
	public Individual getSecondFittest() {
		int maxFit1 = 0;
		int maxFit2 = 0;
		
		for(int i = 0; i < individuals.length; i++) {
			if(individuals[i].fitness > individuals[maxFit1].fitness) {
				maxFit2 = maxFit1;
				maxFit1 = i;
			}else if(individuals[i].fitness > individuals[maxFit2].fitness) {
				maxFit2 = i;
			}
		}
		
		return individuals[maxFit2];
	}
	
	public int getLeastFittestIndex() {
		int minFitVal = Integer.MAX_VALUE;
		int minFitIndex = 0;
		
		for(int i = 0; i < individuals.length; i++) {
			if(minFitVal >= individuals[i].fitness) {
				minFitVal = individuals[i].fitness;
				minFitIndex = i;
			}
		}
		
		return minFitIndex;
	}
	
	public void calculateFitness() {
		for(int i = 0; i < individuals.length; i++) {
			individuals[i].calculateFitness();
		}
		
		getFittest();
	}
}
