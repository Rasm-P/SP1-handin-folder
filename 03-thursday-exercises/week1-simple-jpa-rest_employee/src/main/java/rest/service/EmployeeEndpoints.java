package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
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

@Path("employee")
public class EmployeeEndpoints {

    EntityManagerFactory em = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getFacadeExample(em);
    Gson g = new Gson();

    @Context
    private UriInfo context;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployeesList() {
        List<EmployeeDTO> custDto = new ArrayList();
        List<Employee> employees = facade.getAllEmployees();
        for (Employee emp : employees) {
            custDto.add(new EmployeeDTO(emp));
        }
        return g.toJson(custDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeObById(@PathParam("id") Long id) {
        Employee employee = facade.getEmployeeById(id);
        EmployeeDTO dto = new EmployeeDTO(employee);
        return g.toJson(dto);
    }

    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public String getByHighestPaid() {
        List<EmployeeDTO> custDto = new ArrayList();
        List<Employee> employees = facade.getEmployeesWithHighestSalary();
        for (Employee emp : employees) {
            custDto.add(new EmployeeDTO(emp));
        }
        return g.toJson(custDto);
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeesByFirstName(@PathParam("name") String name) {
        List<EmployeeDTO> custDto = new ArrayList();
        List<Employee> employees = facade.getEmployeesByName(name);
        for (Employee emp : employees) {
            custDto.add(new EmployeeDTO(emp));
        }
        return g.toJson(custDto);
    }
}
