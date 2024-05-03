package cinema;

//import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DBConnector {

	static final String DB_URL = "jdbc:mysql://localhost:3306/cinema";
	static final String USER = "root";
	static final String PASS = "";
	
	public static Connection connectDB() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		} catch(Exception ex) {
			System.out.println("There were errors whil connecting to db");
			return null;
		}
	}

}
