package hsql_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StartDB {
	Connection conn;
	public StartDB(String db_file_name_prefix) throws Exception {
		Class.forName("org.hsqldb.jdbd.JDBCDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:"+db_file_name_prefix,"SA","");
	}
	
	public synchronized void update(String expression) throws SQLException {

        Statement st = null;

        st = conn.createStatement();    // statements

        int i = st.executeUpdate(expression);    // run the query

        if (i == -1) {
            System.out.println("db error : " + expression);
        }

        st.close();
    }   
	
	public void shutdown() throws SQLException {
		
	}
	
	public void createAndPopulateCarTable(StartDB db) throws SQLException{
		db.update("CREATE TABLE car ( id INTEGER IDENTITY, name VARCHAR(256), registration VARCHAR(256)");
		db.update("INSERT INTO car(str_col,str_col) VALUES('Ford', 'AXX100')");
		db.update("INSERT INTO car(str_col,str_col) VALUES('Toyota', 'AXX200')");
		db.update("INSERT INTO car(str_col,str_col) VALUES('Dodge', 'AXX300')");
		db.update("INSERT INTO car(str_col,str_col) VALUES('BMW', 'AXX400')");
		db.update("INSERT INTO car(str_col,str_col) VALUES('Mercedes', 'AXX500')");
	}
	public static void createCarFleetDB(String db_file_name){
		StartDB carFleetDB = null;
		try {carFleetDB = new StartDB("carFleetDB");
		} catch (Exception ex1){
			ex1.printStackTrace();
			return;
		}
		
			try {
				carFleetDB.createAndPopulateCarTable(carFleetDB);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		
		
	}
}
