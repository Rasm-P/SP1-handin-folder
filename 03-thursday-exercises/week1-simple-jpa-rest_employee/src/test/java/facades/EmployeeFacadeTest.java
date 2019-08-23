/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rasmus2
 */
public class EmployeeFacadeTest {

    EntityManagerFactory em = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getFacadeExample(em);

    public EmployeeFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("pu");
        EmployeeFacade facade = EmployeeFacade.getFacadeExample(em);
        Employee e1 = new Employee("Ole", "Ølleby", 20200.000);
        facade.createEmployee(e1);
        Employee e2 = new Employee("Anders", "Andrerstad", 30301.00);
        facade.createEmployee(e2);
        Employee e3 = new Employee("Mickael", "Eldrup", 30301.00);
        facade.createEmployee(e3);
    }

    /**
     * Test of getEmployeeById method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeeById() {
        Employee emp = new Employee("Ole", "Ølleby", 2020.000);
        emp.setId(1l);
        Employee result = facade.getEmployeeById(1l);
        assertTrue(result != null);
        assertEquals(emp.getName(), result.getName());
    }

    /**
     * Test of getEmployeesByName method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesByName() {
        Employee emp = new Employee("Test2", "Testerson2", 10.00);
        facade.createEmployee(emp);
        List<Employee> result = facade.getEmployeesByName("Test2");
        assertTrue(result != null);
        assertEquals(emp.getName(), result.get(0).getName());
    }

    /**
     * Test of getAllEmployees method, of class EmployeeFacade.
     */
    @Test
    public void testGetAllEmployees() {
        List<Employee> result = facade.getAllEmployees();
        assertTrue(result != null);
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of getEmployeesWithHighestSalary method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesWithHighestSalary() {
        List<Employee> result = facade.getEmployeesWithHighestSalary();
        assertTrue(result != null);
        assertTrue(result.size() >= 1);
        assertEquals(30301.00, result.get(0).getSalary(), 0.0);
    }

    /**
     * Test of createEmployee method, of class EmployeeFacade.
     */
    @Test
    public void testCreateEmployee() {
        Employee emp = new Employee("Create", "Employee", 200.00);
        facade.createEmployee(emp);
        List<Employee> otherEmp = facade.getEmployeesByName(emp.getName());
        assertTrue(emp.getName().equals(otherEmp.get(0).getName()));
    }

}
