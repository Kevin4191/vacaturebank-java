package org.acme.javaspringvacaturebankbackend.service;

import org.acme.javaspringvacaturebankbackend.model.branchModel;
import org.acme.javaspringvacaturebankbackend.repository.branchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class branchService {

  @Autowired
  branchRepository branchRepository;

  // Get method
  public Iterable<branchModel> getBranches() {
    try {
      return branchRepository.findAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return branchRepository.findAll();
  }

}
