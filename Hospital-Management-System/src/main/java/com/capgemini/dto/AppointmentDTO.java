package com.capgemini.dto;


import jakarta.validation.constraints.Future;
import java.time.LocalDateTime;

public class AppointmentDTO {

    public int appointmentId;
    public String patientName;

    @Future
    public LocalDateTime scheduledTime;

    public String status;
}