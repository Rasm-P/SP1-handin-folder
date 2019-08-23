/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Customer;
import facade.EntityFacade;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Rasmus2
 */
@Path("customer")
public class CustomerEndpoint {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityFacade facade = EntityFacade.getCustomerFacade(emf);
    Gson g = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TheCustomer
     */
    public CustomerEndpoint() {
        Customer b1 = facade.addCustomer("Ole", "Olsen");
        Customer b2 = facade.addCustomer("Anders", "Andrersen");
    }

    /**
     * Retrieves representation of an instance of rest.TheCustomer
     *
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getString() {
        //TODO return proper representation object
        return "Hej";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomer() {
        //TODO return proper representation object
        List<Customer> cus = facade.allCustomers();
        return g.toJson(cus);
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomCustomer() {
        //TODO return proper representation object
        List<Customer> cus = facade.allCustomers();
        Random r = new Random();
        Customer s = cus.get(r.nextInt(facade.getNumberOfCustomers() + 1));
        return g.toJson(s);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerById(@PathParam("id") Long id) {
        //TODO return proper representation object
        Customer cust = facade.findByID(id);
        return g.toJson(cust);
    }
    
}
