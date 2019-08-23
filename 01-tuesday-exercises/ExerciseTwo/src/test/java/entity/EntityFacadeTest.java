/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import facade.EntityFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Rasmus2
 */
public class EntityFacadeTest {

    EntityManagerFactory em = Persistence.createEntityManagerFactory("pu");
    EntityFacade facade = EntityFacade.getCustomerFacade(em);

    public EntityFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("pu");
        EntityFacade facade = EntityFacade.getCustomerFacade(em);
        facade.addCustomer("Test2", "Testerson2");
    }

    /**
     * Test of getCustomerFacade method, of class EntityFacade.
     */
    @Test
    public void testGetCustomerFacade() {
        EntityManagerFactory _emf = null;
        EntityFacade result = EntityFacade.getCustomerFacade(_emf);
        assertTrue(result != null);
    }

    /**
     * Test of addCustomer method, of class EntityFacade.
     */
    @Test
    public void testAddCustomer() {
        Customer customer = new Customer("Test", "Testerson");
        Customer result = facade.addCustomer(customer.getFirstName(), customer.getLastName());
        assertEquals(customer.getLastName(), result.getLastName());
    }

    /**
     * Test of findByID method, of class EntityFacade.
     */
    @Test
    public void testFindByID() {
        Customer cust = new Customer("Ole", "Olsen");
        cust.setId(1l);
        Customer result = facade.findByID(1l);
        assertEquals(cust, result);
    }

    /**
     * Test of allCustomers method, of class EntityFacade.
     */
    @Test
    public void testAllCustomers() {
        List<Customer> customers = facade.allCustomers();
        assertTrue(customers != null);
        assertTrue(!customers.isEmpty());
    }

    /**
     * Test of findByLastName method, of class EntityFacade.
     */
    @Test
    public void testFindByLastName() {
        Customer customer = new Customer("Test2", "Testerson2");
        List<Customer> cust = facade.findByLastName(customer.getLastName());
        assertTrue(cust != null);
        assertEquals(cust.get(0).getFirstName(), customer.getFirstName());
    }

    /**
     * Test of getNumberOfCustomers method, of class EntityFacade.
     */
    @Test
    public void testGetNumberOfCustomers() {
        int num = facade.getNumberOfCustomers();
        assertTrue(num >= 2);
    }

}
