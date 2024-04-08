package org.acme.javaspringvacaturebankbackend.controller;

import java.util.Map;

import org.acme.javaspringvacaturebankbackend.model.employerModel;
import org.acme.javaspringvacaturebankbackend.service.employerService;
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
@RequestMapping(value = "/api/v1/employers")
public class employerController {
    @Autowired
    private employerService employerService;

    // GET Method to get all Employers
    @GetMapping
    public Iterable<employerModel> getAllEmployers() {
        return employerService.getEmployers();
    }

    // POST method to post Employers
    @PostMapping
    public employerModel postemployer(@RequestBody employerModel employer) {
      return employerService.postEmployer(employer);
    }

    // Patch method for updating Employers
    @PatchMapping(value = "/patch/employer/{id}")
    public employerModel employerModel (@PathVariable(value = "id", required = false) int id, @RequestBody Map<String, Object> fields)  {
        return employerService.patchEmployer(id, fields);
    }
    
    // Delete method for deleting Employers
    @DeleteMapping(value = "/delete/employer/{id}")
    public String employerModel  (@PathVariable(value = "id", required = false) int id){
        return employerService.deleteEmployer(id);
    } 

    

}
