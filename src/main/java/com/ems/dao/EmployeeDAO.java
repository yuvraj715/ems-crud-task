package com.ems.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.ems.model.Employee;

public class EmployeeDAO {
	
    private String jdbcURL = "jdbc:mysql://localhost:3306/employee_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Charlie@12";

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employees (name, email, department, salary) VALUES (?, ?, ?, ?);";
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT id, name, email, department, salary FROM employees WHERE id = ?;";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT id, name, email, department, salary FROM employees;";
    private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM employees WHERE id = ?;";
    private static final String UPDATE_EMPLOYEE_SQL = "UPDATE employees SET name = ?, email = ?, department = ?, salary = ? WHERE id = ?;";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertEmployee(Employee emp) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartment());
            ps.setDouble(4, emp.getSalary());
            ps.executeUpdate();
        }
    }

    public Employee selectEmployee(int id) throws SQLException {
        Employee emp = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                    );
                }
            }
        }
        return emp;
    }

    public List<Employee> selectAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_EMPLOYEES);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                employees.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("department"),
                    rs.getDouble("salary")
                ));
            }
        }
        return employees;
    }

    public boolean deleteEmployee(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_SQL)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateEmployee(Employee emp) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_SQL)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartment());
            ps.setDouble(4, emp.getSalary());
            ps.setInt(5, emp.getId());
            return ps.executeUpdate() > 0;
        }
    }
}
