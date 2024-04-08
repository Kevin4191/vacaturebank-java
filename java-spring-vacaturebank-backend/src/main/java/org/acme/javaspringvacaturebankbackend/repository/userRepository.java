package org.acme.javaspringvacaturebankbackend.repository;

import java.util.List;

import org.acme.javaspringvacaturebankbackend.model.userModel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@Repository
public interface userRepository extends CrudRepository<userModel, Integer> {
    // Method to find user by email
    @Query("SELECT u FROM userModel u WHERE ( u.userEmail = :email) AND u.userId <> :id")
    public List<userModel> findUserByEmail(@Param("email") String email, @Param("id") Integer id);

    @Query("SELECT u FROM userModel u WHERE (u.userEmail = :email)")
    public List<userModel> validationByEmail(@Param("email") String email);
    
}

