package com.shashwat.doctor;

import com.shashwat.doctor.controller.DoctorController;
import com.shashwat.doctor.entity.Doctor;
import com.shashwat.doctor.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DoctorController.class)
public class DoctorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService service;

    @Test
    public void shouldReturnStudentById() throws Exception {
        Doctor mockDoctor = new Doctor();
        mockDoctor.setRegistrationNumber("R1");
        mockDoctor.setName("Test Doc");
        mockDoctor.setPatientsAttended(2);
        mockDoctor.setSpecialization("S1");
        mockDoctor.setAge(33);
        mockDoctor.setGender("Male");
        when(service.getDoctorById("R1")).thenReturn(mockDoctor);
        this.mockMvc.perform(get("/doctor/findById/R1")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStudentByIdResponse() throws Exception {
        Doctor mockDoctor = new Doctor();
        mockDoctor.setRegistrationNumber("R3");
        mockDoctor.setName("Test3");
        mockDoctor.setPatientsAttended(2);
        mockDoctor.setSpecialization("S3");
        mockDoctor.setAge(33);
        mockDoctor.setGender("Male");
        when(service.getDoctorById("R3")).thenReturn(mockDoctor);
        this.mockMvc.perform(get("/doctor/findById/R3")).andExpect(status().isOk()).andExpect(content().json("{" +
                "    \"registrationNumber\": \"R3\"," +
                "    \"name\": \"Test3\"," +
                "    \"specialization\": \"S3\",\n" +
                "    \"age\": 33,\n" +
                "    \"gender\": \"Male\"\n" +
                "}"));
    }

    @Test
    public void shouldThrowExceptionIfDoctorNotFound() throws Exception {
        when(service.getDoctorById("")).thenThrow(new NoSuchElementException());
        this.mockMvc.perform(get("/doctor/findById/")).andDo(print()).andExpect(status().isNotFound());
    }
}
