package com.appdhome.repository;

import com.appdhome.entities.Appointment;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment,Long> {
    public Appointment findAppointmentById(Long id);
    public List<Appointment> findAppointmentByIdCustomer(Long Id);
    public List<Appointment> findAppointmentByStatus(String status);
}
