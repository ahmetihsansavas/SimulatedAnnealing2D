/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatedannealing_2dlayout;

/**
 *
 * @author ahmetihsan
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulatedAnnealing {
	
	public static List<Double> runResults;
	
	// Calculate the acceptance probability
    public static double acceptanceProbability(double energy, double newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy > energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }
    
    public static void main(String[] args) {
    	int sayac=1;
    	int counter = 0;
    	double globalMean = 0;
        
        double toplam=0;
        double currentmean=0;
    	runResults = new ArrayList<Double>();
    	
    	while(counter < 100) {
                
	    	// Init System Temp
	    	double temp = 1000000;
	    	
	    	// Init Cooling Rate
	    	double coolingRate = 0.5;
	    	
	    	// Init Current Solution
	    	Garage currentSolution = new Garage();
	    	currentSolution.fillGarage();
	    	
	    	// Set Best Solution
	    	Garage best = currentSolution;
	    	
	    	while(temp > 1) {
	    		
	    		// Create New Solution
	    		Garage newSolution = new Garage(currentSolution);
	    		
	    		swapGene(newSolution);
	    		
	    		// Get energy of solutions
	            double currentEnergy = currentSolution.getFitness();
	            double neighbourEnergy = newSolution.getFitness();
                    System.out.println(sayac+"-current solution "+currentSolution.getFitness()+"- Car Size "+ currentSolution.carSize()+"- sıcaklık "+temp +" toplam "+toplam);	
	            // Decide if we should accept the neighbour
	            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
	                currentSolution = newSolution;
	            }
	
	            // Keep track of the best solution found
	            if (currentSolution.getFitness() > best.getFitness()) {
	                best = currentSolution;
	            }
	            
	            // Cool system
	            temp *= 1 - coolingRate;
                    sayac++;
                    if (currentSolution.getFitness()>1) {
                        toplam+=currentSolution.getFitness();
                    }
	        //System.out.println("current solution "+currentSolution.getFitness()+"- Car Size "+ currentSolution.carSize());	 
	    	}
                
	    	
	        System.out.println("Final solution distance: " + best.getFitness() + "- Car Size:" + best.carSize() + " - Total Area:" + best.sumCarCapacity()+"  sıcaklık değeri : "+temp);
	        runResults.add(best.getFitness());
        	globalMean += best.getFitness();
            counter++;
            System.out.println("Run : " + counter + " - Global Best:" + best.getFitness() + " - Car Size:" + best.carSize() + " - Total Area:" + best.sumCarCapacity());
        
    	}
    	
    	Collections.sort(runResults);
        System.out.println("-------------------");
        System.out.println("100 Run Best:" + runResults.get(runResults.size() - 1) + "- 100 Run Mean:" + (globalMean / 100) + " - 100 Run St. Dev:" + calculateStandartDev(globalMean / 100)+"  toplamın ortalaması "+(toplam/sayac)+"toplamın standart sapması "+calculateStandartDev(toplam / sayac));
        
	}
    
    public static double calculateStandartDev(double mean) {
		
		double result = 0.0;
		for (Double value : runResults) {
			result += Math.pow((value - mean), 2); 
		}
		return Math.sqrt(result / (runResults.size() - 1)); 
	}
    
    public static void swapGene(Garage newSolution) {
    	Garage testSolution = new Garage();
    	testSolution.fillGarage();
    	
    	int minSize = testSolution.carSize();
    	
    	if(newSolution.carSize() < minSize)
    		minSize = newSolution.carSize();
    	
    	// Generate Random Index
    	int randomIndex1 = (int) (minSize * Math.random());
    	int randomIndex2 = (int) (minSize * Math.random());
    	
    	// Select Random Car
    	Car randomCar1 = testSolution.getCars().get(randomIndex1);
    	Car randomCar2 = testSolution.getCars().get(randomIndex1);
    	
    	newSolution.setFitness(0);
    	newSolution.setCar(randomIndex1, randomCar1);
    	newSolution.setCar(randomIndex2, randomCar2);
    }
}
