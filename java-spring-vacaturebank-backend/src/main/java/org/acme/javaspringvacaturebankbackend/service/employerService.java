package org.acme.javaspringvacaturebankbackend.service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.acme.javaspringvacaturebankbackend.model.employerModel;
import org.acme.javaspringvacaturebankbackend.repository.employerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class employerService {

  @Autowired
  employerRepository employerRepository;

  // Get method
  public Iterable<employerModel> getEmployers() {
    try {
      return employerRepository.findAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return employerRepository.findAll();
  }

  // Post method
  public employerModel postEmployer(employerModel employer) {
    try {
      // Check if employer fields are left empty
      if ((employer.getEmployerCompany().isEmpty()) || (employer.getEmployerEmail().isEmpty())
          || (employer.getEmployerPhoneNumber().isEmpty())
          || (employer.getEmployerLocation().isEmpty()) || (employer.getEmployerCity().isEmpty())) {
        throw new IllegalArgumentException("Fields cannot be empty");
      }
      // check if employer fields contain numbers or special characters
      if ((!StringUtils.isAlphaSpace(employer.getEmployerCompany()))) {
        throw new IllegalArgumentException("Name cannot contain numbers or special characters");
      }

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
    return employerRepository.save(employer);
  }

  // Patch method
  public employerModel patchEmployer(int id, Map<String, Object> fields) {
    try {
      Optional<employerModel> existingEmployer = employerRepository.findById(id);
      if (existingEmployer.isPresent()) {
        fields.forEach((key, value) -> { // Map through fields
          Field field = ReflectionUtils.findField(employerModel.class, key);
          if ((key.toString() != "employerId") && (!StringUtils.isAlphaSpace(value.toString()))) {
            throw new IllegalArgumentException("Field cannot contain numbers or special characters" + key.toString()
                + (StringUtils.isAlphaSpace(value.toString())));
          } else {
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingEmployer.get(), value);
          }
        });
        return employerRepository.save(existingEmployer.get());
      } else {
        throw new IllegalArgumentException("employer not found");
      }

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  // Delete Method
  public String deleteEmployer(int id) {
    employerRepository.deleteById(id);
    try {
      Optional<employerModel> existingEmployer = employerRepository.findById(id);
      if (existingEmployer.isPresent()) {
        employerRepository.deleteById(id);
        return "employer deleted succesfully";
      } else {
        throw new IllegalArgumentException("employer not found");
      }

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
    

  }

}
