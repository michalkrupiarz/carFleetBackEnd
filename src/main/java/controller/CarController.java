package controller;

import java.util.List;

import javax.ws.rs.DELETE;  
import javax.ws.rs.GET;  
import javax.ws.rs.POST;  
import javax.ws.rs.PUT;  
import javax.ws.rs.Path;  
import javax.ws.rs.PathParam;  
import javax.ws.rs.Produces;  
import javax.ws.rs.core.MediaType;


import service.CarService;  
import bean.Car;

@Path("/cars")
public class CarController {
	
	CarService carService = new CarService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Car> getCars()
	{	
		List<Car> listOfCars = carService.getAllCars();
		
		return listOfCars;
	}
	@GET
	@Path("/carId={id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCarById(@PathParam("id") int id){
		return carService.getCar(id);
	}
	 @POST  
	    @Produces(MediaType.APPLICATION_JSON)  
	 public Car addCar(Car Car)  
	 {  
	  return CarService.addCar(Car);  
	 }  
	  
	    @PUT  
	    @Produces(MediaType.APPLICATION_JSON)  
	 public Car updateCar(Car Car)  
	 {  
	  return CarService.updateCar(Car);  
	    
	 }  
	   
	    @DELETE  
	    @Path("/{id}")  
	    @Produces(MediaType.APPLICATION_JSON)  
	 public void deleteCar(@PathParam("id") int id)  
	 {  
	   CarService.deleteCar(id);  
	    
	 }  

	
}
