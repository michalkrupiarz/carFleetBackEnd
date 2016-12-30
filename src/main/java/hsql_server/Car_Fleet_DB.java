package hsql_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;import bean.Car;

public class Car_Fleet_DB {
	static Connection conn;
	public Car_Fleet_DB() throws Exception {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:car_fleet_5","SA","");
	}
	public void shutdown() throws SQLException {

        Statement st = conn.createStatement();

        // db writes out to files and performs clean shuts down
        // otherwise there will be an unclean shutdown
        // when program ends
        st.execute("SHUTDOWN");
        conn.close();    // if there are no other open connection
    }
	
	
	public synchronized void update(String querry) throws SQLException{
		Statement st = null;

        st = conn.createStatement();    // statements

        int i = st.executeUpdate(querry);    // run the query

        if (i == -1) {
            System.out.println("db error : " + querry);
        }

        st.close();
    }
	
	
	public static void checkOrCreateTable(Car_Fleet_DB db){
		try {
			db.update("CREATE TABLE cars (id INTEGER IDENTITY, name VARCHAR(256), registration VARCHAR(256))");
			db.update(
	                "INSERT INTO cars(name,registration) VALUES('Ford', 'AXX100')");
	            db.update(
	                "INSERT INTO cars(name,registration) VALUES('Toyota', 'AXX100')");
	            db.update(
	                "INSERT INTO cars(name,registration) VALUES('Honda', 'AXX100')");
	            db.update(
	                "INSERT INTO cars(name,registration) VALUES('GM', 'AXX100')");
		} catch (SQLException ex2) {
        	System.out.println(ex2);
        	ex2.printStackTrace();}
		
	}
	public static List<Car> getAllCars(String querry) throws Exception{
		System.out.println("62");
		List<Car> cars = new ArrayList<>();
		System.out.println("64");
		Car_Fleet_DB db = new Car_Fleet_DB();
		System.out.println("66");
		Statement st = null;
        ResultSet rs = null;
        checkOrCreateTable(db);
        st = conn.createStatement();
        System.out.println(querry);
        
        rs = st.executeQuery(querry);
        cars = getCars(rs);
        st.close();
		return cars;
		}
	
	public static List<Car> getCars(ResultSet rs) throws SQLException{
		List <Car> cars = new ArrayList<>();
		System.out.println(rs);
		Car car = new Car();
		for (;rs.next();){
			car.setId(rs.getInt(1));
			car.setName(rs.getString(2));
			car.setRegistration(rs.getString(3));
			System.out.println(car);
			cars.add(car);
		}
		System.out.println(cars);
		return cars;
	}
}
