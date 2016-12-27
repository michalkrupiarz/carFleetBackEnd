package bean;

public class Car {
	
	int id;
	String name;
	String registration;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public Car(int id, String name, String registration) {
		super();
		this.id = id;
		this.name = name;
		this.registration = registration;
	}
	public Car() {
		super();
	}

	
}
