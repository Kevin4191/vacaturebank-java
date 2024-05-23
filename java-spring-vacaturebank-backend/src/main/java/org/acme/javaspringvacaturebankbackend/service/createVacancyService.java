package org.acme.javaspringvacaturebankbackend.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Optional;

import org.acme.javaspringvacaturebankbackend.model.createVacancyModel;
import org.acme.javaspringvacaturebankbackend.model.vacancyModel;
import org.acme.javaspringvacaturebankbackend.repository.createVacancyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class createVacancyService {

  @Autowired
  createVacancyRepository createVacancyRepository;
  // format for current timestamp
  private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  Timestamp timestamp = new Timestamp(System.currentTimeMillis());

  // Post method
  public createVacancyModel postVacancy(createVacancyModel vacancy) {
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
    return createVacancyRepository.save(vacancy);
  }


    public createVacancyModel patchVacancy(int id, Map<String, Object> fields) {
    try {
      Optional<createVacancyModel> existingVacancy = createVacancyRepository.findById(id);
      if (existingVacancy.isPresent()) {
        fields.forEach((key, value) -> { // Map through fields
          Field field = ReflectionUtils.findField(createVacancyModel.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingVacancy.get(), value);
          //}
        });
        return createVacancyRepository.save(existingVacancy.get());
      } else {
        throw new IllegalArgumentException("Vacancy not found");
      }

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }

  }

}
