package com.capgemini.service;


import com.capgemini.dto.*;
import com.capgemini.entity.Doctor;
import com.capgemini.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DoctorServiceImpl {

    @Autowired
    private DoctorRepository repo;

    public DoctorDTO createDoctor(DoctorDTO dto) {

        if (repo.findByEmail(dto.email).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Doctor d = new Doctor();
        d.setName(dto.name);
        d.setSpecialization(dto.specialization);
        d.setEmail(dto.email);
        d.setPhone(dto.phone);

        Doctor saved = repo.save(d);

        dto.doctorId = saved.getDoctorId();
        dto.totalAppointments = 0;

        return dto;
    }

    public List<DoctorDTO> getAllDoctors() {
        return repo.findAll().stream().map(d -> {
            DoctorDTO dto = new DoctorDTO();
            dto.doctorId = d.getDoctorId();
            dto.name = d.getName();
            dto.specialization = d.getSpecialization();
            dto.email = d.getEmail();
            dto.phone = d.getPhone();
            dto.totalAppointments = d.getAppointments().size();
            return dto;
        }).toList();
    }

    public DoctorDTO getDoctor(int id) {
        Doctor d = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        DoctorDTO dto = new DoctorDTO();
        dto.doctorId = d.getDoctorId();
        dto.name = d.getName();
        dto.specialization = d.getSpecialization();
        dto.email = d.getEmail();
        dto.phone = d.getPhone();
        dto.totalAppointments = d.getAppointments().size();
        return dto;
    }

    public void deleteDoctor(int id) {
        repo.deleteById(id);
    }
}