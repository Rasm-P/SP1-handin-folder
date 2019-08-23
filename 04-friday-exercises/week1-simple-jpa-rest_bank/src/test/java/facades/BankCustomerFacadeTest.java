/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
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
public class BankCustomerFacadeTest {

    EntityManagerFactory em = Persistence.createEntityManagerFactory("pu");
    BankCustomerFacade facade = BankCustomerFacade.getFacadeExample(em);

    public BankCustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("pu");
        BankCustomerFacade facade = BankCustomerFacade.getFacadeExample(em);
        BankCustomer cust = new BankCustomer("Test", "Testerson", "12dsfs345", 3423434600.230, 55, "sfsz4dsdss5x4e6cr789b");
        facade.addCustomer(cust);
    }

    /**
     * Test of getCustomerByID method, of class BankCustomerFacade.
     */
    @Test
    public void testGetCustomerByID() {
        CustomerDTO cust = new CustomerDTO(new BankCustomer("Kaj", "Hansen", "12345", 34600.230, 45, "z45x4e6cr789b"));
        CustomerDTO result = facade.getCustomerByID(1l);
        assertTrue(result != null);
        assertEquals(cust.getFullName(), result.getFullName());
    }

    /**
     * Test of getCustomerByName method, of class BankCustomerFacade.
     */
    @Test
    public void testGetCustomerByName() {
        CustomerDTO customer = new CustomerDTO(new BankCustomer("Test", "Testerson", "12dsfs345", 3423434600.230, 55, "sfsz4dsdss5x4e6cr789b"));
        List<CustomerDTO> cust = facade.getCustomerByName(customer.getFullName());
        assertTrue(cust != null);
        assertEquals(cust.get(0).getFullName(), customer.getFullName());
    }

    /**
     * Test of addCustomer method, of class BankCustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        BankCustomer cust = new BankCustomer("Test2", "Testerson2", "12ddfsdfsfs345", 34600.230, 125, "sfssdfghz4dsdss5x4e6cr789b");
        BankCustomer c = facade.addCustomer(cust);
        CustomerDTO customer = new CustomerDTO(cust);
        List<CustomerDTO> custList = facade.getCustomerByName(customer.getFullName());
        assertEquals(cust.getAccountNumber(), custList.get(0).getAccountNumber());
    }

    /**
     * Test of getAllBankCustomers method, of class BankCustomerFacade.
     */
    @Test
    public void testGetAllBankCustomers() {
        List<BankCustomer> customers = facade.getAllBankCustomers();
        assertTrue(customers != null);
        assertTrue(!customers.isEmpty());
    }

}
