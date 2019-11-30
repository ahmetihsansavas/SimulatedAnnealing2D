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

public class Garage {

	private double width;
	private double height;
	private double area;
	private double capacity;
	private ArrayList<Car> cars;
	private double fitness = 0;

	public Garage() {
		this.width = 1000;
		this.height = 800;
		this.area = width * height;
		this.capacity = this.area;
		cars = new ArrayList<>();
	}
	
	public Garage(Garage garage) {
		this.width = 1000;
		this.height = 800;
		this.area = width * height;
		this.capacity = this.area;
		cars = new ArrayList<>();
		cloneGarage(garage);
                //fillGarage();
	}

	/* Getter and Setters */
	public double getFitness() {
		if (fitness == 0) {
			fitness = FitnessCalc.getFitness(this);
		}
		return fitness;
	}

	public ArrayList<Car> getDatas() {
		return cars;
	}

	public void setPbest(ArrayList<Car> pbest) {
		for (int i = 0; i < pbest.size(); i++) {
			Car cloneCar = new Car(pbest.get(i).getGenes(), pbest.get(i).getArea(), pbest.get(i).getCost());
			pbest.set(i, cloneCar);
		}
	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	public int carSize() {
		return cars.size();
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public double getArea() {
		return area;
	}
	/* Public Methods */

	public void fillGarage() {
		Car newCar = new Car();
		while (capacity - newCar.getArea() >= 0) {
			addCar(newCar);
			capacity -= newCar.getArea();
			newCar = new Car();
		}
		fitness = 0;
	}
	
	public void cloneGarage(Garage garage) {
		this.capacity = garage.capacity;
		this.cars.clear();
		for (int i = 0; i < garage.carSize(); i++) {
			cars.add(garage.getCars().get(i));
		}
	}

	public double sumCarCapacity() {
		double sum = 0;
		for (int i = 0; i < carSize(); i++) {
			sum += cars.get(i).getArea();
		}
		return sum;
	}

	public void updateCapacity() {
		capacity = area;
		for (int i = 0; i < carSize(); i++) {
			capacity -= cars.get(i).getArea();
		}
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public void setCar(int index, Car car) {
		cars.set(index, car);
	}
	
	@Override
	public String toString() {
		return cars.toString();
	}

}