package org.acme.javaspringvacaturebankbackend.controller;

import java.util.Map;

import org.acme.javaspringvacaturebankbackend.model.employeeModel;
import org.acme.javaspringvacaturebankbackend.service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api/v1/employees")
public class employeeController {
    @Autowired
    private employeeService employeeService;

    // GET Method to get all Employees
    @GetMapping
    public Iterable<employeeModel> getAllEmployees() {
        return employeeService.getEmployees();
    }

    // POST method to post Employees
    @PostMapping
    public employeeModel postemployee(@RequestBody employeeModel employee) {
      return employeeService.postEmployee(employee);
    }

    // Patch method for updating Employees
    @PatchMapping(value = "/patch/employee/{id}")
    public employeeModel employeeModel (@PathVariable(value = "id", required = false) int id, @RequestBody Map<String, Object> fields)  {
        return employeeService.patchEmployee(id, fields);
    }
    
    // Delete method for deleting Employees
    @DeleteMapping(value = "/delete/employee/{id}")
    public String employeeModel  (@PathVariable(value = "id", required = false) int id){
        return employeeService.deleteEmployee(id);
    } 

    

}
