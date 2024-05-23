package org.acme.javaspringvacaturebankbackend.controller;

import java.util.Map;

import org.acme.javaspringvacaturebankbackend.model.createVacancyModel;
import org.acme.javaspringvacaturebankbackend.model.vacancyModel;
import org.acme.javaspringvacaturebankbackend.service.createVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  // Patch method for updating vacancies
  @PatchMapping(value = "/patch/{id}")
  public createVacancyModel vacancyModel(@PathVariable(value = "id", required = false) int id,
      @RequestBody Map<String, Object> fields) {
    return createVacancyService.patchVacancy(id, fields);
  }

}
