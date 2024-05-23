package org.acme.javaspringvacaturebankbackend.controller;

import java.util.Map;
import java.util.Optional;

import org.acme.javaspringvacaturebankbackend.model.vacancyModel;
import org.acme.javaspringvacaturebankbackend.service.vacancyService;
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
@RequestMapping(value = "/api/v1/vacancies")
public class vacancyController {
    @Autowired
    private vacancyService vacancyService;

    // GET Method to get all vacancies
    @GetMapping
    public Iterable<vacancyModel> getAllVacancies() {
        return vacancyService.getVacancies();
    }

    // GET Method to get vacancy by id
    @GetMapping(value = "/{id}")
    public Optional<vacancyModel> getVacancyById(@PathVariable int id) {
        return vacancyService.getVacancyById(id);
    }

    // POST method to post vacancies
    @PostMapping
    public vacancyModel postVacancy(@RequestBody vacancyModel vacancy) {
        return vacancyService.postVacancy(vacancy);
    }

    // Patch method for updating vacancies
    @PatchMapping(value = "/patch/vacancy/{id}")
    public vacancyModel vacancyModel(@PathVariable(value = "id", required = false) int id,
            @RequestBody Map<String, Object> fields) {
        return vacancyService.patchVacancy(id, fields);
    }

    // Delete method for deleting vacancies
    @DeleteMapping(value = "/delete/vacancy/{id}")
    public String vacancyModel(@PathVariable(value = "id", required = false) int id) {
        return vacancyService.deleteVacancy(id);
    }

}
