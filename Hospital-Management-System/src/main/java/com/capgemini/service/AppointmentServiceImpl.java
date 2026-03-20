package com.capgemini.service;


import com.capgemini.dto.*;
import com.capgemini.entity.*;
import com.capgemini.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AppointmentServiceImpl {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private AppointmentRepository repo;

    private boolean isValidStatus(String status) {
        return status.equals("PENDING") ||
               status.equals("CONFIRMED") ||
               status.equals("DONE");
    }

    public AppointmentDTO createAppointment(int doctorId, AppointmentDTO dto) {

        Doctor d = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (!isValidStatus(dto.status)) {
            throw new RuntimeException("Invalid status");
        }

        if (dto.scheduledTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Time must be future");
        }

        Appointment a = new Appointment();
        a.setPatientName(dto.patientName);
        a.setScheduledTime(dto.scheduledTime);
        a.setStatus(dto.status);
        a.setDoctor(d);

        Appointment saved = repo.save(a);

        dto.appointmentId = saved.getAppointmentId();
        return dto;
    }
}