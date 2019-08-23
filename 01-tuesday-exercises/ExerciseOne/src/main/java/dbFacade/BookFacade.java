/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbFacade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rasmus2
 */
public class BookFacade {

    private static EntityManagerFactory emf;
    private static BookFacade instance;

    private BookFacade() {
    }

    //Renturns an instance of BookFacade.
    public static BookFacade getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookFacade();
        }
        return instance;
    }

    //Instansiates a book object and an entityManager and begins/commits a persistance of the book object.
    public Book addBook(String author) {
        Book book = new Book(author);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return book;
        } finally {
            em.close();
        }
    }

    //Instanciates entityManager and uses it to find and return a book object based on the object and its id.
    public Book findBook(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Book book = em.find(Book.class, id);
            return book;
        } finally {
            em.close();
        }
    }

    //Instanciates entityManager and creates a typedQuery with the object book, where the entity manager with some SQL and the book.class returns a resoult list of book objects.
    public List<Book> getAllBooks() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Book> query
                    = em.createQuery("Select book from Book book", Book.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("po");
        BookFacade facade = BookFacade.getBookFacade(em);
        Book b1 = facade.addBook("Author 1");
        Book b2 = facade.addBook("Author 2");
        //Find book by ID
        System.out.println("Book1: " + facade.findBook(b1.getId()).getAuthor());
        System.out.println("Book2: " + facade.findBook(b2.getId()).getAuthor());
        //Find all books
        System.out.println("Number of books: " + facade.getAllBooks().size());
    }

}
