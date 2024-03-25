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
@Table(name = "vacancies") // Specify which table needs to be used from the db
public class vacancyModel {
    // Specify which columns needs to be used from the db
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Generate random id
    @Column(name = "vacancies_id")
    private int vacancyId;
    @Column(name = "vacancy_name")
    private String vacancyName;
    @Column(name = "vacancy_description")
    private String vacancyDescription;
    @Column(name = "vacancy_salary")
    private String vacancySalary;
    @Column(name = "vacancy_education")
    private String vacancyEducation;
    @Column(name = "vacancy_location")
    private String vacancy_location;
    @Column(name = "vacancy_workinghours")
    private String vacancyWorkingHours;
    @Column(name = "vacancy_upload_date")
    private String vacancyUploadDate;
    @Column(name = "branches_branch_id")
    private int vacancyBranchesBranchId;
    @Column(name = "employers_employer_id")
    private int vacancyEmployersEmployerId;

}
