package hsql_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {
	
	Connection conn;
	

	public TestDB(String db_file_name_prefix) throws Exception {    // note more general exception

	        // Load the HSQL Database Engine JDBC driver
	        // hsqldb.jar should be in the class path or made part of the current jar
	        Class.forName("org.hsqldb.jdbc.JDBCDriver");

	        // connect to the database.   This will load the db files and start the
	        // database if it is not alread running.
	        // db_file_name_prefix is used to open or create files that hold the state
	        // of the db.
	        // It can contain directory names relative to the
	        // current working directory
	        conn = DriverManager.getConnection("jdbc:hsqldb:"
	                                           + db_file_name_prefix,    // filenames
	                                           "SA",                     // username
	                                           "");                      // password
	    }
	 
	 public void shutdown() throws SQLException {

	        Statement st = conn.createStatement();

	        // db writes out to files and performs clean shuts down
	        // otherwise there will be an unclean shutdown
	        // when program ends
	        st.execute("SHUTDOWN");
	        conn.close();    // if there are no other open connection
	    }
	 
	 public synchronized void query(String expression) throws SQLException {

	        Statement st = null;
	        ResultSet rs = null;

	        st = conn.createStatement();         // statement objects can be reused with

	        // repeated calls to execute but we
	        // choose to make a new one each time
	        rs = st.executeQuery(expression);    // run the query

	        // do something with the result set.
	        dump(rs);
	        st.close();    // NOTE!! if you close a statement the associated ResultSet is

	        // closed too
	        // so you should copy the contents to some other object.
	        // the result set is invalidated also  if you recycle an Statement
	        // and try to execute some other query before the result set has been
	        // completely examined.
	    }
	 
	 public synchronized void update(String expression) throws SQLException {

	        Statement st = null;

	        st = conn.createStatement();    // statements

	        int i = st.executeUpdate(expression);    // run the query

	        if (i == -1) {
	            System.out.println("db error : " + expression);
	        }

	        st.close();
	    }    // void update()
	 public static void dump(ResultSet rs) throws SQLException {

	        // the order of the rows in a cursor
	        // are implementation dependent unless you use the SQL ORDER statement
	        ResultSetMetaData meta   = rs.getMetaData();
	        int               colmax = meta.getColumnCount();
	        int               i;
	        Object            o = null;
	       

	        // the result set is a cursor into the data.  You can only
	        // point to one row at a time
	        // assume we are pointing to BEFORE the first row
	        // rs.next() points to next row and returns true
	        // or false if there is no next row, which breaks the loop
	        for (; rs.next(); ) {
	            for (i = 0; i < colmax; ++i) {
	                o = rs.getObject(i + 1);    // Is SQL the first column is indexed

	                // with 1 not 0
	                System.out.print(meta.getColumnName(i+1)+" ");
	                System.out.print(meta.getColumnTypeName(i+1)+" ");
	                System.out.print(meta.getColumnLabel(i+1)+" ");
	                System.out.print(o.toString() + " ");
	            }

	            System.out.println(" ");
	        }
	    }                                       //void dump( ResultSet rs )
	 
	 
	 
	 public static void main(String[] args) {

	        TestDB db = null;

	        try {
	            db = new TestDB("car_Fleet_3");
	        } catch (Exception ex1) {
	            ex1.printStackTrace();    // could not start db

	            return;                   // bye bye
	        }

	        try {

	            //make an empty table
	            //
	            // by declaring the id column IDENTITY, the db will automatically
	            // generate unique values for new rows- useful for row keys
	            db.update(
	            		"CREATE TABLE car_table (id INTEGER IDENTITY, name_col VARCHAR(256), registration_col VARCHAR(256))");
	        } catch (SQLException ex2) {
	        	System.out.println(ex2);
	        	ex2.printStackTrace();
	            //ignore
	            //ex2.printStackTrace();  // second time we run programv
	            //  should throw execption since table
	            // already there
	            //
	            // this will have no effect on the db
	        }

	        try {

	            // add some rows - will create duplicates if run more then once
	            // the id column is automatically generated
	            db.update(
	                "INSERT INTO car_table(name_col,registration_col) VALUES('Ford', 'AXX100')");
	            db.update(
	                "INSERT INTO car_table(name_col,registration_col) VALUES('Toyota', 'AXX100')");
	            db.update(
	                "INSERT INTO car_table(name_col,registration_col) VALUES('Honda', 'AXX100')");
	            db.update(
	                "INSERT INTO car_table(name_col,registration_col) VALUES('GM', 'AXX100')");

	            // do a query
	            db.query("SELECT * FROM car_table");

	            // at end of program
	            db.shutdown();
	        } catch (SQLException ex3) {
	            ex3.printStackTrace();
	        }
	    }
	 
}
