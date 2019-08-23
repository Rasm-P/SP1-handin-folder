package rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CustomerDTO;
import entities.BankCustomer;
import facades.BankCustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("bankcustomer")
public class BankCustomerResource {

    EntityManagerFactory em = Persistence.createEntityManagerFactory("pu");
    BankCustomerFacade facade = BankCustomerFacade.getFacadeExample(em);
    Gson g = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBankCustomerById(@PathParam("id") Long id) {
        CustomerDTO dto = facade.getCustomerByID(id);
        return g.toJson(dto);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployeesList() {
        List<BankCustomer> list = facade.getAllBankCustomers();
        return g.toJson(list);
    }

}
