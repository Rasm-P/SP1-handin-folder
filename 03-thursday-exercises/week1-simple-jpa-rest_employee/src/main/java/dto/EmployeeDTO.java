/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Employee;

/**
 *
 * @author Rasmus2
 */
public class EmployeeDTO {

    private Long id;
    private String name;
    private String adress;

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        adress = employee.getAdress();
    }

}
