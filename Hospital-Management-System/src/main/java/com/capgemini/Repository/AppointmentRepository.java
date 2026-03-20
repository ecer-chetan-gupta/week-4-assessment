package com.capgemini.Repository;


import com.capgemini.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByDoctorDoctorId(int doctorId);

    List<Appointment> findByStatusOrderByScheduledTimeAsc(String status);
}