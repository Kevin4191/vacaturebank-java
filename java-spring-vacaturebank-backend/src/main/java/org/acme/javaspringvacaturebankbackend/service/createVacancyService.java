package org.acme.javaspringvacaturebankbackend.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.acme.javaspringvacaturebankbackend.model.createVacancyModel;
import org.acme.javaspringvacaturebankbackend.repository.createVacancyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
