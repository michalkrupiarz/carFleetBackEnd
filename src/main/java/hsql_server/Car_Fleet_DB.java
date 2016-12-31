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
		conn = DriverManager.getConnection("jdbc:hsqldb:C:/hsqldb-2.3.4/baza","SA","");
	}
	public void shutdown() throws SQLException {

        Statement st = conn.createStatement();

        // db writes out to files and performs clean shuts down
        // otherwise there will be an unclean shutdown
        // when program ends
        st.execute("SHUTDOWN");
        conn.close();    // if there are no other open connection
    }
	
	
	public static synchronized void update(String querry) throws SQLException{
		Statement st = null;
		System.out.println("XXX Executing querry XXX "+querry);
        st = conn.createStatement();    // statements
        
        int i = st.executeUpdate(querry);    // run the query

        if (i == -1) {
            System.out.println("db error : " + querry);
        }

        
        st.execute("SHUTDOWN");
        st.close();
        conn.close();  
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
        }		
	}
	public static List<Car> getAllCars(String querry) throws Exception{
	
		List<Car> cars = new ArrayList<>();
		
		Car_Fleet_DB db = new Car_Fleet_DB();
		
		Statement st = null;
        ResultSet rs = null;
        checkOrCreateTable(db);
        st = conn.createStatement();
       
        
        rs = st.executeQuery(querry);
        cars = getCars(rs);
        st.close();
		return cars;
		}
	
	public static List<Car> getCars(ResultSet rs) throws SQLException{
		List <Car> cars = new ArrayList<>();
		System.out.println(rs);
		
		for (;rs.next();){
			Car car = new Car();
			car.setId(rs.getInt(1));
			car.setName(rs.getString(2));
			car.setRegistration(rs.getString(3));
	
			cars.add(car);
		}
	
		return cars;
	}
	
}
