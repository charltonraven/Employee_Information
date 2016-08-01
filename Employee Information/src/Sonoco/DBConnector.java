package Sonoco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import Sonoco.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBConnector {

    private static Connection conn;

    public DBConnector() {
        this.conn = null;
    }

    public Connection getConn() {
        String url = "jdbc:mysql://oldbrainbox:3306/employeeinfo";
        String user = "root";
        String password = "Raven47946$";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        }
        try {
            DBConnector.conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            // TODO Auto-generated catch block


        }
        return DBConnector.conn;
    }

    public Connection getConnection() {
        String url = "jdbc:mysql://oldbrainbox:3306/employeeinfo";
        String user = "root";
        String password = "Raven47946$";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        }
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Not Connected");
        }
        return conn;
    }

    public ObservableList<Employee> GenerateTable() throws SQLException {
        ObservableList<Employee> employee = FXCollections.observableArrayList();
        DBConnector start = new DBConnector();
        conn = start.getConn();

        ResultSet rs = null;
        Statement stmt = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery("Select * from employeeinformation");

        while (rs.next()) {
            String First = rs.getString("EmployeeFirstName");
            String Last = rs.getString("EmployeeLastName");
            String Phone = rs.getString("PhoneNumber");
            String Serial = rs.getString("SerialNumbers");
            String OldPC = rs.getString("OldPCName");
            String newPC = rs.getString("NewPCName");
            String Notes = rs.getString("Notes");
            String Date = rs.getString("Date");

            employee.add(new Employee(First, Last, Phone, Serial, OldPC, newPC, Notes, Date));
        }
        stmt.close();
        conn.close();

        return employee;

    }

    public void Add(Employee newEmployee) throws SQLException {
        conn = getConnection();


        Statement stmt;
        stmt = conn.createStatement();
       // System.out.println("INSERT INTO employeeinformation VALUES ('" + newEmployee.getEmployeeFirstName() + "','" + newEmployee.getEmployeeLastName() + "','" + newEmployee.getPhone() + "','" + newEmployee.getSerialNumbers() + "','" + newEmployee.getOldPCName() + "','" + newEmployee.getNewPCName() + "','" + newEmployee.getNotes() + "','" + newEmployee.getDate() + "') ");
       stmt.executeUpdate("INSERT INTO employeeinformation VALUES ('" + newEmployee.getEmployeeFirstName() + "','" + newEmployee.getEmployeeLastName() + "','" + newEmployee.getPhone() + "','" + newEmployee.getSerialNumbers() + "','" + newEmployee.getOldPCName() + "','" + newEmployee.getNewPCName() + "','" + newEmployee.getNotes() + "','" + newEmployee.getDate() + "') ");
        stmt.close();
        GenerateTable();

    }
}