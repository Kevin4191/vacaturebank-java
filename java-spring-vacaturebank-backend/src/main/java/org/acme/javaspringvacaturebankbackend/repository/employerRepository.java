package org.acme.javaspringvacaturebankbackend.repository;



import org.acme.javaspringvacaturebankbackend.model.employerModel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@SpringBootApplication
@Repository
public interface employerRepository extends CrudRepository<employerModel, Integer>, JpaSpecificationExecutor<employerModel> {   
}
