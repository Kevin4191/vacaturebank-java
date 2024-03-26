package org.acme.javaspringvacaturebankbackend.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Optional;

import org.acme.javaspringvacaturebankbackend.model.vacancyModel;
import org.acme.javaspringvacaturebankbackend.repository.vacancyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class vacancyService {

  @Autowired
  vacancyRepository vacancyRepository;
  // format for current timestamp
  private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  Timestamp timestamp = new Timestamp(System.currentTimeMillis());

  // Get method
  public Iterable<vacancyModel> getVacancies() {
    try {
      return vacancyRepository.findAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return vacancyRepository.findAll();
  }

  // Post method
  public vacancyModel postVacancy(vacancyModel vacancy) {
    try {
      // Check if vacancy fields are left empty
      if ((vacancy.getVacancyName().isEmpty()) || (vacancy.getVacancyDescription().isEmpty())
          || (vacancy.getVacancySalary().isEmpty()) || (vacancy.getVacancyEducation().isEmpty())
          || (vacancy.getVacancyLocation().isEmpty()) || (vacancy.getVacancyWorkingHours().isEmpty())
          || (vacancy.getVacancyUploadDate().isEmpty())
          || (vacancy.getVacancyBranchesBranchId() == 0) || (vacancy.getVacancyEmployersEmployerId() == 0)) {
        throw new IllegalArgumentException("Fields cannot be empty");
      }
      // check if vacancy fields contain numbers or special characters
      if ((!StringUtils.isAlphaSpace(vacancy.getVacancyName())
          || (!StringUtils.isAlphaSpace(vacancy.getVacancyEducation())))) {

        throw new IllegalArgumentException("Name cannot contain numbers or special characters");
      }

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
    // set current time stamp
    vacancy.setVacancyUploadDate(sdf3.format(timestamp));
    return vacancyRepository.save(vacancy);
  }

  // Patch method
  public vacancyModel patchVacancy(int id, Map<String, Object> fields) {
    try {
      Optional<vacancyModel> existingVacancy = vacancyRepository.findById(id);
      if (existingVacancy.isPresent()) {
        fields.forEach((key, value) -> { // Map through fields
          Field field = ReflectionUtils.findField(vacancyModel.class, key);
          if ((key.toString() != "vacancyBranchesBranchId") && (key.toString() != "vacancyEmployersEmployerId")
              && (key.toString() != "vacancyId") && (!StringUtils.isAlphaSpace(value.toString()))) {
            throw new IllegalArgumentException("Field cannot contain numbers or special characters" + key.toString()
                + (StringUtils.isAlphaSpace(value.toString())));
          } else {
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingVacancy.get(), value);
          }
        });
        return vacancyRepository.save(existingVacancy.get());
      } else {
        throw new IllegalArgumentException("Vacancy not found");
      }

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }

  }

  // Delete Method
  public String deleteVacancy(int id) {
    try {
      Optional<vacancyModel> existingVacancy = vacancyRepository.findById(id);
      if (existingVacancy.isPresent()) {
        vacancyRepository.deleteById(id);
      } else {
        throw new IllegalArgumentException("Vacancy not found");
      }

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
    return "Vacancy deleted succesfully";

  }

}
