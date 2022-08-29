package com.shashwat.doctor.repository;

import com.shashwat.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    public Doctor getDoctorByName(String name);
}
