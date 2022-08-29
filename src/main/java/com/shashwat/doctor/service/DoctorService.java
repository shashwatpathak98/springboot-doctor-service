package com.shashwat.doctor.service;

import com.shashwat.doctor.entity.Doctor;
import com.shashwat.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository repo;

    public Doctor saveDoctor(Doctor doctor) {
        return repo.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return repo.findAll();
    }

    public Doctor getDoctorById(String registrationNumber) {
        return repo.findById(registrationNumber).orElse(null);
    }

    public Doctor getDoctorByName(String name) {
        return repo.getDoctorByName(name);
    }

    public Doctor updateDoctor(Doctor doctor) {
        Doctor docToUpdate = repo.findById(doctor.getRegistrationNumber()).orElse(null);
        if (docToUpdate != null) {
            if (doctor.getAge() != null) docToUpdate.setAge(doctor.getAge());
            if (doctor.getName() != null) docToUpdate.setName(doctor.getName());
            if (doctor.getGender() != null) docToUpdate.setGender(doctor.getGender());
            if (doctor.getSpecialization() != null) docToUpdate.setSpecialization(doctor.getSpecialization());
            if (doctor.getPatientsAttended() != null) docToUpdate.setPatientsAttended(doctor.getPatientsAttended());
            repo.save(docToUpdate);
        }
        return docToUpdate;
    }

    public Boolean deleteDoctor(String registrationNumber) {
        Boolean status = false;
        Doctor docToUpdate = repo.findById(registrationNumber).orElse(null);
        if (docToUpdate != null) {
            repo.deleteById(docToUpdate.getRegistrationNumber());
            status = true;
        }
        return status;
    }
}
