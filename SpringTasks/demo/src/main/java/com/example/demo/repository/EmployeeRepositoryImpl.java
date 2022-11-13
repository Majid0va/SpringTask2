package com.example.demo.repository;
import org.springframework.stereotype.Component;
import com.example.demo.model.Employee;


import java.sql.*;
@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=636847482";


    @Override
    public void printStatements() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select * From departments");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("department_id"));
                System.out.println("\tDepartment name: " + rs.getString("department_name"));
                System.out.println("\tLocation ID: " + rs.getInt("location_id"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public long insertNewEmployee(Employee employees) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into employees(employee_id,first_name,last_name,email,phone_number,hire_date,job_id,salary,manager_id,department_id) values( ?,?,?, ?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, employees.getEmployeeId());
            preparedStatement.setString(2, employees.getFirstName());
            preparedStatement.setString(3, employees.getLastName());
            preparedStatement.setString(4, employees.getEmail());
            preparedStatement.setString(5, employees.getPhoneNumber());
            preparedStatement.setDate(6, Date.valueOf(employees.getHireDate()));
            preparedStatement.setInt(7, employees.getJobId());
            preparedStatement.setDouble(8, employees.getSalary());
            preparedStatement.setInt(9, employees.getManagerId());
            preparedStatement.setInt(10, employees.getDepartmentId());


            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                return rs.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1L;
    }


    @Override
    public void insertNewDepartmentBatch() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into departments(department_id, department_name, location_id) values (?,?,?)");
            preparedStatement.setLong(1, 12L);
            preparedStatement.setString(2, "Back-end");
            preparedStatement.setDouble(3, 1800);

            preparedStatement.addBatch();
            preparedStatement.setLong(1, 13L);
            preparedStatement.setString(2, "Front-end");
            preparedStatement.setDouble(3, 1800);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }


    @Override
    public void updateEmployeeId(String firstName, Long employeeId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement("update employees set first_name = ? where employee_id = ? ");

            preparedStatement.setString(1, String.valueOf(firstName));
            preparedStatement.setLong(2, employeeId);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteEmployeeWithId(Long employeeId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {

            PreparedStatement preparedStatement = connection.prepareStatement("delete from employees where employee_id = ?");

            preparedStatement.setLong(1, employeeId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
