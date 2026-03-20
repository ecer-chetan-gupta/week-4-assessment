package com.capgemini.controller;

import com.capgemini.dto.*;
import com.capgemini.service.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorServiceImpl service;

    @PostMapping
    public DoctorDTO create(@RequestBody DoctorDTO dto) {
        return service.createDoctor(dto);
    }

    @GetMapping
    public List<DoctorDTO> getAll() {
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorDTO get(@PathVariable int id) {
        return service.getDoctor(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteDoctor(id);
    }
}