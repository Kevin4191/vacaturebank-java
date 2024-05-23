package org.acme.javaspringvacaturebankbackend.controller;


import org.acme.javaspringvacaturebankbackend.model.createVacancyModel;
import org.acme.javaspringvacaturebankbackend.service.createVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api/v1/createVacancy")
public class createVacancyController {
    @Autowired
    private createVacancyService createVacancyService;

    // POST method to post vacancies
    @PostMapping
    public createVacancyModel postVacancy(@RequestBody createVacancyModel vacancy) {
      return createVacancyService.postVacancy(vacancy);
    }


    

}
