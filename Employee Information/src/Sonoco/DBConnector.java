package Sonoco;

import java.sql.*;

import javax.sql.DataSource;

import Sonoco.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBConnector {

    private static Connection conn;
    PreparedStatement AddEmployee = null;
    PreparedStatement EditEmployee = null;
    PreparedStatement DeleteEmployee = null;
    String url = "jdbc:mysql://oldbrainbox:3306/employeeinfo";
    String user = "root";
    String password = "Raven47946$";


    public DBConnector() {
        this.conn = null;
    }

    public Connection getConn() {


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

        conn = DriverManager.getConnection(url, user, password);
        ResultSet rs = null;
        String getAllstmt = "Select * from employeeinformation";
        PreparedStatement getTables = conn.prepareStatement(getAllstmt);
        rs = getTables.executeQuery();

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

        rs.close();
        conn.close();

        return employee;

    }

    public ObservableList<Employee> Add(Employee newEmployee) throws SQLException {
        if (conn == null) {
            conn = getConnection();
        }


        String stmt = "INSERT INTO employeeinformation VALUES ('" + newEmployee.getEmployeeFirstName() + "','" + newEmployee.getEmployeeLastName() + "','" + newEmployee.getPhone() + "','" + newEmployee.getSerialNumbers() + "','" + newEmployee.getOldPCName() + "','" + newEmployee.getNewPCName() + "','" + newEmployee.getNotes() + "','" + newEmployee.getDate() + "') ";
        AddEmployee = conn.prepareStatement(stmt);
        AddEmployee.execute();

        ObservableList<Employee> employee = FXCollections.observableArrayList();

        ResultSet rs = null;
        String getAllstmt = "Select * from employeeinformation";
        PreparedStatement getTables = conn.prepareStatement(getAllstmt);
        rs = getTables.executeQuery();

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

        rs.close();
        conn.close();

        return employee;

    }

    public ObservableList<Employee> edit(Employee ObservedEmployee) throws SQLException {
        if (conn == null) {
            conn = getConnection();
        }


        String stmt = "UPDATE employeeinformation SET PhoneNumber='" + ObservedEmployee.getPhone() + "',SerialNumbers='" + ObservedEmployee.getSerialNumbers() + "',NewPCName='" + ObservedEmployee.getNewPCName() + "',Notes='" + ObservedEmployee.getNotes() + "' WHERE EmployeeLastName='" + ObservedEmployee.getEmployeeLastName() + "' AND SerialNumbers='" + ObservedEmployee.getSerialNumbers() + "';";
        EditEmployee = conn.prepareStatement(stmt);
        EditEmployee.execute();


        ObservableList<Employee> employee = FXCollections.observableArrayList();
        ResultSet rs = null;
        String getAllstmt = "Select * from employeeinformation";
        PreparedStatement getTables = conn.prepareStatement(getAllstmt);
        rs = getTables.executeQuery();

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

        rs.close();
        conn.close();

        return employee;

    }

    public ObservableList<Employee> delete(Employee ObservedEmployee) throws SQLException {
        if (conn == null) {
            conn = getConnection();
        }
        String stmt = "DELETE FROM employeeinformation WHERE EmployeeLastName='" + ObservedEmployee.getEmployeeLastName() + "' AND SerialNumbers='" + ObservedEmployee.getSerialNumbers() + "';";
        DeleteEmployee = conn.prepareStatement(stmt);

        DeleteEmployee.execute();

        ObservableList<Employee> employee = FXCollections.observableArrayList();

        ResultSet rs = null;
        String getAllstmt = "Select * from employeeinformation";
        PreparedStatement getTables = conn.prepareStatement(getAllstmt);
        rs = getTables.executeQuery();

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

        rs.close();
        conn.close();

        return employee;
    }
    public ObservableList<Employee> Search(Employee ObservedEmployee) throws SQLException {

        return null;
    }

}