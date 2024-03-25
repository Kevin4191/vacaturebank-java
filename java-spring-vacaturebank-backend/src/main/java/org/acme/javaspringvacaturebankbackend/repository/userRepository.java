package org.acme.javaspringvacaturebankbackend.repository;

import org.acme.javaspringvacaturebankbackend.model.userModel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@Repository
public interface userRepository extends CrudRepository<userModel, Integer> {

    
}
