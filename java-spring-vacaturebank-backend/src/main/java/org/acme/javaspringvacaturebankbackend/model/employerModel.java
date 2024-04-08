package org.acme.javaspringvacaturebankbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "employers") // Specify which table needs to be used from the db
public class employerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private int employerId;
    @Column(name = "employer_company")
    private String employerCompany;
    @Column(name = "employer_email")
    private String employerEmail;
    @Column(name = "employer_phonenumber")
    private String employerPhoneNumber;
    @Column(name = "employer_location")
    private String employerLocation;
    @Column(name = "employer_city")
    private String employerCity;
}
