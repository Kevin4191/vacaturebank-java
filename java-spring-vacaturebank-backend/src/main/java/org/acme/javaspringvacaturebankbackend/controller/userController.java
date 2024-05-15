package org.acme.javaspringvacaturebankbackend.controller;

import java.util.Map;

import org.acme.javaspringvacaturebankbackend.model.userModel;
import org.acme.javaspringvacaturebankbackend.service.userService;
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
@RequestMapping(value = "/api/v1/users")
public class userController {

    @Autowired
    private userService userService;

    // GET Method to get all vacancies
    @GetMapping
    public Iterable<userModel> getAllUsers() {
        return userService.getUsers();
    }

    // POST method to post vacancies
    @PostMapping
    public userModel postUser(@RequestBody userModel user) {
      return userService.postUser(user);
    }

    // Patch method for updating vacancies
    @PatchMapping(value = "/patch/user/{id}")
    public userModel userModel (@PathVariable(value = "id", required = false) int id, @RequestBody Map<String, Object> fields)  {
        return userService.patchUser(id, fields);
    }
    
    // Delete method for deleting vacancies
    @DeleteMapping(value = "/delete/user/{id}")
    public String userModel  (@PathVariable(value = "id", required = false) int id){
        return userService.deleteUser(id);
    } 

    @PostMapping("/login")
    public Boolean loginUser(@RequestBody Map<String, Object> fields) {
        return userService.validateUser(fields);
    }
    

}
