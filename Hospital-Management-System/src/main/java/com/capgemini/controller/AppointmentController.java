package com.capgemini.controller;


import com.capgemini.dto.*;
import com.capgemini.service.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl service;

    @PostMapping("/doctors/{doctorId}/appointments")
    public AppointmentDTO create(@PathVariable int doctorId,
                                 @RequestBody AppointmentDTO dto) {
        return service.createAppointment(doctorId, dto);
    }
}