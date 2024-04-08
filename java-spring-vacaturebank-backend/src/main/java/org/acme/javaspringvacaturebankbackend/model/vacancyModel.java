package org.acme.javaspringvacaturebankbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "vacancies") // Specify which table needs to be used from the db
public class vacancyModel {
    // Specify which columns needs to be used from the db
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generate random id
    @Column(name = "vacancies_id")
    private Integer vacancyId;
    @NotBlank
    @Column(name = "vacancy_name")
    private String vacancyName;
    @NotBlank
    @Column(name = "vacancy_description")
    private String vacancyDescription;
    @NotBlank
    @Column(name = "vacancy_salary")
    private String vacancySalary;
    @NotBlank
    @Column(name = "vacancy_education")
    private String vacancyEducation;
    @NotBlank
    @Column(name = "vacancy_location")
    private String vacancyLocation;
    @NotBlank
    @Column(name = "vacancy_workinghours")
    private String vacancyWorkingHours;
    @NotBlank
    @Column(name = "vacancy_upload_date")
    private String vacancyUploadDate;
    @Column(name = "branches_branch_id")
    private Integer vacancyBranchesBranchId;
    @Column(name = "employers_employer_id")
    private Integer vacancyEmployersEmployerId;

    // Relations
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "employers_employer_id", insertable = false, updatable = false) 
    private employerModel employer;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branches_branch_id", referencedColumnName = "branch_id", insertable = false, updatable = false)
    private branchModel branches;
}
