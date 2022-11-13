package com.example.demo;

import com.example.demo.config.EmployeeConfig;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(EmployeeConfig.class);

        EmployeeRepository employeeRepository = applicationContext.getBean(EmployeeRepository.class);

//        ApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        EmployeeRepository employeeRepository1 = xmlApplicationContext.getBean(EmployeeRepository.class);
//        employeeRepository1.printStatements();

        Employee employees = new Employee();
        employees.setEmployeeId(208L);
        employees.setFirstName("Kate");
        employees.setLastName("Johnson");
        employees.setEmail("kate.jhons@sqltutorial.org");
        employees.setPhoneNumber("515.123.1212");
        employees.setHireDate("1998-08-11");
        employees.setJobId(19);
        employees.setSalary(8700);
        employees.setManagerId(101);
        employees.setDepartmentId(11);

        employeeRepository.insertNewEmployee(employees);
//
//        employeeRepository.insertNewDepartmentBatch();
//
//
//        Employee employees2 = new Employee();
//        employees2.setEmployeeId(43L);
//        employees.setFirstName("Jhon");
//        employees.setLastName("Johnson");
//        employees.setEmail("kate.jhons@sqltutorial.org");
//        employees.setPhoneNumber("515.123.1212");
//        employees.setHireDate("1998-08-11");
//        employees.setJobId(19);
//        employees.setSalary(8700);
//        employees.setManagerId(101);
//        employees.setDepartmentId(11);
//
//        employeeRepository.updateEmployeeId(employees2.getFirstName(), employees2.getEmployeeId());
//        employeeRepository.deleteEmployeeWithId(41L);
    }
}
