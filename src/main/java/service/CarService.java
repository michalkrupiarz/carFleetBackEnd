package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.Car;
import exception.ItemNotFoundException;

/* 
 * It is just a helper class which should be replaced by database implementation. 
 * It is not very well written class, it is just used for demonstration. 
 */  




public class CarService {
	static HashMap<Integer,Car> carIdMap = getCarIdMap();
	
	public CarService() {
		super();
		if (carIdMap == null){
			carIdMap = new HashMap<Integer,Car>();
			Car indiaCar=new Car(1, "India","10000");  
			Car chinaCar=new Car(4, "China","20000");  
			Car nepalCar=new Car(3, "Nepal","8000");  
			Car bhutanCar=new Car(2, "Bhutan","7000"); 	
			   
			carIdMap.put(1, indiaCar);
			carIdMap.put(2, chinaCar);
			carIdMap.put(3, nepalCar);
			carIdMap.put(4, bhutanCar);
		}
	}
	
	public List<Car> getAllCars(){
		List<Car> cars = new ArrayList<Car>(carIdMap.values());
		return cars;
	}
	public  Car getCar(int id){
		Car car = carIdMap.get(id);
		if (car == null){
			throw new ItemNotFoundException("Car with id "+id+" not found");
		}
		return car;
	}
	
	private static HashMap<Integer, Car> getCarIdMap() {
		// TODO Auto-generated method stub
		return null;
	}
}