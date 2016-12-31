package service;

import java.sql.SQLException;
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
	}
	
	public List<Car> getAllCarsSQLWise() throws Exception{
		
		List<Car> cars = new ArrayList<Car>();
		String querry = "Select * from cars";
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
	
	public static Car addCarSqlWise(Car car) throws SQLException{
		String querry = "INSERT INTO cars (name,registration) VALUES('"+car.getName()+"','"+car.getRegistration()+"')";
		Car_Fleet_DB.update(querry);
		return car;
	}
	
	public static Car updateCar(Car car){
		if(car.getId()<=0){
			throw new ItemNotFoundException("Car with id "+car.getId()+" not found");
		}else {
			carIdMap.put(car.getId(), car);
		}
		return car;
	}
	
	public static Car updateCarSQLWise(Car car) throws SQLException{
		if(car.getId()<=0){
			throw new ItemNotFoundException("Car with id "+car.getId()+" not found");
		}else {
			System.out.println("sqlWise 3");
		String querry = "UPDATE cars SET name = '"+car.getName()+"', registration = '"+car.getRegistration()+"' where id = "+car.getId();
		Car_Fleet_DB.update(querry);
		}
		return car;
	}
	
	public static void deleteCarSqlWise(int id) throws SQLException{
		String querry = "DELETE from cars where id = "+id;
		Car_Fleet_DB.update(querry);
	}
	
	
	public static void deleteCar(int id){
		carIdMap.remove(id);
	}

	private static HashMap<Integer, Car> getCarIdMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
