/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Rasmus2
 */
public class MakeTestData {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        BankCustomer customer1 = new BankCustomer("Kaj", "Hansen", "12345", 34600.230, 45, "z45x4e6cr789b");
        BankCustomer customer2 = new BankCustomer("Ole", "Olsen", "67891", 2335523.340, 35, "4zwx5ec6r7vt8b");
        BankCustomer customer3 = new BankCustomer("Anders", "Andersen", "26893", 23462474723.340, 19, "wz5zx7c8v89w");
        try {
            em.getTransaction().begin();
            em.persist(customer1);
            em.persist(customer2);
            em.persist(customer3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

//        try {
//            em.getTransaction().begin();
//            em.persist(customer1);
//            em.persist(customer2);
//            em.getTransaction().commit();
//            //Verify that customers are managed and has been given a database id
//            System.out.println(customer1.getId());
//            System.out.println(customer2.getId());
//
//        } finally {
//            em.close();
//        }
    }
}
