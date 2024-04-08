package org.acme.javaspringvacaturebankbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users") // Specify which table needs to be used from the db
public class userModel {
    // Specify which columns needs to be used from the db
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generate random id
    @Column(name = "user_id")
    private int userId;
    @NotBlank
    @Column(name = "user_name")
    private String userName;
    @NotBlank
    @Column(name = "user_email")
    private String userEmail;
    @NotBlank
    @Column(name = "user_phonenumber")
    private String userPhonenumber;
    @NotBlank
    @Column(name = "user_password")
    private String userPassword;
    @NotBlank
    @Column(name = "user_role")
    private String userRole;
    @Column(name = "employers_employer_id")
    private Integer employersEmployerId;
    @Column(name = "employees_employee_id")
    private Integer employeesEmployeeId;
}
