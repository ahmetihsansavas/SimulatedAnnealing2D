/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatedannealing_2dlayout;

import java.util.Random;

/**
 *
 * @author ahmetihsan
 */
public class FitnessCalc {
	
	static double getFitness(Garage garage) {
		double fitness = 0.0;
		double sumArea = 0.0;
		for (int i = 0; i < garage.carSize(); i++) {
			Car car = garage.getCars().get(i);
			fitness += car.getCost();
			sumArea += car.getArea();
		}
		
		if(sumArea > garage.getArea())
			return -1;
		else
			return fitness + (garage.carSize());
	}
	
	static double diffMirror(double value) {
    /*	while(!(value > PSO.lowerBoundary && value < PSO.upperBoundary)) {
			
    		double diff = 0.0;
    		if (value < PSO.lowerBoundary) {
    			diff = value - PSO.lowerBoundary;
    			value = PSO.lowerBoundary - diff;
    		} 
    		
    		if (value > PSO.upperBoundary){
    			diff = value - PSO.upperBoundary;
    			value = PSO.upperBoundary - diff;
    		}
    		
		} */
    	return value;
    }
        
        
        
    
}
