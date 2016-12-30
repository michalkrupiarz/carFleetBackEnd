package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import hsql_server.*;
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
			carIdMap.put(4, chinaCar);
			carIdMap.put(3, nepalCar);
			carIdMap.put(2, bhutanCar);
		}
	}
	
	public List<Car> getAllCars(){
		List<Car> cars = new ArrayList<Car>(carIdMap.values());
		return cars;
	}
	
	public List<Car> getAllCarsSQLWise() throws Exception{
		
		
		List<Car> cars = new ArrayList<Car>();
		String querry = "Select * from cars";
		System.out.println("47");
		cars = Car_Fleet_DB.getAllCars(querry);
		return cars;
	}
	public  Car getCar(int id){	
		
		Car car = carIdMap.get(id);
		if (car == null){
			throw new ItemNotFoundException("Car with id "+id+" not found");
		}
		return car;
	}
	
	public static Car addCar(Car car){
		car.setId(getMaxId()+1);
		carIdMap.put(car.getId(), car);
		return car;
	}
	
	private static int getMaxId() {
		int max = 0;
		for (int id:carIdMap.keySet()){
			if(max<=id)
				max=id;
		}
		return max;
	}
	
	public static Car updateCar(Car car){
		if(car.getId()<=0){
			throw new ItemNotFoundException("Car with id "+car.getId()+" not found");
		}else {
			carIdMap.put(car.getId(), car);
		}
		return car;
	}
	
	public static void deleteCar(int id){
		carIdMap.remove(id);
	}

	private static HashMap<Integer, Car> getCarIdMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
