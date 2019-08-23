/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class EntityFacade {

    private static EntityManagerFactory emf;
    private static EntityFacade instance;

    private EntityFacade() {
    }

    public static EntityFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EntityFacade();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Customer addCustomer(String fName, String lName) {
        Customer customer = new Customer(fName, lName);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }

    public Customer findByID(Long id) {
        EntityManager em = getEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select customer from Customer customer WHERE customer.lastName LIKE ?1", Customer.class).setParameter(1, name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public int getNumberOfCustomers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList().size();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU");
        EntityFacade facade = EntityFacade.getCustomerFacade(em);
        Customer b1 = facade.addCustomer("Ole", "Olsen");
        Customer b2 = facade.addCustomer("Anders", "Andrersen");
        //Find book by ID
        System.out.println("Customer1: " + facade.findByID(b1.getId()));
        System.out.println("Customer2: " + facade.findByID(b2.getId()));
        //Find all books
        System.out.println("Number of Customers: " + facade.allCustomers());
        //Customer by last name
        System.out.println("Customer2: " + facade.findByLastName("Andrersen"));
        //Number of all customers
        System.out.println("Number of all customers: " + facade.getNumberOfCustomers());
    }
}
