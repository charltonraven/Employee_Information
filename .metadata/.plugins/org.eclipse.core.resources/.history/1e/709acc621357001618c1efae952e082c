package Sonoco.DatabaseConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DBConnector {

	private static Connection conn;

	

	public DBConnector(){
		this.conn=null;
	}
	public static Connection setConn(Connection conn) {
		String url = "jdbc:mysql://localhost:3306/employeeinfo";
		String user = "root";
		String password = "Raven47946$";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("Error: " + cnfe.getMessage());
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Not Connected");
		
	}
		return DBConnector.conn = conn;
	}

	public  Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/employeeinfo";
		String user = "root";
		String password = "Raven47946$";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("Error: " + cnfe.getMessage());
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Not Connected");
		}
		return conn;
	}
	
}
