package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {

	 static EmployeePayrollDBService employeePayrollDBService;
     PreparedStatement employeePayrollDataStatement;

    //creating getConnection method and throws sql Exception
    Connection getConnection() throws EmployeePayrollExcepation, SQLException {
        Connection connection;
        String jdbcUrl = "jdbc:mysql://localhost:3306/employee_service?characterEncoding=utf8";
        String userName = "root";
        String Password = "12345";
        //getConnection method of DriverManager class attempts to establish a connection to the database by using the given db url
        connection = DriverManager.getConnection(jdbcUrl, userName, Password);
        System.out.println("Connection is successful!!" + connection);
        return connection;
    }

    public static EmployeePayrollDBService getInstance() {//getInstance method is EmployeePayrollDBService type
        if (employeePayrollDBService == null)
            employeePayrollDBService = new EmployeePayrollDBService();
        return employeePayrollDBService;
    }

    //readData method is List type which is of EmployeePayrollData type
    public List<EmployeePayrollData> readData() throws EmployeePayrollExcepation{
        String sql = "select * from employee_payroll";//display employee_payroll_new table
        return this.getEmployeePayrollDataUsingDB(sql);

    }

    private List<EmployeePayrollData> getEmployeePayrollDataUsingDB(String sql) throws EmployeePayrollExcepation{
        List<EmployeePayrollData> employeePayrollData = new ArrayList<>();
        try {
            Connection con = this.getConnection();//calling getConnection method
            //statement is interface used to sending sql query to db and con variable is of connection interface
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(sql);//select all records and display them
            employeePayrollData = this.getEmployeePayrollData(result);
        } catch (SQLException | EmployeePayrollExcepation e) {
            e.printStackTrace();
        }

        return employeePayrollData;
    }

    private List<EmployeePayrollData> getEmployeePayrollData(ResultSet result) {
        List<EmployeePayrollData> employeePayrollData = new ArrayList<>();//creating object of list and creating list of EmployeePayrollData type
        try {
            while (result.next()) {

                int id = result.getInt("Emp_id");
                String name = result.getString("name");
                double salary = result.getDouble("Basic_pay");
                employeePayrollData.add(new EmployeePayrollData(id, name, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollData;
    }

    public List<EmployeePayrollData> getEmployeePayrollData(String name) throws EmployeePayrollExcepation{
        List<EmployeePayrollData> employeePayrollData =null;
        if(this.employeePayrollDataStatement == null)
            this.prepareStatementForEmployeeData();
        try {
            employeePayrollDataStatement.setString(1, name);//set string in employeePayrollDataStatement
            ResultSet resultSet = employeePayrollDataStatement.executeQuery();//to get the resultSet
            employeePayrollData = this.getEmployeePayrollData(resultSet);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollData;
    }

    private void prepareStatementForEmployeeData() throws EmployeePayrollExcepation{
        try {
            Connection con = this.getConnection();
            String sql = "select * from employee_payroll_new where name = ?";//retrieve query
            employeePayrollDataStatement =  con.prepareStatement(sql);//query store in employeePayrollDataStatement
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
