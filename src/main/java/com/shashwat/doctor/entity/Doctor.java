package com.shashwat.doctor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @Column(name = "REG_NUM", unique=true, columnDefinition="VARCHAR(64)")
    private String registrationNumber;
    private String name;
    private Integer age ;
    private String gender;
    private String specialization;
    private Integer patientsAttended = 0;
}