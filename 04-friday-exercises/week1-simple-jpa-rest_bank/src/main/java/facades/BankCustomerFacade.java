package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private BankCustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerByID(Long id) {
        EntityManager em = getEntityManager();
        try {
            BankCustomer customer = em.find(BankCustomer.class, id);
            CustomerDTO custDto = new CustomerDTO(customer);
            return custDto;
        } finally {
            em.close();
        }
    }

    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = getEntityManager();
        List<CustomerDTO> custDto = new ArrayList();
        try {
            TypedQuery<BankCustomer> query
                    = em.createQuery("Select bankCustomer from BankCustomer bankCustomer WHERE bankCustomer.firstName LIKE ?1", BankCustomer.class).setParameter(1, name);
            List<BankCustomer> customers = query.getResultList();
            for (BankCustomer cust : customers) {
                custDto.add(new CustomerDTO(cust));
            }
            return custDto;
        } finally {
            em.close();
        }
    }

    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }

    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<BankCustomer> query
                    = em.createQuery("Select bankCustomer from BankCustomer bankCustomer", BankCustomer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
