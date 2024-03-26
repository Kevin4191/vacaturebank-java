package org.acme.javaspringvacaturebankbackend.service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.acme.javaspringvacaturebankbackend.model.employeeModel;
import org.acme.javaspringvacaturebankbackend.repository.employeeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class employeeService {

  @Autowired
  employeeRepository employeeRepository;

  // Get method
  public Iterable<employeeModel> getEmployees() {
    try {
      return employeeRepository.findAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return employeeRepository.findAll();
  }

  // Post method
  public employeeModel postEmployee(employeeModel employee) {
    try {
      // Check if employee fields are left empty
      if ((employee.getEmployeeLocation().isEmpty())) {
        throw new IllegalArgumentException("Fields cannot be empty");
      }

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
    return employeeRepository.save(employee);
  }

  // Patch method
  public employeeModel patchEmployee(int id, Map<String, Object> fields) {
    try {
      Optional<employeeModel> existingEmployee = employeeRepository.findById(id);
      if (existingEmployee.isPresent()) {
        fields.forEach((key, value) -> { // Map through fields
          Field field = ReflectionUtils.findField(employeeModel.class, key);
          if ((key.toString() != "employeeId") && (!StringUtils.isAlphaSpace(value.toString()))) {
            throw new IllegalArgumentException("Field cannot contain numbers or special characters" + key.toString()
                + (StringUtils.isAlphaSpace(value.toString())));
          } else {
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingEmployee.get(), value);
          }
        });
        return employeeRepository.save(existingEmployee.get());
      } else {
        throw new IllegalArgumentException("employee not found");
      }

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  // Delete Method
  public String deleteEmployee(int id) {
    employeeRepository.deleteById(id);
    try {
      Optional<employeeModel> existingEmployee = employeeRepository.findById(id);
      if (existingEmployee.isPresent()) {
        employeeRepository.deleteById(id);
        return "employee deleted succesfully";
      } else {
        throw new IllegalArgumentException("employee not found");
      }

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }

  }

}
