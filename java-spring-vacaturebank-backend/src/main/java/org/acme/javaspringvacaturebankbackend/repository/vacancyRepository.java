package org.acme.javaspringvacaturebankbackend.repository;

import org.acme.javaspringvacaturebankbackend.model.vacancyModel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@Repository
public interface vacancyRepository extends CrudRepository<vacancyModel, Integer> {

    
}
