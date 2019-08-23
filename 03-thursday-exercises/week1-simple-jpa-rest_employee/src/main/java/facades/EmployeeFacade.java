package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeById(Long id) {
        EntityManager em = getEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            return employee;
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("Select employee from Employee employee WHERE employee.name LIKE ?1", Employee.class).setParameter(1, name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("Select employee from Employee employee", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("Select employee from Employee employee where employee.salary = (select max(employee.salary) from Employee employee)", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void createEmployee(Employee employee) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("pu");
        EmployeeFacade facade = EmployeeFacade.getFacadeExample(em);
        Employee e1 = new Employee("Ole", "Ølleby", 20200.000);
        facade.createEmployee(e1);
        Employee e2 = new Employee("Anders", "Andrerstad", 30301.00);
        facade.createEmployee(e2);
        Employee e3 = new Employee("Mickael", "Eldrup", 30301.00);
        facade.createEmployee(e3);

        System.out.println("Employee1: " + facade.getEmployeeById(e1.getId()));
        System.out.println("Employee2: " + facade.getEmployeeById(e2.getId()));
        System.out.println("Employee3: " + facade.getEmployeeById(e3.getId()));

        System.out.println("Employee name: " + facade.getEmployeesByName("Ole"));

        System.out.println("All employees: " + facade.getAllEmployees());

        System.out.println("Employees with highest salary: " + facade.getEmployeesWithHighestSalary());
    }

}
