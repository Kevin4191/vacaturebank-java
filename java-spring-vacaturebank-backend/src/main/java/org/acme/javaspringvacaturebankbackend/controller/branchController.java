package org.acme.javaspringvacaturebankbackend.controller;

import org.acme.javaspringvacaturebankbackend.model.branchModel;
import org.acme.javaspringvacaturebankbackend.service.branchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api/v1/branches")
public class branchController {
    @Autowired
    private branchService branchService;

    // GET Method to get all vacancie
    @GetMapping
    public Iterable<branchModel> getAllBranches() {
        return branchService.getBranches();
    }

}
