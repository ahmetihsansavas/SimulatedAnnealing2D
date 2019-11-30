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
public class Car implements Cloneable{
	
	private double area = 10;
	private double cost;
	private double[] genes = new double[2];

	public Car() {
		generateCar();
	}
	
	public Car(double[] genes, double area, double cost) {
		for (int i = 0; i < genes.length; i++) {
			this.genes[i] = genes[i];
		}
		this.area = area;
		this.cost = cost;
	}
	
	// Create a random car
	public void generateCar() {
		for (int i = 0; i < genes.length; i++) {
			double gene = getRandomGene();
			area = area * gene;
			genes[i] = gene;
		}
		cost = area * 0.5;
	}
	
	public int size() {
		return genes.length;
	}
	
	public static double getRandomGene() {
		return 10 + (Math.random() * 50);
	}
		
	/* Getters and Setters */
	
	public double getGene(int index) {
		return genes[index];
	}
	
	public void setGene(int index, double value) {
		genes[index] = value;
	}
	
	public void setGenes(double[] genes) {
		this.genes = genes;
	}
	
	public double[] getGenes() {
		return genes;
	}
	
	public double getArea() {
		return genes[0] * genes[1];
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getCost() {
		return genes[0] * genes[1] * 0.5;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return genes[0] + " - " + genes[1];
	}
	
}
